package cn.redarmy.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.redarmy.service.Service.GoodService;

final class GetConnection {
	// static
	/**
	 * Uses DriverManager.
	 */
	static Connection getSimpleConnection() throws SQLException {
		Connection conn = null;
		String DB_CONN_STRING = "jdbc:mysql://localhost:3306/showstatus";
		String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
		String USER_NAME = "root";
		String PASSWORD = "P%mysql154";
		conn = DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);

		try {
			Class.forName(DRIVER_CLASS_NAME).newInstance();
		} catch (Exception ex) {
		}

		try {
			conn = DriverManager.getConnection(DB_CONN_STRING, USER_NAME,
					PASSWORD);
		} catch (SQLException e) {
		}
		return conn;
	}

	@SuppressWarnings("unused")
	private static void log(Object aObject) {
	}
}

public class GoodServiceImpl implements GoodService {

	public String dateToString(Date data, String formatType) {
		return new SimpleDateFormat(formatType).format(data);
	}

	private String SecondsToDate(long lSeconds) {
		Date dateOld = new Date(lSeconds); // 根据long类型的毫秒数生命一个date类型的时间
		String sDateTime = dateToString(dateOld, "yyyy-MM-dd HH:mm:ss"); // 把date类型的时间转换为string
		return sDateTime;
	}

