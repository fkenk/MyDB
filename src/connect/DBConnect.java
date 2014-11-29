package connect;


import org.jooq.*;
import org.jooq.impl.DSL;
import test.generated.Tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import static test.generated.Tables.CUSTOMER;

/**
 * Created by Ворона on 19.10.2014.
 */
public class DBConnect {
    private static Connection con;
    private static int idCust = 1;
    private Statement st;
    private ResultSet rs;

    public DBConnect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursschema", "root", "261013");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println("Error : " + ex);
        }
    }

    public static Connection getConnect() {
        return con;
    }
    }
