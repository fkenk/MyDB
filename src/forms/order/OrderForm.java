/*
 * Created by JFormDesigner on Sat Nov 15 16:59:54 EET 2014
 */

package forms.order;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;

import MAIN.Main;
import connect.DBConnect;
import connect.DatabaseTableModel;
import forms.Fill;
import info.clearthought.layout.*;
import org.freixas.jcalendar.*;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import static test.generated.Tables.CUSTOMER;
import static test.generated.Tables.ORDER_CONTRACT;
import static test.generated.Tables.PRODUCTS;

/**
 * @author ad ad
 */
public class OrderForm extends JPanel implements Fill{
    DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);
    public OrderForm() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        try {
            java.util.Date utilDate = calendarCombo1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            create.insertInto(ORDER_CONTRACT,ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER,ORDER_CONTRACT.IDPRODUTION,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER).
                    values(Integer.parseInt(textField1.getText()), sqlDate, Integer.parseInt(textField3.getText()), Integer.parseInt(textField4.getText()), Integer.parseInt(textField5.getText()), Integer.parseInt(textField6.getText())).
                    execute();
        }catch (Exception exp){
            JOptionPane.showMessageDialog(Main.mainForm,exp);
        }
        Main.mainForm.updateTable(ORDER_CONTRACT);
    }

    private void button2MouseClicked(MouseEvent e) {
        try {
            System.out.println(Integer.parseInt(textField1.getText()));
            create.delete(ORDER_CONTRACT).where(ORDER_CONTRACT.IDORDER.equal(Integer.parseInt(textField1.getText()))).execute();
        }catch (Exception exp) {
            JOptionPane.showMessageDialog(Main.mainForm, exp);
        }
        Main.mainForm.updateTable(ORDER_CONTRACT);
    }

    private void textField1KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            e.consume();
        }
    }

    public void updateComboBoxes(){
        label1.setText("Order Form Numeric");
        ArrayList<String> arrayList = new ArrayList<String>();
        for (Object[] objects1 : create.select(CUSTOMER.IDCUSTOMER,CUSTOMER.NAME).from(CUSTOMER).fetchArrays()) {
            arrayList.add(objects1[0] + " " + objects1[1]);
        }
        DefaultComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<Object>(arrayList.toArray());
        comboBox1.setModel(comboBoxModel);
        arrayList.clear();
        for (Object[] objects1 : create.select(PRODUCTS.IDPRODUCTS, PRODUCTS.NAME).from(PRODUCTS).fetchArrays()) {
            arrayList.add(objects1[0] + " " + objects1[1]);
        }
        comboBoxModel = new DefaultComboBoxModel<Object>(arrayList.toArray());
        comboBox2.setModel(comboBoxModel);
    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        String[] strings = comboBox1.getSelectedItem().toString().split(" ");
        textField3.setText(strings[0]);
    }

    private void comboBox2ActionPerformed(ActionEvent e) {
        String[] strings = comboBox2.getSelectedItem().toString().split(" ");
        textField4.setText(strings[0]);
    }

    private void label1MouseClicked(MouseEvent e) {
        if(label1.getText().equals("Order Form Numeric")) {
            ResultSet resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,CUSTOMER.NAME,PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER)
                    .from(ORDER_CONTRACT)
                    .leftOuterJoin(CUSTOMER)
                    .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                    .join(PRODUCTS)
                    .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION)).fetchResultSet();
            DatabaseTableModel databaseTableModel = new DatabaseTableModel();
            try {
                databaseTableModel.setDataSource(resultSet);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            Main.mainForm.getTable1().setModel(databaseTableModel);
            /*TableColumn customerColumn = Main.mainForm.getTable1().getColumnModel().getColumn(2);
            JComboBox comboBoxForColum1 = new JComboBox();
            ArrayList<String> arrayList = new ArrayList<String>();
            /*for (Object[] objects1 : create.select(CUSTOMER.IDCUSTOMER, CUSTOMER.NAME).from(CUSTOMER).fetchArrays()) {
                arrayList.add(objects1[0] + " " + objects1[1]);
            }

            DefaultComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<Object>(arrayList.toArray());
            comboBoxForColum1.setModel(comboBoxModel);
            comboBoxForColum1.addItem("1");
            customerColumn.setCellEditor(new DefaultCellEditor(comboBoxForColum1));*/
            label1.setText("Order Form Symbol");
        } else
        if(label1.getText().equals("Order Form Symbol")) {
            Main.mainForm.updateTable(ORDER_CONTRACT);
            label1.setText("Order Form Numeric");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        calendarCombo1 = new JCalendarCombo();
        label7 = new JLabel();
        textField3 = new JTextField();
        comboBox1 = new JComboBox<Object>();
        label4 = new JLabel();
        textField4 = new JTextField();
        comboBox2 = new JComboBox<Object>();
        label5 = new JLabel();
        textField5 = new JTextField();
        label6 = new JLabel();
        textField6 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setLayout(new TableLayout(new double[][] {
            {TableLayout.FILL},
            {TableLayout.PREFERRED}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //======== this2 ========
        {
            this2.setLayout(new TableLayout(new double[][] {
                {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL},
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)this2.getLayout()).setHGap(5);
            ((TableLayout)this2.getLayout()).setVGap(5);

            //---- label1 ----
            label1.setText("Order Form Numeric");
            label1.setFont(new Font("Consolas", Font.BOLD, 20));
            label1.setForeground(new Color(182, 66, 103));
            label1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    label1MouseClicked(e);
                }
            });
            this2.add(label1, new TableLayoutConstraints(1, 0, 4, 0, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label2 ----
            label2.setText("Id order");
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
            label3.setText("Date");
            label3.setForeground(new Color(182, 66, 103));
            label3.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label3, new TableLayoutConstraints(0, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- calendarCombo1 ----
            calendarCombo1.setMaximumRowCount(9);
            this2.add(calendarCombo1, new TableLayoutConstraints(2, 4, 5, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label7 ----
            label7.setText("Id customer");
            label7.setForeground(new Color(182, 66, 103));
            label7.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label7, new TableLayoutConstraints(0, 6, 1, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField3 ----
            textField3.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField1KeyTyped(e);
                }
            });
            this2.add(textField3, new TableLayoutConstraints(2, 6, 3, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- comboBox1 ----
            comboBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboBox1ActionPerformed(e);
                }
            });
            this2.add(comboBox1, new TableLayoutConstraints(4, 6, 5, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label4 ----
            label4.setText("Id production");
            label4.setForeground(new Color(182, 66, 103));
            label4.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label4, new TableLayoutConstraints(0, 8, 1, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField4 ----
            textField4.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField1KeyTyped(e);
                }
            });
            this2.add(textField4, new TableLayoutConstraints(2, 8, 3, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- comboBox2 ----
            comboBox2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboBox2ActionPerformed(e);
                }
            });
            this2.add(comboBox2, new TableLayoutConstraints(4, 8, 5, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label5 ----
            label5.setText("Count ");
            label5.setForeground(new Color(182, 66, 103));
            label5.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label5, new TableLayoutConstraints(0, 10, 1, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField5 ----
            textField5.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField1KeyTyped(e);
                }
            });
            this2.add(textField5, new TableLayoutConstraints(2, 10, 5, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label6 ----
            label6.setText("Month deliver");
            label6.setForeground(new Color(182, 66, 103));
            label6.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label6, new TableLayoutConstraints(0, 12, 1, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField6, new TableLayoutConstraints(2, 12, 5, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button1 ----
            button1.setText("Add");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            this2.add(button1, new TableLayoutConstraints(2, 13, 3, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button2 ----
            button2.setText("Delete");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            this2.add(button2, new TableLayoutConstraints(4, 13, 5, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
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
    private JCalendarCombo calendarCombo1;
    private JLabel label7;
    private JTextField textField3;
    private JComboBox<Object> comboBox1;
    private JLabel label4;
    private JTextField textField4;
    private JComboBox<Object> comboBox2;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label6;
    private JTextField textField6;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    @Override
    public void fill(ArrayList objects) {
        textField1.setText(String.valueOf(objects.get(0)));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed;
        try {
            parsed = format.parse(String.valueOf(objects.get(1)));
            calendarCombo1.setDate(parsed);
            calendarCombo1.updateUI();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        textField3.setText(String.valueOf(objects.get(2)));
        textField4.setText(String.valueOf(objects.get(3)));
        textField5.setText(String.valueOf(objects.get(4)));
        textField6.setText(String.valueOf(objects.get(5)));
        for (int i = 0; i < comboBox1.getItemCount(); i++) {
            String[] split = comboBox1.getItemAt(i).toString().split(" ");
            if(split[0].equals(textField3.getText())){
                comboBox1.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < comboBox2.getItemCount(); i++) {
            String[] split = comboBox2.getItemAt(i).toString().split(" ");
            if(split[0].equals(textField4.getText())){
                comboBox2.setSelectedIndex(i);
                break;
            }
        }
    }
}
