

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xutao
 * @email xutao199218@163.com
 * @since 2016-01-13
 * @version 1.0
 */

public class JDBCDao {

	public JDBCDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建数据库连接
	 * */
	public static Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://172.16.18.19:3306/data_collaboration_platform?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String password = "123456";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection create failed");
		}
		return conn;
	}

	/**
	 * 插入数据库
	 * */
	public static void insertConfig(){
		
	}
	
	
	/**
	 * 获取参数列表
	 * */
	public static Param selectParam() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Param param = null;
		
		final String configQuery = "select conf_type, id, cmd_type, create_time, conf_content from UKey where status = ?";
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(configQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int time = timeStringToInt(rs.getString("create_time"));
				String confType = rs.getString("conf_type");
				String cmdType = rs.getString("cmd_type");
				String confContent = rs.getString("conf_content");
				
				//printResultSetInfo(rs);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return param;
	}

	public static void setStatus(int id) {
		Connection conn = null;
		final String statusSet = "update conf_t set status=? where id=?";
		final int sendStatus = 1;

		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(statusSet);
			ps.setInt(1, sendStatus);
			ps.setInt(2, id);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("statue updated with id=" + id);
			} else {
				System.out.println("No statue found with id=" + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * 将字符串时间戳截断为int
	 * */
	private static int timeStringToInt(String user_time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int stamp = 0;
		try {
			Date date = sdf.parse(user_time);
			stamp = (int) (date.getTime() / 1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stamp;
	}

	/**
	 * 打印rs的结果，用于调试
	 * */
	@SuppressWarnings("unused")
	private static void printResultSetInfo(ResultSet rs) {
		if (rs != null) {
			try {
				System.out.println(timeStringToInt(rs.getString("create_time"))
						+ " " + rs.getString("conf_type") + " "
						+ rs.getInt("id") + " " + rs.getString("cmd_type")
						+ " " + rs.getString("conf_content"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * */
	public static void main(String[] args) {
		//getParam(); 
	}
	 

}
