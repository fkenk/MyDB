/*
 * Created by JFormDesigner on Sat Nov 15 16:59:54 EET 2014
 */

package forms.order;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import MAIN.Main;
import com.toedter.calendar.*;
import connect.DBConnect;
import connect.DatabaseTableModel;
import forms.Fill;
import info.clearthought.layout.*;
import org.freixas.jcalendar.*;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
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
                    values(Integer.parseInt(textField1.getText()), sqlDate, Integer.parseInt(textField3.getText()), Integer.parseInt(textField4.getText()), Integer.parseInt(textField5.getText()), monthChooser1.getMonth() + 1).
                    execute();
        }catch (DataAccessException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Duplicate key!");
        }catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Fill some data!");
        }
        Main.mainForm.updateTable();
    }

    private void button2MouseClicked(MouseEvent e) {
        try {
            create.delete(ORDER_CONTRACT).where(ORDER_CONTRACT.IDORDER.equal(Integer.parseInt(textField1.getText()))).execute();
        }catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(Main.mainForm, "Input ID for delete!");
        }
        Main.mainForm.updateTable();
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
        comboBoxModel = new DefaultComboBoxModel<Object>(arrayList.toArray());
        comboBox3.setModel(comboBoxModel);
        arrayList.clear();
        colorForColunms();
    }

    public void colorForColunms() {
        Main.mainForm.getTable1().getColumnModel().getColumn(8).setCellRenderer(new Renderer());
    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        String[] strings = comboBox1.getSelectedItem().toString().split(" ");
        textField3.setText(strings[0]);
    }

    private void button3MouseClicked(MouseEvent e) {
        try {
            java.util.Date utilDate = calendarCombo1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            create.update(ORDER_CONTRACT).set(ORDER_CONTRACT.IDORDER, Integer.parseInt(textField1.getText()))
                    .set(ORDER_CONTRACT.DATE, sqlDate)
                    .set(ORDER_CONTRACT.IDCUSTOMER, Integer.parseInt(textField3.getText()))
                    .set(ORDER_CONTRACT.IDPRODUTION, Integer.parseInt(textField4.getText()))
                    .set(ORDER_CONTRACT.COUNT, Integer.parseInt(textField5.getText()))
                    .set(ORDER_CONTRACT.MONTHDELIVER, monthChooser1.getMonth() + 1)
                    .where(ORDER_CONTRACT.IDORDER.equal((Integer) Main.mainForm.getTable1().getValueAt(Main.mainForm.getTable1().getSelectedRow(), 0))).execute();
        }catch (IndexOutOfBoundsException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Choose row to update!");
        }catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(Main.mainForm, "Choose row to update!");
        }
        Main.mainForm.updateTable();
    }

    private void button4MouseClicked(MouseEvent e) {
        //create.select().from(ORDER_CONTRACT).
    }

    private void calendarCombo2DateChanged(DateEvent e) {
        Date date = calendarCombo2.getDate();
        calendarCombo3.setDate(date);
        calendarCombo3.updateUI();
    }

    private void monthChooser3PropertyChange(PropertyChangeEvent e) {
        int month = monthChooser2.getMonth();
        monthChooser3.setMonth(month);
        monthChooser3.updateUI();
    }

    private void comboBox2ActionPerformed(ActionEvent e) {
        String[] strings = comboBox2.getSelectedItem().toString().split(" ");
        textField4.setText(strings[0]);
    }

    private void button5MouseClicked(MouseEvent e) {
        java.util.Date utilDate1 = calendarCombo2.getDate();
        java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
        java.util.Date utilDate2 = calendarCombo3.getDate();
        java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
        ResultSet resultSet = null;
        if(checkBox1.isSelected() && !checkBox2.isSelected() && !checkBox3.isSelected()){
            resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                    .from(ORDER_CONTRACT)
                    .leftOuterJoin(CUSTOMER)
                    .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                    .join(PRODUCTS)
                    .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION)).where(ORDER_CONTRACT.DATE.between(sqlDate1, sqlDate2)).fetchResultSet();
            Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, ORDER_CONTRACT));
        }else if(!checkBox1.isSelected() && checkBox2.isSelected() && !checkBox3.isSelected() ){
            String[] strings = comboBox3.getSelectedItem().toString().split(" ");
            resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                    .from(ORDER_CONTRACT)
                    .leftOuterJoin(CUSTOMER)
                    .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                    .join(PRODUCTS)
                    .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION))
                    .where(ORDER_CONTRACT.IDPRODUTION.equal(Integer.valueOf(strings[0])))
                    .fetchResultSet();
            Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, ORDER_CONTRACT));
        }else if(!checkBox1.isSelected() && !checkBox2.isSelected() && checkBox3.isSelected()){
            resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                    .from(ORDER_CONTRACT)
                    .leftOuterJoin(CUSTOMER)
                    .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                    .join(PRODUCTS)
                    .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION))
                    .where(ORDER_CONTRACT.MONTHDELIVER.between(monthChooser2.getMonth() + 1,monthChooser3.getMonth() + 1))
                    .fetchResultSet();
            Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, ORDER_CONTRACT));
        }else if(checkBox1.isSelected() && checkBox2.isSelected() && !checkBox3.isSelected()){
            String[] strings = comboBox3.getSelectedItem().toString().split(" ");
            resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                    .from(ORDER_CONTRACT)
                    .leftOuterJoin(CUSTOMER)
                    .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                    .join(PRODUCTS)
                    .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION))
                    .where(ORDER_CONTRACT.DATE.between(sqlDate1, sqlDate2)
                            .and(ORDER_CONTRACT.IDPRODUTION.equal(Integer.valueOf(strings[0]))))
                    .fetchResultSet();
            Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, ORDER_CONTRACT));
        }else if(!checkBox1.isSelected() && checkBox2.isSelected() && checkBox3.isSelected()) {
            String[] strings = comboBox3.getSelectedItem().toString().split(" ");
            resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                    .from(ORDER_CONTRACT)
                    .leftOuterJoin(CUSTOMER)
                    .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                    .join(PRODUCTS)
                    .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION))
                    .where(ORDER_CONTRACT.MONTHDELIVER.between(monthChooser2.getMonth() + 1, monthChooser3.getMonth() + 1)
                            .and(ORDER_CONTRACT.IDPRODUTION.equal(Integer.valueOf(strings[0]))))
                    .fetchResultSet();
            Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, ORDER_CONTRACT));
        }else if(checkBox1.isSelected() && !checkBox2.isSelected() && checkBox3.isSelected()) {
            resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                    .from(ORDER_CONTRACT)
                    .leftOuterJoin(CUSTOMER)
                    .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                    .join(PRODUCTS)
                    .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION))
                    .where(ORDER_CONTRACT.MONTHDELIVER.between(monthChooser2.getMonth() + 1, monthChooser3.getMonth() + 1)
                            .and(ORDER_CONTRACT.DATE.between(sqlDate1, sqlDate2)))
                    .fetchResultSet();
            Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, ORDER_CONTRACT));
        }else if(checkBox1.isSelected() && checkBox2.isSelected() && checkBox3.isSelected()) {
            String[] strings = comboBox3.getSelectedItem().toString().split(" ");
            resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                    .from(ORDER_CONTRACT)
                    .leftOuterJoin(CUSTOMER)
                    .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                    .join(PRODUCTS)
                    .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION))
                    .where(ORDER_CONTRACT.MONTHDELIVER.between(monthChooser2.getMonth() + 1, monthChooser3.getMonth() + 1)
                            .and(ORDER_CONTRACT.DATE.between(sqlDate1, sqlDate2))
                            .and(ORDER_CONTRACT.IDPRODUTION.equal(Integer.valueOf(strings[0]))))
                    .fetchResultSet();
            Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, ORDER_CONTRACT));
        }
        try {
            resultSet.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        colorForColunms();
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
        comboBox1 = new JComboBox();
        textField3 = new JTextField();
        label4 = new JLabel();
        comboBox2 = new JComboBox();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        label6 = new JLabel();
        monthChooser1 = new JMonthChooser();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label8 = new JLabel();
        label11 = new JLabel();
        checkBox1 = new JCheckBox();
        label12 = new JLabel();
        calendarCombo2 = new JCalendarCombo();
        label13 = new JLabel();
        calendarCombo3 = new JCalendarCombo();
        label10 = new JLabel();
        checkBox2 = new JCheckBox();
        comboBox3 = new JComboBox();
        label9 = new JLabel();
        checkBox3 = new JCheckBox();
        label14 = new JLabel();
        monthChooser2 = new JMonthChooser();
        label15 = new JLabel();
        monthChooser3 = new JMonthChooser();
        button5 = new JButton();

        //======== this ========
        setLayout(new TableLayout(new double[][] {
            {TableLayout.MINIMUM},
            {TableLayout.PREFERRED}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //======== this2 ========
        {
            this2.setLayout(new TableLayout(new double[][] {
                {TableLayout.PREFERRED, 50, 80, 120, 120, 120},
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)this2.getLayout()).setHGap(5);
            ((TableLayout)this2.getLayout()).setVGap(5);

            //---- label1 ----
            label1.setText("Order Form Numeric");
            label1.setFont(new Font("Consolas", Font.BOLD, 20));
            label1.setForeground(new Color(182, 66, 103));
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

            //---- comboBox1 ----
            comboBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboBox1ActionPerformed(e);
                }
            });
            this2.add(comboBox1, new TableLayoutConstraints(2, 6, 3, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField3 ----
            textField3.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField1KeyTyped(e);
                }
            });
            this2.add(textField3, new TableLayoutConstraints(4, 6, 5, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label4 ----
            label4.setText("Id production");
            label4.setForeground(new Color(182, 66, 103));
            label4.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label4, new TableLayoutConstraints(0, 8, 1, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- comboBox2 ----
            comboBox2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboBox2ActionPerformed(e);
                }
            });
            this2.add(comboBox2, new TableLayoutConstraints(2, 8, 3, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField4 ----
            textField4.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField1KeyTyped(e);
                }
            });
            this2.add(textField4, new TableLayoutConstraints(4, 8, 5, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

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
            this2.add(monthChooser1, new TableLayoutConstraints(2, 12, 5, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button1 ----
            button1.setText("Add");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            this2.add(button1, new TableLayoutConstraints(3, 13, 3, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button2 ----
            button2.setText("Delete");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            this2.add(button2, new TableLayoutConstraints(4, 13, 4, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button3 ----
            button3.setText("Update");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
            });
            this2.add(button3, new TableLayoutConstraints(5, 13, 5, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label8 ----
            label8.setText("Sample");
            label8.setForeground(new Color(182, 66, 103));
            label8.setFont(new Font("Consolas", Font.BOLD, 20));
            this2.add(label8, new TableLayoutConstraints(2, 14, 3, 14, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

            //---- label11 ----
            label11.setText("By date order");
            label11.setForeground(new Color(182, 66, 103));
            label11.setFont(new Font("Consolas", Font.BOLD, 15));
            label11.setLabelFor(comboBox2);
            this2.add(label11, new TableLayoutConstraints(0, 15, 0, 15, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(checkBox1, new TableLayoutConstraints(1, 15, 1, 15, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

            //---- label12 ----
            label12.setText("form");
            label12.setForeground(new Color(182, 66, 103));
            label12.setFont(new Font("Consolas", Font.BOLD, 15));
            label12.setLabelFor(comboBox2);
            this2.add(label12, new TableLayoutConstraints(2, 15, 2, 15, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- calendarCombo2 ----
            calendarCombo2.addDateListener(new DateListener() {
                @Override
                public void dateChanged(DateEvent e) {
                    calendarCombo2DateChanged(e);
                }
            });
            this2.add(calendarCombo2, new TableLayoutConstraints(3, 15, 3, 15, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label13 ----
            label13.setText("to");
            label13.setForeground(new Color(182, 66, 103));
            label13.setFont(new Font("Consolas", Font.BOLD, 15));
            label13.setLabelFor(comboBox2);
            this2.add(label13, new TableLayoutConstraints(4, 15, 4, 15, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
            this2.add(calendarCombo3, new TableLayoutConstraints(5, 15, 5, 15, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label10 ----
            label10.setText("By production");
            label10.setForeground(new Color(182, 66, 103));
            label10.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label10, new TableLayoutConstraints(0, 16, 0, 16, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(checkBox2, new TableLayoutConstraints(1, 16, 1, 16, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
            this2.add(comboBox3, new TableLayoutConstraints(3, 16, 5, 16, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label9 ----
            label9.setText("By month deliver");
            label9.setForeground(new Color(182, 66, 103));
            label9.setFont(new Font("Consolas", Font.BOLD, 15));
            label9.setLabelFor(comboBox2);
            this2.add(label9, new TableLayoutConstraints(0, 17, 0, 17, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(checkBox3, new TableLayoutConstraints(1, 17, 1, 17, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

            //---- label14 ----
            label14.setText("form");
            label14.setForeground(new Color(182, 66, 103));
            label14.setFont(new Font("Consolas", Font.BOLD, 15));
            label14.setLabelFor(comboBox2);
            this2.add(label14, new TableLayoutConstraints(2, 17, 2, 17, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- monthChooser2 ----
            monthChooser2.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent e) {
                    monthChooser3PropertyChange(e);
                }
            });
            this2.add(monthChooser2, new TableLayoutConstraints(3, 17, 3, 17, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label15 ----
            label15.setText("to");
            label15.setForeground(new Color(182, 66, 103));
            label15.setFont(new Font("Consolas", Font.BOLD, 15));
            label15.setLabelFor(comboBox2);
            this2.add(label15, new TableLayoutConstraints(4, 17, 4, 17, TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));
            this2.add(monthChooser3, new TableLayoutConstraints(5, 17, 5, 17, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button5 ----
            button5.setText("Sample");
            button5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button5MouseClicked(e);
                }
            });
            this2.add(button5, new TableLayoutConstraints(5, 18, 5, 18, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
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
    private JComboBox comboBox1;
    private JTextField textField3;
    private JLabel label4;
    private JComboBox comboBox2;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label6;
    private JMonthChooser monthChooser1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label8;
    private JLabel label11;
    private JCheckBox checkBox1;
    private JLabel label12;
    private JCalendarCombo calendarCombo2;
    private JLabel label13;
    private JCalendarCombo calendarCombo3;
    private JLabel label10;
    private JCheckBox checkBox2;
    private JComboBox comboBox3;
    private JLabel label9;
    private JCheckBox checkBox3;
    private JLabel label14;
    private JMonthChooser monthChooser2;
    private JLabel label15;
    private JMonthChooser monthChooser3;
    private JButton button5;
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
        textField4.setText(String.valueOf(objects.get(4)));
        textField5.setText(String.valueOf(objects.get(6)));
        monthChooser1.setMonth((Integer)objects.get(7) - 1);
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

    public ResultSet orderInnerJoin(){
        ResultSet resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                .from(ORDER_CONTRACT)
                .leftOuterJoin(CUSTOMER)
                .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                .join(PRODUCTS)
                .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION)).fetchResultSet();
        return resultSet;
    }
}
