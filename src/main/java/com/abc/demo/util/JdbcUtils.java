package com.abc.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {
    
    /**
     * 取得Oracle資料庫 host=192.168.0.1, port=1521, sid=mydb 的連線物件
     *
     * @return 連線物件
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        String url = "jdbc:oracle:thin:@192.168.0.1:1521:mydb";
        String user = "admin";
        String password = "12345";

        return DriverManager.getConnection(url, user, password);
    }
    
    public static boolean hasConnection() {
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("select 1 as num from dual");
            rs = ps.executeQuery();
            
            if(rs.next()) {
                int num = rs.getInt("num");
                return num == 1;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {/* Ignored */}
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {/* Ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {/* Ignored */}
            }

        }
        return false;
    }

}
