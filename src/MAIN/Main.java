package MAIN;

import connect.DBConnect;
import forms.MainForm;

import javax.swing.*;

/**
 * Created by Ворона on 19.10.2014.
 */
public class Main {
    public static MainForm mainForm;
    public static void main(String[] aegs){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
        }
        catch (ClassNotFoundException e) {
        }
        catch (InstantiationException e) {
        }
        catch (IllegalAccessException e) {
        }
        DBConnect connect = new DBConnect();
        mainForm = new MainForm();
        mainForm.setVisible(true);
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

