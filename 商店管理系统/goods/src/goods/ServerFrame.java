package MarketSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ServerFrame extends JFrame {
	private JTextArea txt_area;
	private JTextField txt_field;
	private JButton btn_send;

    private ServerSocket serverSocket;
    private BufferedReader input;
    private PrintWriter output;
    
    public ServerFrame() {
		this.setTitle("客服小叶");
    	init();
    	this.setSize(500, 300);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
	}
    
    public void init() {
        //frame设置为边界布局，设置存放在frame上center、south位置的panel
        JPanel p_center=new JPanel();
        txt_area=new JTextArea();
        txt_area.setEditable(false);
        txt_area.setLineWrap(true);
        p_center.add(txt_area);
        
        JPanel p_south=new JPanel();
        txt_field=new JTextField(40);
        //文本框监听器
        txt_field.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txt_area.append("客服小叶："+txt_field.getText()+"\n");
                sendMessage(txt_field.getText());//调用sendMessage函数
                txt_field.setText("");//清空发送框
            }
        });
        
        btn_send=new JButton("发送");
       //发送按钮监听器
        btn_send.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txt_area.append("客服小叶："+txt_field.getText()+"\n");
                sendMessage(txt_field.getText());//调用sendMessage函数
                txt_field.setText("");//清空发送框
            }
        });
        p_south.add(txt_field);
        p_south.add(btn_send);
        
        this.setLayout(new BorderLayout());
		this.add(BorderLayout.SOUTH,p_south);
		this.add(new JScrollPane(txt_area), BorderLayout.CENTER);//使聊天框铺满
	}
    //发送信息
    public void sendMessage(String message) {
	    if (output != null) {
	        output.println(message);
	        output.flush();
	    }
	}
    //选择监听端口
    public void Server(int port) {
	    try {
	        serverSocket = new ServerSocket(port);
	        System.out.println("服务端已启动，监听端口：" + port);
	        //接收来自客户端的Socket
	        Socket clientSocket = serverSocket.accept();
	        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println("客户端已连接!");
            startAccepting();//调用startAccepting()函数
	    } catch (IOException e) {
	        System.err.println("无法监听指定的端口：" + port);
	        e.printStackTrace();
	    }
	}
    //开始多线程
    public void startAccepting() {
	    new Thread() {
	        public void run() {
	            try {
	            	String line;
	                while ((line = input.readLine()) != null) {//一行一行读取，输入不为空
	                	txt_area.append("小杨："+line+"\n");//输入到聊天框中
		                txt_area.setCaretPosition(txt_area.getDocument().getLength());	
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }finally {
					try {
						serverSocket.close();
						System.out.println("客户端已断开连接！");
						} catch (IOException e) {
							e.printStackTrace();
							}
					}
	        }
	    }.start();
	}
 
    
    public static void main(String[] args) {
    	ServerFrame server = new ServerFrame();
    	server.Server(8888);
    }
}
