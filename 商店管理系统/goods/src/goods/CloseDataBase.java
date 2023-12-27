package goods;
import java.sql.*;
import javax.swing.JOptionPane;
//关闭数据库
public class CloseDataBase {
	public static void close(ResultSet rs, Statement st, Connection con) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(st!=null) {
				st.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(SQLException e) {
			//跳出窗口提示错误
			JOptionPane.showMessageDialog(null,"关闭数据库异常"+e.getMessage());
		}
	}
	public static void close(ResultSet rs, PreparedStatement st, Connection con) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(st!=null) {
				st.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"关闭数据库异常"+e.getMessage());
		}
	}
	public static void close( Statement st, Connection con) {
		try {
			if(st!=null) {
				st.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(SQLException e) {
			//跳出窗口提示错误
			JOptionPane.showMessageDialog(null,"关闭数据库异常"+e.getMessage());
		}
	}
	public static void close( PreparedStatement st, Connection con) {
		try {
			if(st!=null) {
				st.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(SQLException e) {
			//跳出窗口提示错误
			JOptionPane.showMessageDialog(null,"关闭数据库异常"+e.getMessage());
		}
	}
}
