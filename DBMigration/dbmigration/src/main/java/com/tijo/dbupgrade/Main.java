package com.tijo.dbupgrade;

import com.tijo.util.IO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
	  DBConnection dcon = new DBConnection();
        String input ,ip , port, dbname , username , password;
        System.out.println("Enter IP , port, dbname, username,pwd,upgrdefile \n");
        input  = IO.readInput();
        String in[] =input.split(",");
        ip = in[0];
        //System.out.println("port");
        //port = IO.readInput();
        port=in[1];
        //System.out.println("dbname");
        //dbname = IO.readInput();
        dbname=in[2];;
        //System.out.println("User Name");
        //username = IO.readInput();
        username=in[3];;
        //System.out.println("password ");
        //password = IO.readInput();
        password = in[4];;
        Connection con = null;
        try {
            con = dcon.getConnection(ip , port, dbname , username , password);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        Statement statement;

        //System.out.println("upgrade script");
        //String upgradeFile = IO.readInput();
        String upgradeFile = in[5];
        String sqlscript = null;
        try {
             sqlscript = IO.readFile(upgradeFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        String[] sqlquery = sqlscript.split(";\n");
        IO writer  = new IO();
        for (String sqlstmt:sqlquery) {

            try {
                statement = con.createStatement();
                statement.executeUpdate(sqlstmt);
              //  System.out.println("executed "+sqlstmt);
            } catch (SQLException e) {
                System.out.println("Error executing query ==============> "+sqlstmt);
                e.printStackTrace();
                System.exit(0);
            }

            try {
                writer.writeFile(upgradeFile+".res",sqlstmt);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

        }
    }
}
