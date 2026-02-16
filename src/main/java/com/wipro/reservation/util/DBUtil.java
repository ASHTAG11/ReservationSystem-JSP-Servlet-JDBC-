package com.wipro.reservation.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getDBConnection() {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/reservation_db?useSSL=false&serverTimezone=UTC",
                "root",
                "2006"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
