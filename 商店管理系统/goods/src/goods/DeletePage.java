package goods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
//删除页面
public class DeletePage extends JFrame{
	//序列化
	private static final long serialVersionUID=1L;
	private wares a=new wares();
	private Vector <wares> s=new Vector<wares>();
	private Manage manage=new Manage();	
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
	private JButton Button1=new JButton("删除");
	private JButton Button2=new JButton("取消");
	
	public DeletePage() {
		InitUI();
		//标题
		this.setTitle("删除商品信息");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//隐藏当前窗口
		//显示窗口
		this.setVisible(true);
	}
	
	//初始化页面
	public void InitUI() {
		//将文本框背景调节成为亮灰，并使其不可编辑
		//setEditable（）只能在方式里用
		Text_Id.setBackground(Color.LIGHT_GRAY);
		Text_Name.setBackground(Color.LIGHT_GRAY);
		Text_Category.setBackground(Color.LIGHT_GRAY);
		Text_Price.setBackground(Color.LIGHT_GRAY);
		Text_Amount.setBackground(Color.LIGHT_GRAY);
		Text_Id.setEditable(false);
		Text_Name.setEditable(false);
		Text_Category.setEditable(false);
		Text_Price.setEditable(false);
		Text_Amount.setEditable(false);
		
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

		//取消按钮事件监听器
		Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	//通过商品编号删除
	public void DeletePageId(String id) {
		s=manage.FindId(id);
		a=s.get(0);
		Text_Id.setText(a.GetId());
		Text_Name.setText(a.GetName());
		Text_Category.setText(a.GetCategory());
		Text_Price.setText(a.GetPrice());
		Text_Amount.setText(a.GetAmount());

		//确定按钮事件监听器
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s=manage.FindId(id);
				//因为我们的id是唯一的，所以直接得到一个就行
				a=s.get(0);
				manage.DeleteRecord(a);
				JOptionPane.showMessageDialog(null,"删除成功！");
				dispose();
			}
		});
	}
	//通过商品名称删除
	public void DeletePageName(String name) {		
		s=manage.FindName(name);
		a=s.get(0);
		Text_Id.setText(a.GetId());
		Text_Name.setText(a.GetName());
		Text_Category.setText(a.GetCategory());
		Text_Price.setText(a.GetPrice());
		Text_Amount.setText(a.GetAmount());
		//确定按钮事件监听器
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s=manage.FindName(name);
				a=s.get(0);
				manage.DeleteRecord(a);
				JOptionPane.showMessageDialog(null,"删除成功！");
				dispose();
			}
		});
	}
	//通过商品种类删除
	public void DeletePageCategory(String category) {		
		s=manage.FindCategory(category);
		a=s.get(0);
		Text_Id.setText(a.GetId());
		Text_Name.setText(a.GetName());
		Text_Category.setText(a.GetCategory());
		Text_Price.setText(a.GetPrice());
		Text_Amount.setText(a.GetAmount());
		//确定按钮事件监听器
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s=manage.FindCategory(category);
				a=s.get(0);
				manage.DeleteRecord(a);
				JOptionPane.showMessageDialog(null,"删除成功！");
				dispose();
			}
		});
	}
	
}

