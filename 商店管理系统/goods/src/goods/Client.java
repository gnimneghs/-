package goods;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
public class Client extends JFrame {
	private static final long serialVersionUID=1L;
	private static final String Host= "YYDS";
    private static final int PORT = 8888;
    private Socket ClientSocket;
    private BufferedReader br;
    private PrintWriter pw;
    
    private JTextArea txt_area;
	private JTextField txt_field;
	private JButton btn_send;
    
    public Client() {
    	try {
    	this.ClientSocket=new Socket(Host,PORT);
    	this.br=new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
    	this.pw=new PrintWriter(ClientSocket.getOutputStream(),true);
    	InitUI();
    	//设置按钮事件，点击触发
    	btn_send.addActionListener(e->{
    		String Message;
    		Message=txt_field.getText();
    		txt_area.append("你:"+Message+"\n");
    		pw.println(Message);
    		txt_field.setText("");
    	});
    	//设置文本框事件，Enter触发
    	txt_field.addActionListener(e->{
    		String Message;
    		Message=txt_field.getText();
    		txt_area.append("你:"+Message+"\n");
    		pw.println(Message);
    		txt_field.setText("");
    	});
    	//新线程
    	new Thread(()->{
    		try {
    			String ServerMessage;
    			while((ServerMessage=br.readLine())!=null) {
    				txt_area.append("服务端:"+ServerMessage+"\n");
    			}
    		}catch(IOException e) {
    			e.printStackTrace();
    		}finally {
    			try {
    				ClientSocket.close();
    			}catch(IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}).start();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    public void InitUI() {
    	//frame设置为边界布局，设置存放在frame上center、south位置的panel
        JPanel p_center=new JPanel();
        txt_area=new JTextArea();
        txt_area.setEditable(false);
        txt_area.setLineWrap(true);
        p_center.add(txt_area);
        
        JPanel p_south=new JPanel();
        txt_field=new JTextField(40);
        
        btn_send=new JButton("发送");
        p_south.add(txt_field);
        p_south.add(btn_send);
        
        this.setLayout(new BorderLayout());
		this.add(BorderLayout.SOUTH,p_south);
		this.add(new JScrollPane(txt_area), BorderLayout.CENTER);//使聊天框铺满
		
		this.setTitle("客户端");
		this.setSize(500, 300);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//隐藏当前窗口
    	this.setVisible(true);
    }
    public static void main(String[] args) {
    	new Client();
    }
}
