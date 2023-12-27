package goods;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
public class WaresDAOimpl implements WaresDAO{
	
	//增加商品数据
	@Override
	public  boolean addWares(wares a) {
		Connection con=null;
		PreparedStatement pst=null;
		int nums=0;
		try {
			con=ConnectionDataBase.getConnection();
			String sql="insert into Commodity(Id,Name,Category,Price,Amount) values(?,?,?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1,a.GetId());
			pst.setString(2,a.GetName());
			pst.setString(3,a.GetCategory());
			pst.setString(4, a.GetPrice());
			pst.setString(5, a.GetAmount());
			nums=pst.executeUpdate();
			if(nums==1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"添加商品记录异常"+e.getMessage());
			return false;
		}finally {
			CloseDataBase.close(pst, con);
		}
	}
	
	//修改商品数据
	@Override
	public boolean UpdateWares(wares b) {
		Connection con=null;
		PreparedStatement pst=null;
		int nums=0;
		try {
			con=ConnectionDataBase.getConnection();
			String sql="update Commodity set Name=?,Category=?,Price=?,Amount=?where Id=? ";
			pst=con.prepareStatement(sql);
			pst.setString(1, b.GetName());
			pst.setString(2, b.GetCategory());
			pst.setString(3, b.GetPrice());
			pst.setString(4, b.GetAmount());
			pst.setString(5, b.GetId());
			nums=pst.executeUpdate();
			if(nums==1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"修改商品记录异常"+e.getMessage());
			return false;
		}finally {
			CloseDataBase.close(pst, con);
		}
	}
	
	//删除商品记录，根据Id;
	@Override
	public boolean DeleteWares(wares c) {
		Connection con=null;
		PreparedStatement pst=null;
		int nums=0;
		try {
			con=ConnectionDataBase.getConnection();
			String sql="delete from Commodity where Id=?";
			pst=con.prepareStatement(sql);
			pst.setString(1,c.GetId());
			nums=pst.executeUpdate();
			if(nums==1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"删除商品记录异常"+e.getMessage());
			return false;
		}finally {
			CloseDataBase.close(pst, con);
		}
	}
	
	//查询所有商品,并存入动态数组
	@Override
	public Vector<wares> SelectAll(){
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		Vector<wares> goods=new Vector<wares>();
		try {
			con=ConnectionDataBase.getConnection();
			String sql="select * from Commodity";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				wares s=new wares();
				s.SetId(rs.getString("Id"));
				s.SetName(rs.getString("Name"));
				s.SetCategory(rs.getString("Category"));
				s.SetPrice(rs.getString("Price"));
				s.SetAmount(rs.getString("Amount"));
				goods.add(s);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"查找所有商品记录异常"+e.getMessage());
		}finally {
			CloseDataBase.close(rs, stmt, con);
		}
		return goods;
	}
	
	//查询单个商品，通过Id;
	@Override
	public Vector<wares> SelectById(String id){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Vector<wares> goods=new Vector<wares>();
		try {
			con=ConnectionDataBase.getConnection();
			String sql="select * from Commodity where Id=?";
			pst=con.prepareStatement(sql);
			pst.setString(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				wares s=new wares();
				s.SetId(rs.getString("Id"));
				s.SetName(rs.getString("Name"));
				s.SetCategory(rs.getString("Category"));
				s.SetPrice(rs.getString("Price"));
				s.SetAmount(rs.getString("Amount"));
				goods.add(s);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"查找单个商品记录异常(Id)"+e.getMessage());
			
		}finally {
			CloseDataBase.close(rs, pst, con);
		}
		return goods;
	}
	
	//查询商品通过种类
	@Override
	public Vector<wares> SelectByCategory(String Category){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Vector<wares> goods=new Vector<wares>();
		try {
			con=ConnectionDataBase.getConnection();
			String sql="select * from Commodity where Category=?";
			pst=con.prepareStatement(sql);
			pst.setString(1, Category);
			rs=pst.executeQuery();
			while(rs.next()) {
				wares s=new wares();
				s.SetId(rs.getString("Id"));
				s.SetName(rs.getString("Name"));
				s.SetCategory(rs.getString("Category"));
				s.SetPrice(rs.getString("Price"));
				s.SetAmount(rs.getString("Amount"));
				goods.add(s);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"查找商品记录异常(种类)"+e.getMessage());
			
		}finally {
			CloseDataBase.close(rs, pst, con);
		}
		return goods;
	}
	//查询通过商品名字
	@Override
	public Vector<wares> SelectByName(String Name){
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Vector<wares> goods=new Vector<wares>();
		try {
			con=ConnectionDataBase.getConnection();
			String sql="select * from Commodity where Name=?";
			pst=con.prepareStatement(sql);
			pst.setString(1, Name);
			rs=pst.executeQuery();
			while(rs.next()) {
				wares s=new wares();
				s.SetId(rs.getString("Id"));
				s.SetName(rs.getString("Name"));
				s.SetCategory(rs.getString("Category"));
				s.SetPrice(rs.getString("Price"));
				s.SetAmount(rs.getString("Amount"));
				goods.add(s);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"查找商品记录异常(名字)"+e.getMessage());
			
		}finally {
			CloseDataBase.close(rs, pst, con);
		}
		return goods;
	}
}
