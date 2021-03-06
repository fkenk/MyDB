/*
 * Created by JFormDesigner on Sat Nov 15 17:12:06 EET 2014
 */

package forms.sent;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
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
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;


import static test.generated.Tables.*;

/**
 * @author ad ad
 */
public class SentForm extends JPanel implements Fill {
    DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);
    public SentForm() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        try {
            java.util.Date utilDate = calendarCombo1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            create.insertInto(SENT, SENT.IDSENT, SENT.DATE, SENT.IDPRODUCTION, SENT.COUNT, SENT.NUMORDER).
                    values(Integer.parseInt(textField1.getText()), sqlDate, Integer.parseInt(textField2.getText()), Integer.parseInt(textField3.getText()), Integer.parseInt(textField4.getText())).
                            execute();
        }catch (DataAccessException exp){
            JOptionPane.showMessageDialog(Main.mainForm,exp);
        }catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Fill some data!");
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
        ArrayList arrayList = new ArrayList();
        for (Object[] objects1 : create.select(PRODUCTS.IDPRODUCTS, PRODUCTS.NAME).from(PRODUCTS).fetchArrays()) {
            arrayList.add(objects1[0] + " " + objects1[1]);
        }
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(arrayList.toArray());
        comboBox1.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel(arrayList.toArray());
        comboBox3.setModel(comboBoxModel);
        arrayList.clear();
        for (Object[] objects1 : create.select(ORDER_CONTRACT.IDORDER, ORDER_CONTRACT.DATE).from(ORDER_CONTRACT).fetchArrays()) {
            arrayList.add(objects1[0] + " " + objects1[1]);
        }
        comboBoxModel = new DefaultComboBoxModel(arrayList.toArray());
        comboBox2.setModel(comboBoxModel);
    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        String[] strings = comboBox1.getSelectedItem().toString().split(" ");
        textField2.setText(strings[0]);
        textField2.setBackground(Color.white);
    }

    private void comboBox2ActionPerformed(ActionEvent e) {
        String[] strings = comboBox2.getSelectedItem().toString().split(" ");
        textField4.setText(strings[0]);
        textField2.setBackground(Color.white);
    }

    private void button2MouseClicked(MouseEvent e) {
        try {
            create.delete(SENT).where(SENT.IDSENT.equal(Integer.parseInt(textField1.getText()))).execute();
        }catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(Main.mainForm, "Input ID for delete!");
        }
        Main.mainForm.updateTable();
    }

    private void button3MouseClicked(MouseEvent e) {
        try {
            java.util.Date utilDate = calendarCombo1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            create.update(SENT).set(SENT.IDSENT, Integer.parseInt(textField1.getText()))
                    .set(SENT.DATE, sqlDate)
                    .set(SENT.IDPRODUCTION, Integer.parseInt(textField2.getText()))
                    .set(SENT.COUNT, Integer.parseInt(textField3.getText()))
                    .set(SENT.NUMORDER, Integer.parseInt(textField4.getText()))
                    .where(SENT.IDSENT.equal((Integer) Main.mainForm.getTable1().getValueAt(Main.mainForm.getTable1().getSelectedRow(), 0))).execute();
        }catch (IndexOutOfBoundsException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Choose row to update!");
        }catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(Main.mainForm, "Choose row to update!");
        }
        Main.mainForm.updateTable();
    }

    private void textField2KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        textField2.setBackground(Color.white);
        if (!Character.isDigit(c)) {
            e.consume();
        }
        if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE ) {
            boolean flag = false;
            String s;
            for (int i = 0; i < comboBox1.getItemCount(); i++) {
                String[] split = comboBox1.getItemAt(i).toString().split(" ");
                if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    s = textField2.getText();
                    if (split[0].equals(s)) {
                        comboBox1.setSelectedIndex(i);
                        textField2.setText(textField2.getText().substring(0, textField2.getText().length()));
                        flag = true;
                        break;
                    }
                } else {
                    s = textField2.getText() + c;
                    if (split[0].equals(s)) {
                        comboBox1.setSelectedIndex(i);
                        textField2.setText(textField2.getText().substring(0, textField2.getText().length() - 1));
                        flag = true;
                        break;
                    }
                }
            }
            if (flag == false) {
                textField2.setBackground(new Color(170, 49, 58));
            }
        }
    }

    private void textField4KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        textField4.setBackground(Color.white);
        if (!Character.isDigit(c)) {
            e.consume();
        }
        if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE ) {
            boolean flag = false;
            String s;
            for (int i = 0; i < comboBox2.getItemCount(); i++) {
                String[] split = comboBox2.getItemAt(i).toString().split(" ");
                if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    s = textField4.getText();
                    if (split[0].equals(s)) {
                        comboBox2.setSelectedIndex(i);
                        textField4.setText(textField4.getText().substring(0, textField4.getText().length()));
                        flag = true;
                        break;
                    }
                } else {
                    s = textField4.getText() + c;
                    if (split[0].equals(s)) {
                        comboBox2.setSelectedIndex(i);
                        textField4.setText(textField4.getText().substring(0, textField4.getText().length() - 1));
                        flag = true;
                        break;
                    }
                }
            }
            if (flag == false) {
                textField4.setBackground(new Color(170, 49, 58));
            }
        }
    }

    private void comboBox3ActionPerformed(ActionEvent e) {
        String[] strings = comboBox3.getSelectedItem().toString().split(" ");
        textField5.setText(strings[0]);
        textField5.setBackground(Color.white);
    }

    private void textField5KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        textField5.setBackground(Color.white);
        if (!Character.isDigit(c)) {
            e.consume();
        }
        if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE ) {
            boolean flag = false;
            String s;
            for (int i = 0; i < comboBox3.getItemCount(); i++) {
                String[] split = comboBox3.getItemAt(i).toString().split(" ");
                if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    s = textField5.getText();
                    if (split[0].equals(s)) {
                        comboBox3.setSelectedIndex(i);
                        textField5.setText(textField5.getText().substring(0, textField5.getText().length()));
                        flag = true;
                        break;
                    }
                } else {
                    s = textField5.getText() + c;
                    if (split[0].equals(s)) {
                        comboBox3.setSelectedIndex(i);
                        textField5.setText(textField5.getText().substring(0, textField5.getText().length() - 1));
                        flag = true;
                        break;
                    }
                }
            }
            if (flag == false && !textField5.getText().isEmpty()) {
                textField5.setBackground(new Color(170, 49, 58));
            }
        }
    }

    private void button5MouseClicked(MouseEvent e) {
        ResultSet resultSet;
        if(textField4.getText().isEmpty()) {
            resultSet = create.select(SENT.IDSENT, SENT.DATE, SENT.IDPRODUCTION, SENT.COUNT, SENT.NUMORDER, CUSTOMER.ADRESS)
                    .from(SENT)
                    .leftOuterJoin(ORDER_CONTRACT)
                    .on(SENT.NUMORDER.equal(ORDER_CONTRACT.IDORDER))
                    .leftOuterJoin(CUSTOMER)
                    .on(ORDER_CONTRACT.IDCUSTOMER.equal(CUSTOMER.IDCUSTOMER))
                    .where(DSL.month(SENT.DATE).equal(monthChooser1.getMonth()+1))
                    .fetchResultSet();
        }else{
            resultSet = create.select(SENT.IDSENT, SENT.DATE, SENT.IDPRODUCTION, SENT.COUNT, SENT.NUMORDER, CUSTOMER.ADRESS)
                    .from(SENT)
                    .leftOuterJoin(ORDER_CONTRACT)
                    .on(SENT.NUMORDER.equal(ORDER_CONTRACT.IDORDER))
                    .leftOuterJoin(CUSTOMER)
                    .on(ORDER_CONTRACT.IDCUSTOMER.equal(CUSTOMER.IDCUSTOMER))
                    .where(DSL.month(SENT.DATE).equal(monthChooser1.getMonth() + 1).and(SENT.IDPRODUCTION.equal(Integer.valueOf(textField5.getText()))))
                    .fetchResultSet();
        }
        Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, SENT));
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
        textField2 = new JTextField();
        label5 = new JLabel();
        textField3 = new JTextField();
        label6 = new JLabel();
        comboBox2 = new JComboBox();
        textField4 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label4 = new JLabel();
        label8 = new JLabel();
        monthChooser1 = new JMonthChooser();
        label9 = new JLabel();
        comboBox3 = new JComboBox();
        textField5 = new JTextField();
        button5 = new JButton();

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
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)this2.getLayout()).setHGap(5);
            ((TableLayout)this2.getLayout()).setVGap(5);

            //---- label1 ----
            label1.setText("Sent Form");
            label1.setFont(new Font("Consolas", Font.BOLD, 20));
            label1.setForeground(new Color(182, 66, 103));
            this2.add(label1, new TableLayoutConstraints(1, 0, 4, 0, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label2 ----
            label2.setText("Id ");
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
            this2.add(calendarCombo1, new TableLayoutConstraints(2, 4, 5, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label7 ----
            label7.setText("Id production");
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

            //---- textField2 ----
            textField2.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField2KeyTyped(e);
                }
            });
            this2.add(textField2, new TableLayoutConstraints(4, 6, 5, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label5 ----
            label5.setText("Count ");
            label5.setForeground(new Color(182, 66, 103));
            label5.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label5, new TableLayoutConstraints(0, 8, 1, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField3 ----
            textField3.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField1KeyTyped(e);
                }
            });
            this2.add(textField3, new TableLayoutConstraints(2, 8, 5, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label6 ----
            label6.setText("Num order");
            label6.setForeground(new Color(182, 66, 103));
            label6.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label6, new TableLayoutConstraints(0, 10, 1, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- comboBox2 ----
            comboBox2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboBox2ActionPerformed(e);
                }
            });
            this2.add(comboBox2, new TableLayoutConstraints(2, 10, 3, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField4 ----
            textField4.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField4KeyTyped(e);
                }
            });
            this2.add(textField4, new TableLayoutConstraints(4, 10, 5, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button1 ----
            button1.setText("Add");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            this2.add(button1, new TableLayoutConstraints(3, 11, 3, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button2 ----
            button2.setText("Delete");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            this2.add(button2, new TableLayoutConstraints(4, 11, 4, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button3 ----
            button3.setText("Update");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
            });
            this2.add(button3, new TableLayoutConstraints(5, 11, 5, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label4 ----
            label4.setText("Month report");
            label4.setFont(new Font("Consolas", Font.BOLD, 20));
            label4.setForeground(new Color(182, 66, 103));
            this2.add(label4, new TableLayoutConstraints(2, 12, 3, 12, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label8 ----
            label8.setText("Select month");
            label8.setForeground(new Color(182, 66, 103));
            label8.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label8, new TableLayoutConstraints(0, 13, 0, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(monthChooser1, new TableLayoutConstraints(2, 13, 5, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label9 ----
            label9.setText("Id ");
            label9.setForeground(new Color(182, 66, 103));
            label9.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label9, new TableLayoutConstraints(0, 14, 0, 14, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- comboBox3 ----
            comboBox3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboBox3ActionPerformed(e);
                }
            });
            this2.add(comboBox3, new TableLayoutConstraints(2, 14, 3, 14, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField5 ----
            textField5.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField5KeyTyped(e);
                }
            });
            this2.add(textField5, new TableLayoutConstraints(4, 14, 5, 14, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button5 ----
            button5.setText("Select");
            button5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button5MouseClicked(e);
                }
            });
            this2.add(button5, new TableLayoutConstraints(5, 15, 5, 15, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
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
    private JTextField textField2;
    private JLabel label5;
    private JTextField textField3;
    private JLabel label6;
    private JComboBox comboBox2;
    private JTextField textField4;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label4;
    private JLabel label8;
    private JMonthChooser monthChooser1;
    private JLabel label9;
    private JComboBox comboBox3;
    private JTextField textField5;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    @Override
    public void fill(ArrayList objects) {
        textField1.setText(String.valueOf(objects.get(0)));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(String.valueOf(objects.get(1)));
            calendarCombo1.setDate(parsed);
            calendarCombo1.updateUI();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        textField2.setText(String.valueOf(objects.get(2)));
        textField3.setText(String.valueOf(objects.get(4)));
        textField4.setText(String.valueOf(objects.get(5)));
        for (int i = 0; i < comboBox1.getItemCount(); i++) {
            String[] split = comboBox1.getItemAt(i).toString().split(" ");
            if(split[0].equals(textField2.getText())){
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

    public ResultSet sentInnerJoin() {
        return create.select(SENT.IDSENT, SENT.DATE,SENT.IDPRODUCTION, PRODUCTS.NAME, SENT.COUNT, SENT.NUMORDER)
                .from(SENT)
                .leftOuterJoin(PRODUCTS)
                .on(PRODUCTS.IDPRODUCTS.equal(SENT.IDPRODUCTION))
                .fetchResultSet();
    }
}
