/*
 * Created by JFormDesigner on Sat Nov 15 15:18:06 EET 2014
 */

package forms.products;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import MAIN.Main;
import connect.DBConnect;
import forms.Fill;
import info.clearthought.layout.*;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import static test.generated.Tables.PRODUCED;
import static test.generated.Tables.PRODUCTS;

/**
 * @author ad ad
 */
public class ProductsForm extends JPanel implements Fill {
    DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);
    public ProductsForm() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        try {
            create.insertInto(PRODUCTS, PRODUCTS.IDPRODUCTS, PRODUCTS.NAME, PRODUCTS.PRICE).
                    values(Integer.parseInt(textField1.getText()), textField2.getText(), Double.parseDouble(textField3.getText())).
                    execute();
        }catch (DataAccessException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Duplicate key!");
        }catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Fill some data!");
        }
        Main.mainForm.updateTable(PRODUCTS);
    }

    private void button2MouseClicked(MouseEvent e) {
        try {
            create.delete(PRODUCTS).where(PRODUCTS.IDPRODUCTS.equal(Integer.parseInt(textField1.getText()))).execute();
        }catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(Main.mainForm, "Input ID for delete!");
        }
        Main.mainForm.updateTable(PRODUCTS);
    }

    private void textField1KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            e.consume();
        }
    }

    private void textField3KeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.print(c);
        if (!Character.isDigit(c) && c != '.') {
            e.consume();
        }
    }

    private void button3MouseClicked(MouseEvent e) {
        try {
            create.update(PRODUCTS).set(PRODUCTS.IDPRODUCTS, Integer.parseInt(textField1.getText()))
                    .set(PRODUCTS.NAME, textField2.getText())
                    .set(PRODUCTS.PRICE, Double.parseDouble(textField3.getText()))
                    .where(PRODUCTS.IDPRODUCTS.equal((Integer) Main.mainForm.getTable1().getValueAt(Main.mainForm.getTable1().getSelectedRow(), 0))).execute();
        }catch (IndexOutOfBoundsException exp){
            JOptionPane.showMessageDialog(Main.mainForm,"Choose row to update!");
        }catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(Main.mainForm, "Choose row to update!");
        }
        Main.mainForm.updateTable(PRODUCTS);
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
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
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
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)this2.getLayout()).setHGap(5);
            ((TableLayout)this2.getLayout()).setVGap(5);

            //---- label1 ----
            label1.setText("Product Form");
            label1.setFont(new Font("Consolas", Font.BOLD, 20));
            label1.setForeground(new Color(182, 66, 103));
            this2.add(label1, new TableLayoutConstraints(2, 0, 3, 0, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label2 ----
            label2.setText("Id product");
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
            label7.setText("Price");
            label7.setForeground(new Color(182, 66, 103));
            label7.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label7, new TableLayoutConstraints(0, 6, 1, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- textField3 ----
            textField3.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField3KeyTyped(e);
                }
            });
            this2.add(textField3, new TableLayoutConstraints(2, 6, 5, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button1 ----
            button1.setText("Add");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            this2.add(button1, new TableLayoutConstraints(2, 7, 2, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button2 ----
            button2.setText("Delete");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            this2.add(button2, new TableLayoutConstraints(3, 7, 3, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button3 ----
            button3.setText("Update");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
            });
            this2.add(button3, new TableLayoutConstraints(4, 7, 4, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- button4 ----
            button4.setText("Search");
            this2.add(button4, new TableLayoutConstraints(5, 7, 5, 7, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
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
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    @Override
    public void fill(ArrayList objects) {
        textField1.setText(String.valueOf(objects.get(0)));
        textField2.setText(String.valueOf(objects.get(1)));
        textField3.setText(String.valueOf(objects.get(2)));
    }

}
