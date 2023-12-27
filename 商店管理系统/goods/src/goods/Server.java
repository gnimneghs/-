package goods;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.*;
import java.net.Socket;

//服务端
public class Server extends JFrame{
	private static final long serialVersionUID=1L;
	private static final int port=8888;
	//并发集合，HashMap，键是客户端Id，值是客户端运行对象
	private static ConcurrentMap<Integer, ClientHander> clients = new ConcurrentHashMap<>();
	//设为全局的变量，让每次客户端退出都可以更新数量
	private static  JLabel clientsLabel = new JLabel("连接客户端数量: 0");
	private ServerSocket ClientSocket;

	public Server() {
		try {
		     ClientSocket = new ServerSocket(port);
			//查看客户端连接数的窗口
			JFrame frame = new JFrame("在线客服 - 服务端");
	        frame.getContentPane().add(clientsLabel, BorderLayout.CENTER);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(300, 100);
	        frame.setVisible(true);
	       //新建线程
	        new Thread(()->{
	        	while(true) {
	        	try {
	        		Socket socket=ClientSocket.accept();
	        		int ClientId=socket.getPort();
	        		ClientHander clienthander=new ClientHander(socket);
	        		//将客户端放入集合中
	        		clients.put(ClientId,clienthander);
	        		//获取集合的长度，确定客户端数量
	        		clientsLabel.setText("连接客户端数量"+clients.size());
	        		//创建窗口
	        		CreateUI( ClientId, clienthander);
	        		//客户端开始运行，进入线程中
	        		new Thread(clienthander).start();
	        	}catch(IOException e) {
	        		e.printStackTrace();
	        	}
	        }
	        }).start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//创建窗口,设置为静态，这样子就不会被实例化相关联，每次运行都会出现新的窗口
	public static void CreateUI(int ClientId,ClientHander clienthander) {
		
		//Swing中事件处理和绘画代码都在一个单独的线程中执行，这个线程就叫做事件分发线程。
		SwingUtilities.invokeLater(()->{
			JTextArea txt_area=new JTextArea();
			JTextField txt_field=new JTextField(40);
			//文本框事件
	    	txt_field.addActionListener(e->{
	    		String Message;
	    		Message=txt_field.getText();
	    		txt_area.append("你:"+Message+"\n");
	    		clienthander.SendMessage(Message);
	    		txt_field.setText("");
	    	});
			JButton btn_send=new  JButton("发送");
			//按钮事件
	    	btn_send.addActionListener(e->{
	    		String Message;
	    		Message=txt_field.getText();
	    		txt_area.append("你:"+Message+"\n");
	    		clienthander.SendMessage(Message);
	    		txt_field.setText("");
	    	});
	    	
			
			//frame设置为边界布局，设置存放在frame上center、south位置的panel
	        JPanel p_center=new JPanel();
	        txt_area.setEditable(false);
	        txt_area.setLineWrap(true);
	        p_center.add(txt_area);
	        
	        JPanel p_south=new JPanel();
	        p_south.add(txt_field);
	        p_south.add(btn_send);
	        
	        JFrame chatFrame=new JFrame("Client"+ClientId);
	        JScrollPane chatScrollPane = new JScrollPane(txt_area);
	        chatFrame.getContentPane().add(chatScrollPane, BorderLayout.CENTER);//使聊天框铺满
	        chatFrame.getContentPane().add(p_south, BorderLayout.SOUTH);
			
			
	        chatFrame.setSize(500, 300);
	        chatFrame.setLocationRelativeTo(null);
	        chatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//隐藏当前窗口
	    	//关闭窗口事件
	        chatFrame.addWindowListener(new WindowAdapter() {
	    		@Override
	    		public void windowClosed(WindowEvent e) {
	    			clients.remove(ClientId);
	    			clientsLabel.setText("连接客户端数量"+clients.size());
	            }
	    	});
	        chatFrame.setVisible(true);
	    	
	    	
	    	new Thread(()->{
				while(chatFrame.isVisible()) {
					try {
						String message;
						message=clienthander.GetMessage();
						if(message!=null) {
							txt_area.append("客户端"+ClientId+":"+message+"\n");
						}
					}catch(InterruptedException e) {
						 e.printStackTrace();
					}
				}
			}).start();
		});
	}
	public static void main(String[] args) {
		new Server();
	}
}
