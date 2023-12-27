package goods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Vector;

class MarketFrame extends JFrame{
	private static final long serialVersionUID=1L;
	private Manage service=new Manage();
	private wares ww=new wares();//实体类
	private Vector<wares> goods=new Vector<wares>();
	//标签
	private JLabel lab_id=new JLabel("商品编号：");
	private JLabel lab_name=new JLabel("名称：");
	private JLabel lab_category=new JLabel("类别：");
	private JLabel lab_price=new JLabel("售价：");
	private JLabel lab_amount=new JLabel("数量：");
	//文本框
	private JTextField tf_id=new JTextField(8);
	private JTextField tf_name=new JTextField(10);
	private JTextField tf_category=new JTextField(8);
	private JTextField tf_price=new JTextField(8);
	private JTextField tf_amount=new JTextField(8);	
	
	private JLabel lab_choose=new JLabel("请选择查找方式：");
	private JLabel lab_input=new JLabel("请输入：");
	private String[]yg= {"商品编号","名称","类别"};//查找方式数组
    private JComboBox<String>cbb=new JComboBox<>(yg);//组合框
    private JTextField tf_input=new JTextField(10);
	//按钮
	private JButton btn_find=new JButton("查找");
	private JButton btn_add=new JButton("添加");
	private JButton btn_update=new JButton("修改");
	private JButton btn_delete=new JButton("删除");
	
	private JLabel lab_browse=new JLabel("========  浏览区  ========");
	//private JLabel lab_total=new JLabel();//记录总数标签
	private JLabel lab_title=new JLabel("———商品编号———名称————类别——售价——数量————");
	private JList<wares>list;//列表框
	private JScrollPane scrollPane;//含列表框的滚动窗格
	private JButton btn_Getdatas=new JButton("浏览");
	private JButton btn_ReGetdatas=new JButton("刷新");
	private JButton btn_chat=new JButton("客服");
	private JPanel p_up=new JPanel();//上部面板
	private GridLayout gridLay=new GridLayout(6,1);//按6行1列布局
	private JPanel p_up1=new JPanel();
	private JPanel p_up2=new JPanel();
	private JPanel p_up3=new JPanel();
	private JPanel p_up4=new JPanel();
	private JPanel p_up5=new JPanel();
	private JPanel p_up6=new JPanel();
	
	private JPanel p_down=new JPanel();//下部面板
	private BorderLayout borderLay=new BorderLayout();//按边框布局
	private JPanel p_downSouth=new JPanel();
	
