/*
 * Created by JFormDesigner on Sat Nov 15 17:08:37 EET 2014
 */

package forms.produced;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import MAIN.Main;
import connect.DBConnect;
import connect.DatabaseTableModel;
import forms.Fill;
import forms.order.*;
import info.clearthought.layout.*;
import org.freixas.jcalendar.*;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import static test.generated.Tables.*;
import static test.generated.Tables.CUSTOMER;
import static test.generated.Tables.ORDER_CONTRACT;

/**
 * @author ad ad
 */
public class ProducedForm extends JPanel implements Fill {
    DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);

    public ProducedForm() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        try {
            java.util.Date utilDate = calendarCombo1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            create.insertInto(PRODUCED, PRODUCED.IDPRODUCED, PRODUCED.DATE, PRODUCED.IDPRODUCTION, PRODUCED.COUNT).
                    values(Integer.parseInt(textField1.getText()), sqlDate, Integer.parseInt(textField2.getText()), Integer.parseInt(textField3.getText())).
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
            create.delete(PRODUCED).where(PRODUCED.IDPRODUCED.equal(Integer.parseInt(textField1.getText()))).execute();
        }catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(Main.mainForm, "Input ID for delete!");
        }
        Main.mainForm.updateTable();
    }

    private void textField1KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(!Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE) {
            e.consume();
        }
    }

    public void updateComboBoxes(){
        ArrayList arrayList = new ArrayList();
                arrayList.clear();
        for (Object[] objects1 : create.select(PRODUCTS.IDPRODUCTS, PRODUCTS.NAME).from(PRODUCTS).fetchArrays()) {
            arrayList.add(objects1[0] + " " + objects1[1]);
        }
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(arrayList.toArray());
        comboBox1.setModel(comboBoxModel);
    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        String[] strings = comboBox1.getSelectedItem().toString().split(" ");
        textField2.setText(strings[0]);
        textField2.setBackground(Color.white);
    }

    private void button3MouseClicked(MouseEvent e) {
        try {
            java.util.Date utilDate = calendarCombo1.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            create.update(PRODUCED).set(PRODUCED.IDPRODUCED, Integer.parseInt(textField1.getText()))
                    .set(PRODUCED.DATE, sqlDate)
                    .set(PRODUCED.IDPRODUCTION, Integer.parseInt(textField2.getText()))
                    .set(PRODUCED.COUNT, Integer.parseInt(textField3.getText()))
                    .where(PRODUCED.IDPRODUCED.equal((Integer) Main.mainForm.getTable1().getValueAt(Main.mainForm.getTable1().getSelectedRow(),0))).execute();
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
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

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
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)this2.getLayout()).setHGap(5);
            ((TableLayout)this2.getLayout()).setVGap(5);

            //---- label1 ----
            label1.setText("Produced Form");
            label1.setFont(new Font("Consolas", Font.BOLD, 20));
            label1.setForeground(new Color(182, 66, 103));
            this2.add(label1, new TableLayoutConstraints(1, 0, 4, 0, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label2 ----
            label2.setText("Id produced");
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
            calendarCombo1.setInheritsPopupMenu(true);
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
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void fill(ArrayList objects) {
        textField1.setText(String.valueOf(objects.get(0)));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed;
        try {
            parsed = format.parse(String.valueOf(objects.get(1)));
            calendarCombo1.setDate(parsed);
            calendarCombo1.updateUI();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        textField2.setText(String.valueOf(objects.get(2)));
        textField3.setText(String.valueOf(objects.get(4)));
        for (int i = 0; i < comboBox1.getItemCount(); i++) {
            String[] split = comboBox1.getItemAt(i).toString().split(" ");
            if(split[0].equals(textField2.getText())){
                comboBox1.setSelectedIndex(i);
                break;
            }
        }
    }



    public ResultSet producedInnerJoin() {
        return create.select(PRODUCED.IDPRODUCED, PRODUCED.DATE,PRODUCED.IDPRODUCTION, PRODUCTS.NAME, PRODUCED.COUNT)
                .from(PRODUCED)
                .leftOuterJoin(PRODUCTS)
                .on(PRODUCED.IDPRODUCTION.equal(PRODUCTS.IDPRODUCTS))
                .fetchResultSet();
    }
}
