/*
 * Created by JFormDesigner on Sat Nov 15 17:06:09 EET 2014
 */

package forms.plan;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import MAIN.Main;
import com.toedter.calendar.*;

import connect.DBConnect;
import forms.Fill;
import info.clearthought.layout.*;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import static test.generated.Tables.PLAN;
import static test.generated.Tables.PRODUCTS;

/**
 * @author ad ad
 */
public class PlanForm extends JPanel implements Fill{
    DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);
    public PlanForm() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        try {
            create.insertInto(PLAN, PLAN.MONTH, PLAN.IDPRODUCTION, PLAN.COUNT).
                    values(monthChooser1.getMonth(), Integer.parseInt(textField2.getText()), Integer.parseInt(textField3.getText())).
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
            create.delete(PLAN).where(PLAN.MONTH.equal(monthChooser1.getMonth()).and(PLAN.IDPRODUCTION.equal(Integer.parseInt(textField2.getText())))).execute();
        }catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(Main.mainForm, "Input ID for delete!");
        }
        Main.mainForm.updateTable();
    }

    private void button3MouseClicked(MouseEvent e) {
        try {
            create.update(PLAN)
                    .set(PLAN.MONTH, monthChooser1.getMonth() + 1)
                    .set(PLAN.IDPRODUCTION, Integer.parseInt(textField2.getText()))
                    .set(PLAN.COUNT, Integer.parseInt(textField3.getText()))
                    .where(PLAN.MONTH.equal(monthChooser1.getMonth()).and(PLAN.IDPRODUCTION.equal(Integer.parseInt(textField2.getText())))).execute();
        }catch (IndexOutOfBoundsException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Choose row to update!");
        }catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(Main.mainForm, "Choose row to update!");
        }
        Main.mainForm.updateTable();
    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        String[] strings = comboBox1.getSelectedItem().toString().split(" ");
        textField2.setText(strings[0]);
        textField2.setBackground(Color.white);
    }

    public void colorForColunms() {
        Main.mainForm.getTable1().getColumnModel().getColumn(4).setCellRenderer(new Renderer());
    }

    private void textField1KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            e.consume();
        }
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
            if (flag == false && !textField4.getText().isEmpty()) {
                textField4.setBackground(new Color(170, 49, 58));
            }
        }
    }

    private void comboBox2ActionPerformed(ActionEvent e) {
        String[] strings = comboBox2.getSelectedItem().toString().split(" ");
        textField4.setText(strings[0]);
        textField4.setBackground(Color.white);
    }

    private void button4MouseClicked(MouseEvent e) {
        ResultSet resultSet;
        if(textField4.getText().isEmpty()) {
            resultSet = create.select(PLAN.MONTH, PLAN.IDPRODUCTION, PRODUCTS.NAME, PLAN.COUNT, PLAN.PERCENT)
                    .from(PLAN)
                    .leftOuterJoin(PRODUCTS)
                    .on(PLAN.IDPRODUCTION.equal(PRODUCTS.IDPRODUCTS))
                    .where(PLAN.MONTH.equal(monthChooser2.getMonth()+1))
                    .fetchResultSet();
        }else{
            resultSet = create.select(PLAN.MONTH, PLAN.IDPRODUCTION, PRODUCTS.NAME, PLAN.COUNT, PLAN.PERCENT)
                    .from(PLAN)
                    .leftOuterJoin(PRODUCTS)
                    .on(PLAN.IDPRODUCTION.equal(PRODUCTS.IDPRODUCTS))
                    .where(PLAN.MONTH.equal(monthChooser2.getMonth()+1).and(PLAN.IDPRODUCTION.equal(Integer.valueOf(textField4.getText()))))
                    .fetchResultSet();
        }
        Main.mainForm.getTable1().setModel(Main.mainForm.setDBTableModel(resultSet, PLAN));
        colorForColunms();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JPanel();
        label1 = new JLabel();
        label3 = new JLabel();
        monthChooser1 = new JMonthChooser();
        label4 = new JLabel();
        comboBox1 = new JComboBox();
        textField2 = new JTextField();
        label5 = new JLabel();
        textField3 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label6 = new JLabel();
        label7 = new JLabel();
        monthChooser2 = new JMonthChooser();
        label8 = new JLabel();
        comboBox2 = new JComboBox();
        textField4 = new JTextField();
        button4 = new JButton();

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
            label1.setText("Plan Form");
            label1.setFont(new Font("Consolas", Font.BOLD, 20));
            label1.setForeground(new Color(182, 66, 103));
            this2.add(label1, new TableLayoutConstraints(2, 0, 3, 0, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label3 ----
            label3.setText("Month");
            label3.setForeground(new Color(182, 66, 103));
            label3.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label3, new TableLayoutConstraints(0, 4, 1, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(monthChooser1, new TableLayoutConstraints(2, 4, 5, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label4 ----
            label4.setText("Id production");
            label4.setForeground(new Color(182, 66, 103));
            label4.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label4, new TableLayoutConstraints(0, 6, 1, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

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

            //---- button1 ----
            button1.setText("Add");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            this2.add(button1, new TableLayoutConstraints(3, 9, 3, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button2 ----
            button2.setText("Delete");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            this2.add(button2, new TableLayoutConstraints(4, 9, 4, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button3 ----
            button3.setText("Update");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
            });
            this2.add(button3, new TableLayoutConstraints(5, 9, 5, 9, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label6 ----
            label6.setText("Month Report");
            label6.setFont(new Font("Consolas", Font.BOLD, 20));
            label6.setForeground(new Color(182, 66, 103));
            this2.add(label6, new TableLayoutConstraints(2, 10, 3, 10, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label7 ----
            label7.setForeground(new Color(182, 66, 103));
            label7.setFont(new Font("Consolas", Font.BOLD, 15));
            label7.setText("Select Month");
            this2.add(label7, new TableLayoutConstraints(0, 11, 1, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(monthChooser2, new TableLayoutConstraints(2, 11, 5, 11, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label8 ----
            label8.setForeground(new Color(182, 66, 103));
            label8.setFont(new Font("Consolas", Font.BOLD, 15));
            label8.setText("Select Product");
            this2.add(label8, new TableLayoutConstraints(0, 12, 1, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- comboBox2 ----
            comboBox2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboBox2ActionPerformed(e);
                }
            });
            this2.add(comboBox2, new TableLayoutConstraints(2, 12, 3, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField4 ----
            textField4.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField4KeyTyped(e);
                }
            });
            this2.add(textField4, new TableLayoutConstraints(4, 12, 5, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button4 ----
            button4.setText("Select");
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button4MouseClicked(e);
                }
            });
            this2.add(button4, new TableLayoutConstraints(5, 13, 5, 13, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        add(this2, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel this2;
    private JLabel label1;
    private JLabel label3;
    private JMonthChooser monthChooser1;
    private JLabel label4;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JLabel label5;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label6;
    private JLabel label7;
    private JMonthChooser monthChooser2;
    private JLabel label8;
    private JComboBox comboBox2;
    private JTextField textField4;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    @Override
    public void fill(ArrayList objects) {
        monthChooser1.setMonth((Integer)objects.get(0) - 1);
        textField2.setText(String.valueOf(objects.get(1)));
        textField3.setText(String.valueOf(objects.get(3)));
        for (int i = 0; i < comboBox1.getItemCount(); i++) {
            String[] split = comboBox1.getItemAt(i).toString().split(" ");
            if(split[0].equals(textField2.getText())){
                comboBox1.setSelectedIndex(i);
                break;
            }
        }

    }

    public void updateComboBoxes() {
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        for (Object[] objects1 : create.select(PRODUCTS.IDPRODUCTS, PRODUCTS.NAME).from(PRODUCTS).fetchArrays()) {
            arrayList.add(objects1[0] + " " + objects1[1]);
        }
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(arrayList.toArray());
        comboBox1.setModel(comboBoxModel);
        comboBoxModel = new DefaultComboBoxModel(arrayList.toArray());
        comboBox2.setModel(comboBoxModel);
    }

    public ResultSet planInnerJoin() {
        return create.select(PLAN.MONTH, PLAN.IDPRODUCTION, PRODUCTS.NAME, PLAN.COUNT, PLAN.PERCENT)
                .from(PLAN)
                .leftOuterJoin(PRODUCTS)
                .on(PLAN.IDPRODUCTION.equal(PRODUCTS.IDPRODUCTS))
                .fetchResultSet();
    }
}
