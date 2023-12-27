package goods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//添加页面
public class AddPage extends JFrame{
	//序列化，JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，
    //如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。
    //用来判断版本一致
	private static final long serialVersionUID=1L;
	//添加商品信息页面的窗体组件
	//标签
	private JLabel label1=new JLabel("商品编号:");
	private JLabel label2=new JLabel("商品名称:");
	private JLabel label3=new JLabel("商品类别:");
	private JLabel label4=new JLabel("商品售价:");
	private JLabel label5=new JLabel("商品数量:");
	//文本框
	private JTextField Text_Id=new JTextField(10);
	private JTextField Text_Name=new JTextField(10);
	private JTextField Text_Category=new JTextField(10);
	private JTextField Text_Price=new JTextField(10);
	private JTextField Text_Amount=new JTextField(10);
	//按钮
	private JButton Button1=new JButton("确定");
	private JButton Button2=new JButton("取消");
	
	//初始化界面
	public void InitUI() {
		//向容器添加控件
		JPanel P_Id=new JPanel();
		P_Id.add(label1);
		P_Id.add(Text_Id);
		JPanel P_Name=new JPanel();
		P_Name.add(label2);
		P_Name.add(Text_Name);
		JPanel P_Category= new JPanel();
		P_Category.add(label3);
		P_Category.add(Text_Category);
		JPanel P_Price=new JPanel();
		P_Price.add(label4);
		P_Price.add(Text_Price);
		JPanel P_Amount=new JPanel();
		P_Amount.add(label5);
		P_Amount.add(Text_Amount);
		JPanel P_Center=new JPanel();
		P_Center.add(P_Id);
		P_Center.add(P_Name);
		P_Center.add(P_Category);
		P_Center.add(P_Price);
		P_Center.add(P_Amount);
		JPanel P_South=new JPanel();
		P_South.add(Button1);
		P_South.add(Button2);
		
		//边界布局
		this.setLayout(new BorderLayout());
		this.setSize(300,300);
		this.add(P_Center,BorderLayout.CENTER);
		this.add(P_South,BorderLayout.SOUTH);
		this.setBounds(600,100,300,300);// 窗体坐标、长宽
		
		//标题
		this.setTitle("添加商品信息");
		//默认的关闭操作，也就是你的JFrame窗口的关闭按钮，点击它时，退出程序。
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//隐藏当前窗口
		//显示窗口
		this.setVisible(true);
	}
	
	//无参构造函数
	public AddPage() {
		InitUI();
		//确定按钮事件监听器
		Button1.addActionListener(new ActionListener() {
			//这是接口ActionListener里面定义的一个抽象方法，
			//所有实现这个接口的类都要重写这个方法。
			public void actionPerformed(ActionEvent e)  {
				String ID=Text_Id.getText();
				String Name=Text_Name.getText();
				String Category=Text_Category.getText();
				String Price=Text_Price.getText();
				String Amount=Text_Amount.getText();
				if(Amount.length()!=0&&Price.length()!=0&&Category.length()!=0&&Name.length()!=0&&ID.length()!=0&&ID!=null&&Name!=null&&Category!=null&&Price!=null&&Amount!=null) {
					//验证是否为八位数
					if(ID.matches("[\\d]{8}")) { 
						Manage manage=new Manage();
						wares s=new wares(ID,Name,Category,Price,Amount);
						manage.AddRecord(s);
						JOptionPane.showMessageDialog(null,"增加成功!");
						//关闭页面
						dispose();
					}else {
						JOptionPane.showMessageDialog(null,"商品编码必须为8位数");
					}
				}else {
					JOptionPane.showMessageDialog(null,"文本框不能为空!");
				}
			}
		});
		//取消按钮事件监听器
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
