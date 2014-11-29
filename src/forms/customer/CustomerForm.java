/*
 * Created by JFormDesigner on Sat Nov 15 15:19:53 EET 2014
 */

package forms.customer;

import connect.DBConnect;
import forms.Fill;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;
import MAIN.Main;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static test.generated.Tables.CUSTOMER;

/**
 * @author ad ad
 */
public class CustomerForm extends JPanel implements Fill {
    public CustomerForm() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e){
        DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);
        try {
            create.insertInto(CUSTOMER, CUSTOMER.IDCUSTOMER, CUSTOMER.NAME, CUSTOMER.ADRESS, CUSTOMER.PHONE, CUSTOMER.BANKING_ACCOUNT).
                    values(Integer.parseInt(textField1.getText()), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText()).
                    execute();
        }catch (Exception exp){
            JOptionPane.showMessageDialog(Main.mainForm,exp);
        }
        Main.mainForm.updateTable(CUSTOMER);
    }

    private void textField1KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(!Character.isDigit(c)) {
            e.consume();
            System.out.println("dafdf");
            System.out.println("dfsdfdsfsdf");
            System.out.println("aaaaaaaaaaaaaaaaaaaa");
            System.out.println("mybranch");
            System.out.println("myBranch1");
        }
    }

    private void button2MouseClicked(MouseEvent e) {
        DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);
        try {
            create.delete(CUSTOMER).where(CUSTOMER.IDCUSTOMER.equal(Integer.parseInt(textField1.getText()))).execute();
        }catch (Exception exp) {
            JOptionPane.showMessageDialog(Main.mainForm, exp);
        }
        Main.mainForm.updateTable(CUSTOMER);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        textField2 = new JTextField();
        label7 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setLayout(new TableLayout(new double[][] {
            {TableLayout.FILL},
            {TableLayout.FILL}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //======== this2 ========
        {
            this2.setLayout(new TableLayout(new double[][] {
                {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL},
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)this2.getLayout()).setHGap(5);
            ((TableLayout)this2.getLayout()).setVGap(5);

            //---- label1 ----
            label1.setText("Clients Form");
            label1.setFont(new Font("Consolas", Font.BOLD, 20));
            label1.setForeground(new Color(182, 66, 103));
            this2.add(label1, new TableLayoutConstraints(2, 0, 3, 0, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label2 ----
            label2.setText("Id customer");
            label2.setForeground(new Color(182, 66, 103));
            label2.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label2, new TableLayoutConstraints(0, 2, 1, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField1 ----
            textField1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField1KeyTyped(e);
                }
            });
            this2.add(textField1, new TableLayoutConstraints(2, 2, 5, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label3 ----
            label3.setText("Name");
            label3.setForeground(new Color(182, 66, 103));
            label3.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label3, new TableLayoutConstraints(0, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField2, new TableLayoutConstraints(2, 4, 5, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label7 ----
            label7.setText("Adress");
            label7.setForeground(new Color(182, 66, 103));
            label7.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label7, new TableLayoutConstraints(0, 6, 1, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField3, new TableLayoutConstraints(2, 6, 5, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label4 ----
            label4.setText("Phone");
            label4.setForeground(new Color(182, 66, 103));
            label4.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label4, new TableLayoutConstraints(0, 8, 1, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField4, new TableLayoutConstraints(2, 8, 5, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label5 ----
            label5.setText("Banking account");
            label5.setForeground(new Color(182, 66, 103));
            label5.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label5, new TableLayoutConstraints(0, 10, 1, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField5, new TableLayoutConstraints(2, 10, 5, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button1 ----
            button1.setText("Add");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            this2.add(button1, new TableLayoutConstraints(2, 11, 3, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button2 ----
            button2.setText("Delete");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            this2.add(button2, new TableLayoutConstraints(4, 11, 5, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        add(this2, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel this2;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JTextField textField2;
    private JLabel label7;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JButton button1;
    private JButton button2;

    @Override
    public void fill(ArrayList objects) {
        textField1.setText(String.valueOf(objects.get(0)));
        textField2.setText(String.valueOf(objects.get(1)));
        textField3.setText(String.valueOf(objects.get(2)));
        textField4.setText(String.valueOf(objects.get(3)));
        textField5.setText(String.valueOf(objects.get(4)));
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
