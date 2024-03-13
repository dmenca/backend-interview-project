package com.dmenca.java.basic.mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class MySQLDataMock1 {
    // 数据库连接参数
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://10.111.32.80:10208/test";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmtGroup = null;
        PreparedStatement stmtResource = null;

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 插入 info_video_resource_group 表数据
//            String insertGroupSQL = "INSERT INTO info_video_resource_group (serial, organ_id, name, group_type) VALUES (?, ?, ?, ?)";
//            stmtGroup = conn.prepareStatement(insertGroupSQL);
//
//            Random random = new Random();
//            for (int i = 1; i <= 10000; i++) {
//                stmtGroup.setString(1, "serial_" + i);
//                stmtGroup.setInt(2, random.nextInt(100)); // 随机生成 organ_id
//                stmtGroup.setString(3, "Group " + i);
//                stmtGroup.setInt(4, random.nextInt(2)); // 随机生成 group_type
//                stmtGroup.addBatch();
//                if (i % 100 == 0) {
//                    stmtGroup.executeBatch();
//                    stmtGroup.clearBatch();
//                }
//            }

            Random random = new Random();
            // 插入 info_video_resource 表数据
            String insertResourceSQL = "INSERT INTO info_video_resource_1 (serial, name, group_id) VALUES (?, ?, ?)";
            stmtResource = conn.prepareStatement(insertResourceSQL);

            for (int i = 1; i <= 1000000; i++) {
                stmtResource.setString(1, "serial_" + i);
                stmtResource.setString(2, "Resource " + i);
                stmtResource.setInt(3, random.nextInt(10000) + 1); // 随机选择一个 group_id
                stmtResource.addBatch();
                if (i % 1000 == 0) {
                    stmtResource.executeBatch();
                    stmtResource.clearBatch();
                }
            }
            stmtResource.executeBatch();

            System.out.println("Data inserted successfully.");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmtGroup != null) stmtGroup.close();
                if (stmtResource != null) stmtResource.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
