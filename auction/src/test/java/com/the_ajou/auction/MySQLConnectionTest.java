package com.the_ajou.auction;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sys?useSSL=false";
    private static final String USER = "root";
    private static final String PW = "rlagusqls1!";

    @Test
    public void testConnection() throws Exception{

        Class.forName(DRIVER);

        try(Connection con = DriverManager.getConnection(URL, USER, PW)){
            System.out.println(con);
        }catch(Exception e) {
            e.printStackTrace();
        }//try_
    }//testConnection_
}