	public MarketFrame(){
		this.setTitle("系统主页");
		this.setBounds(100, 100, 540, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
	}
	
	private void init() {
		tf_id.setEditable(false);
		tf_name.setEditable(false);
		tf_category.setEditable(false);
		tf_price.setEditable(false);
		tf_amount.setEditable(false);
		p_up1.add(lab_id);
		p_up1.add(tf_id);
		p_up2.add(lab_name);
		p_up2.add(tf_name);
		p_up2.add(lab_category);
		p_up2.add(tf_category);
		p_up3.add(lab_price);
		p_up3.add(tf_price);
		p_up3.add(lab_amount);
		p_up3.add(tf_amount);
		p_up4.setBackground(Color.LIGHT_GRAY);
		p_up4.add(lab_choose);
		p_up4.add(cbb);
		p_up5.setBackground(Color.WHITE);
		p_up5.add(lab_input);
		p_up5.add(tf_input);
		p_up5.add(btn_find);
		p_up5.add(btn_add);
		p_up5.add(btn_update);
		p_up5.add(btn_delete);
		
		//lab_total.setVisible(false);
		lab_browse.setForeground(Color.BLACK);
		//lab_total.setForeground(Color.BLUE);
		p_up6.setBackground(Color.ORANGE);
		p_up6.add(lab_browse);
		//p_up6.add(lab_total);
		p_up.setLayout(gridLay);//上部面板设6行1列网格布局
		p_up.add(p_up1);
		p_up.add(p_up2);
		p_up.add(p_up3);
		p_up.add(p_up4);
		p_up.add(p_up5);
		p_up.add(p_up6);
		
		p_down.setLayout(borderLay);
		p_down.add(lab_title,BorderLayout.NORTH);
		p_downSouth.setBackground(Color.LIGHT_GRAY);
		p_downSouth.add(btn_Getdatas);
		p_downSouth.add(btn_ReGetdatas);
		p_downSouth.add(btn_chat);
		p_down.add(p_downSouth,BorderLayout.SOUTH);
		
		list=new JList<wares>(Manage.Getdatas());//构建列表  显示所有商品 ReGetdatas()
		goods=Manage.Getdatas();
		ww=goods.get(0);
		this.displayRecord(ww);//显示第一条记录
		scrollPane=new JScrollPane(list);//构建滚动窗格
		scrollPane.setViewportView(list);//设置滚动窗格视图
		p_down.add(scrollPane,BorderLayout.CENTER);//下部面板放置滚动窗格
		//scrollPane.setVisible(false);//暂时不显示滚动窗格
		this.setLayout(new GridLayout(2,1));
		this.add(p_up);
		this.add(p_down);
		addEventHandler();
		addEventList();
	}
	//JList事件监听器
	private void addEventList() {
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ww=list.getSelectedValue();//获取选择的一条记录
				displayRecord(ww);//显示对应的信息
			}
    	});
	}
	//按钮事件监听器
	private void addEventHandler() {
		//“查找”按钮
		btn_find.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(cbb.getSelectedIndex()==0) {//查找方式选择“商品编号”
    				String gid=tf_input.getText().trim();
					if(gid.length()==0||!gid.matches("[\\d]{8}")) {
						JOptionPane.showMessageDialog(null, "请输入商品编号再查找!\n商品编号必须是8位数字");
						tf_input.requestFocus();
					}
					else if(service.FindId(gid).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的商品编号!");
					}
					else {
						list=new JList<wares>(service.FindId(gid));
						scrollPane.setViewportView(list);//设置滚动窗格视图
						scrollPane.setVisible(true);
					}
					goods=service.FindId(gid);
					ww=goods.get(0);
					displayRecord(ww);//调用displayRecord函数显示商品信息，显示第一条记录
					addEventList();
    			}
    			else if(cbb.getSelectedIndex()==1) {//查找方式为“名称”
    				String gname=tf_input.getText().trim();
    				if(gname.length()==0||gname==null) {
						JOptionPane.showMessageDialog(null, "请输入名称再查找!");
						tf_input.requestFocus();
					}
    				else if(service.FindName(gname).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的名称!");
					}
					else {
						list=new JList<wares>(service.FindName(gname));
						scrollPane.setViewportView(list);
						scrollPane.setVisible(true);
					}
    				goods=service.FindName(gname);
					ww=goods.get(0);
					displayRecord(ww);//调用displayRecord函数显示商品信息
					addEventList();
    			}
    			else {//查找方式为“类别”
    				String gcategory=tf_input.getText().trim();
    				if(gcategory.length()==0||gcategory==null) {
						JOptionPane.showMessageDialog(null, "请输入类别再查找!");
						tf_input.requestFocus();
					}
    				else if(service.FindCategory(gcategory).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的类别!");
					}
					else {
						list=new JList<wares>(service.FindCategory(gcategory));
						scrollPane.setViewportView(list);
						scrollPane.setVisible(true);
					}
    				goods=service.FindCategory(gcategory);
					ww=goods.get(0);
					displayRecord(ww);//调用displayRecord函数显示商品信息
					addEventList();
    			}
    			
    			
    		}
    	});
		//“添加”按钮
		btn_add.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			new AddPage();
    		}
    	});
		//“修改”按钮
		btn_update.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(cbb.getSelectedIndex()==0) {//选择“商品编号”
    				String gid=tf_input.getText().trim();
					if(gid.length()==0||!gid.matches("[\\d]{8}")) {
						JOptionPane.showMessageDialog(null, "请输入商品编号再删除!\n商品编号必须是8位数字");
						tf_input.requestFocus();
					}
					else if(service.FindId(gid).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的商品编号!");
					}
					else {
		    			goods=service.FindId(gid);
						ww=goods.get(0);
						new UpdatePage().UpdatePageId(ww);
					}
    			}
    			else if(cbb.getSelectedIndex()==1) {//选择“名称”
    				String gname=tf_input.getText().trim();
    				if(gname.length()==0||gname==null) {
						JOptionPane.showMessageDialog(null, "请输入名称再修改!");
						tf_input.requestFocus();
					}
    				else if(service.FindName(gname).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的名称!");
					}
					else {
						goods=service.FindName(gname);
						ww=goods.get(0);
						new UpdatePage().UpdatePageId(ww);
					}
    			}
    			else {//选择“类别”
    				String gcategory=tf_input.getText().trim();
    				if(gcategory.length()==0||gcategory==null) {
						JOptionPane.showMessageDialog(null, "请输入类别再修改!");
						tf_input.requestFocus();
					}
    				else if(service.FindCategory(gcategory).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的类别!");
					}
					else {
						goods=service.FindCategory(gcategory);
						ww=goods.get(0);
						new UpdatePage().UpdatePageId(ww);
					}
    			}
    		}
    	});
		//“删除”按钮
		btn_delete.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(cbb.getSelectedIndex()==0) {//选择“商品编号”
    				String gid=tf_input.getText().trim();
					if(gid.length()==0||!gid.matches("[\\d]{8}")) {
						JOptionPane.showMessageDialog(null, "请输入商品编号再删除!\n商品编号必须是8位数字");
						tf_input.requestFocus();
					}
					else if(service.FindId(gid).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的商品编号!");
					}
					else {
						new DeletePage().DeletePageId(gid);
					}
    			}
    			else if(cbb.getSelectedIndex()==1) {//选择“名称”
    				String gname=tf_input.getText().trim();
    				if(gname.length()==0||gname==null) {
						JOptionPane.showMessageDialog(null, "请输入名称再删除!");
						tf_input.requestFocus();
					}
    				else if(service.FindName(gname).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的名称!");
					}
					else {
						new DeletePage().DeletePageName(gname);
					}
    			}
    			else {//选择“类别”
    				String gcategory=tf_input.getText().trim();
    				if(gcategory.length()==0||gcategory==null) {
						JOptionPane.showMessageDialog(null, "请输入类别再删除!");
						tf_input.requestFocus();
					}
    				else if(service.FindCategory(gcategory).size()==0) {
						JOptionPane.showMessageDialog(null, "不存在该商品!\n请输入正确的类别!");
					}
					else {
						new DeletePage().DeletePageCategory(gcategory);
					}
    			}
			}	
    	});
		//“浏览”按钮
		btn_Getdatas.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			list=new JList<wares>(Manage.Getdatas());
    			scrollPane.setViewportView(list);
				scrollPane.setVisible(true);
				addEventList();
    		}
    	});
		//“刷新”按钮
		btn_ReGetdatas.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			list=new JList<wares>(Manage.ReGetdatas());
    			scrollPane.setViewportView(list);
				scrollPane.setVisible(true);
				addEventList();
    		}
    	});
		//“客服”按钮
		btn_chat.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//new ClientFrame().connect("localhost", 8888);
    			new Server();
    		}
    	});
	}
	//再文本框中显示相应的信息
	public void displayRecord(wares ww) {
		if(ww!=null) {
			tf_id.setText(ww.GetId());
			tf_name.setText(ww.GetName());
			tf_category.setText(ww.GetCategory());
			tf_price.setText(ww.GetPrice());
			tf_amount.setText(ww.GetAmount());
		}
		else {
			tf_id.setText(null);
			tf_name.setText(null);
			tf_category.setText(null);
			tf_price.setText(null);
			tf_amount.setText(null);
		}
	}
	
}
public class YM2 {
	public static void main(String[]args) {
		new MarketFrame();
	}
}

