package com.tijo.dbupgrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by tijo on 1/10/16.
 */
public class DBConnection {


    public Connection getConnection(String ip, String port , String dbname , String username, String pwd ) throws Exception{
        String url = "jdbc:postgresql://"+ip+":"+port+"/"+dbname;
        Properties props = new Properties();
        try {
            props.setProperty("user", username);
            props.setProperty("password", pwd);
            Connection conn = DriverManager.getConnection(url, props);
            return conn;
        }catch (Exception e ){
            throw e;
        }

    }
}
