package goods;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
//import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Connection;

class LoginFrame extends JFrame {
	private static final long serialVersionUID=1L;
	//登入界面的窗体组件
    private JLabel label1=new JLabel("商店管理系统");
    private JLabel label2=new JLabel("用户名：");
    private JLabel label3=new JLabel("密码：");
    private JTextField txt_name=new JTextField(16);
    private JPasswordField txt_pwd=new JPasswordField(16);//密码文本框
    private JButton btn1=new JButton("确定");
    private JButton btn2=new JButton("取消");
    //private String[]yg= {"用户","管理员"};//数组
    //private JComboBox<String>cbb=new JComboBox<>(yg);//组合框

    
    public LoginFrame() {
    	this.setTitle("商店管理系统");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	init();
    	this.setSize(300, 200);
    	this.setVisible(true);
    	//登录事件监听器
    	btn1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
                String username = txt_name.getText();
                String password = String.valueOf(txt_pwd.getPassword());
                // 实现登录验证逻辑，如果用户名和密码正确，则打开另一个界面
                if (username.equals("aa") && password.equals("123456")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录成功！");
                    new MarketFrame();//打开主页面
                    dispose();//关闭当前页面
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "用户名或密码错误！");
                }
            }
    	});
    	//取消按钮监听器
    	btn2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();//关闭当前页面
    		}
    	});
    }
    //初始化方法
    private void init() {
		//密码设置不可见
		txt_pwd.setEchoChar('*');
		//frame设置为边界布局，设置存放在frame上north、center、south位置的panel
		JPanel p_north=new JPanel();
		p_north.add(label1);
		
		JPanel p_name=new JPanel();
		p_name.add(label2);
		p_name.add(txt_name);
		JPanel p_pwd=new JPanel();
		p_pwd.add(label3);
		p_pwd.add(txt_pwd);
		JPanel p_center=new JPanel();
		p_center.add(p_name);
		p_center.add(p_pwd);
		//p_center.add(cbb);
		
		JPanel p_south=new JPanel();
		p_south.add(btn1);
		p_south.add(btn2);
		
		this.setLayout(new BorderLayout());
		this.add(p_north,BorderLayout.NORTH);
		this.add(p_center,BorderLayout.CENTER);
		this.add(p_south,BorderLayout.SOUTH);
    }
    
    
}

public class YM {
	public static void main(String[]args) {
		new LoginFrame();
	}
}