	@Override
	public int LogIn(String strPersonName, String strPhoneNum, String strMac) {
		int nRet = 0;
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = GetConnection.getSimpleConnection();
			stmt = connect.createStatement();
			boolean bPasswordFind = false;

			// 先判断要注册的账号是否存在
			String strSQL = "select Account from customer_info_table where MacAddress = '";
			strSQL += strMac;
			strSQL += "'";
			rs = stmt.executeQuery(strSQL);
			while (rs.next()) {
				bPasswordFind = true;
				break;
			}
			// 如果mac存在
			if (bPasswordFind) {
				nRet = 2;
			} else if (!bPasswordFind) {// 如果mac不存在,那么就注册该账号
				strSQL = "insert into customer_info_table values('";
				strSQL += strPersonName;
				strSQL += "','";
				strSQL += strPhoneNum;
				strSQL += "','";
				strSQL += strMac;
				strSQL += "')";
				stmt.execute(strSQL);
				nRet = 1;
			}

		} catch (Exception e) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return nRet;
	}

	@Override
	public long GetSameStatusPersonNum(String strPersonName, String strContent,
			long lSeconds, double dLongitude, double dLatitude, int nType) {

		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		long lPersonNum = 0;
		try {
			connect = GetConnection.getSimpleConnection();
			stmt = connect.createStatement();

			// 获取系统当前时间
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String strCurrentTime = formatter.format(curDate);
			Date d1 = formatter.parse(strCurrentTime);
			long diff = d1.getTime() - lSeconds * 1000;// 这样得到的差值是毫秒级别
			// 根据diff换算成yyyy年MM月dd日HH:mm:ss格式
			String strStartTime = SecondsToDate(diff);
			// 计算这个时间段内的数据
			// 先判断要注册的账号是否存在
			String strSQL = "select count(*) from status_table where AnncounceTime >= '";
			strSQL += strStartTime;
			strSQL += "' and AnncounceTime <= '";
			strSQL += strCurrentTime;
			strSQL += "' and StatusContent = '";
			strSQL += strContent;
			strSQL += "'";
			rs = stmt.executeQuery(strSQL);
			long lNumTemp = 0;
			while (rs.next()) {
				lNumTemp = rs.getInt(1);
			}
			if (2 == nType) {
				strSQL = "insert into status_table values('";
				strSQL += strPersonName;
				strSQL += "','";
				strSQL += strCurrentTime;
				strSQL += "','";
				strSQL += strContent;
				strSQL += "','";
				strSQL += dLongitude;
				strSQL += "','";
				strSQL += dLatitude;
				strSQL += "')";
				stmt.execute(strSQL);
			}

			lPersonNum = lNumTemp;
		} catch (Exception e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lPersonNum;
	}

	@Override
	public long GetIsSearchingPersonNum(String strPersonName,
			String strContent, long lSeconds, double dLongitude,
			double dLatitude, int nType) {
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		long lPersonNum = 0;
		try {
			connect = GetConnection.getSimpleConnection();
			stmt = connect.createStatement();

			// 获取系统当前时间
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String strCurrentTime = formatter.format(curDate);
			Date d1 = formatter.parse(strCurrentTime);
			long diff = d1.getTime() - lSeconds * 1000;// 这样得到的差值是毫秒级别
			// 根据diff换算成yyyy年MM月dd日HH:mm:ss格式
			String strStartTime = SecondsToDate(diff);
			// 计算这个时间段内的数据
			// 先判断要注册的账号是否存在
			String strSQL = "select count(*) from person_searchstatus_table where AnncounceTime >= '";
			strSQL += strStartTime;
			strSQL += "' and AnncounceTime <= '";
			strSQL += strCurrentTime;
			strSQL += "' and StatusContent = '";
			strSQL += strContent;
			strSQL += "'";
			rs = stmt.executeQuery(strSQL);
			long lNumTemp = 0;
			while (rs.next()) {
				lNumTemp = rs.getInt(1);
			}

			if (2 == nType) {
				strSQL = "insert into person_searchstatus_table values('";
				strSQL += strPersonName;
				strSQL += "','";
				strSQL += strCurrentTime;
				strSQL += "','";
				strSQL += strContent;
				strSQL += "','";
				strSQL += dLongitude;
				strSQL += "','";
				strSQL += dLatitude;
				strSQL += "')";
				stmt.execute(strSQL);
			}

			lPersonNum = lNumTemp;
		} catch (Exception e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lPersonNum;
	}

	@Override
	public int InsertStatus(String strPersonName, String strContent,
			double dLongitude, double dLatitude) {
		int nRet = 0;
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = GetConnection.getSimpleConnection();
			stmt = connect.createStatement();

			// 获取系统当前时间
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String strCurrentTime = formatter.format(curDate);

			String strSQL = "insert into status_table values('";
			strSQL += strPersonName;
			strSQL += "','";
			strSQL += strCurrentTime;
			strSQL += "','";
			strSQL += strContent;
			strSQL += "','";
			strSQL += dLongitude;
			strSQL += "','";
			strSQL += dLatitude;
			strSQL += "')";
			stmt.execute(strSQL);
			nRet = 1;
		} catch (Exception e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return nRet;
	}

	@Override
	public int InsertSearchRecord(String strPersonName, String strContent,
			double dLongitude, double dLatitude) {
		int nRet = 0;
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = GetConnection.getSimpleConnection();
			stmt = connect.createStatement();

			// 获取系统当前时间
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String strCurrentTime = formatter.format(curDate);

			String strSQL = "insert into person_searchstatus_table values('";
			strSQL += strPersonName;
			strSQL += "','";
			strSQL += strCurrentTime;
			strSQL += "','";
			strSQL += strContent;
			strSQL += "','";
			strSQL += dLongitude;
			strSQL += "','";
			strSQL += dLatitude;
			strSQL += "')";
			stmt.execute(strSQL);
			nRet = 1;
		} catch (Exception e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return nRet;
	}

	@Override
	public String GetServiceTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String strCurrentTime = formatter.format(curDate);
		return strCurrentTime;
	}

	@Override
	public String GetAccountName(String strMac) {
		String strAccountName = "";
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = GetConnection.getSimpleConnection();
			stmt = connect.createStatement();
			
			String strSQL = "select Account from customer_info_table where MacAddress = '";
			strSQL += strMac;
			strSQL += "'";
			rs = stmt.executeQuery(strSQL);
			while (rs.next()) {
				strAccountName = rs.getString(1);
				break;
			}
		} catch (Exception e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return strAccountName;
	}

	@Override
	public int GetUsingPersonNum() {
		int nPersonNum = 0;
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = GetConnection.getSimpleConnection();
			stmt = connect.createStatement();
			
			String strSQL = "select count(*) from customer_info_table";
			rs = stmt.executeQuery(strSQL);
			while (rs.next()) {
				nPersonNum = rs.getInt(1);
				break;
			}
		} catch (Exception e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return nPersonNum;
	}

}
