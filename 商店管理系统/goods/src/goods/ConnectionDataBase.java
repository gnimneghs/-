package goods;
import java.sql.*;
//连接数据库
public class ConnectionDataBase {
	private static String url="jdbc:mysql://localhost/Stroes?useUnicode=true&characterEncoding=UTF-8";  
    private  static String UserName="root";
    private static String UserPassword="yzd1234567890";
	public static Connection getConnection() {
    	try {
    		return DriverManager.getConnection(url,UserName,UserPassword);
    	}catch(Exception e) {
    		System.err.println("数据库建立连接异常；");
    		e.printStackTrace();
    		return null;
    	}
    }
}