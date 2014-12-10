/*
 * Created by JFormDesigner on Wed Dec 10 20:40:09 EET 2014
 */

package forms.search;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import MAIN.Main;
import info.clearthought.layout.*;
import static test.generated.Tables.*;
/**
 * @author Arthur
 */
public class Search extends JPanel {
    public Search() {
        initComponents();
        textField1.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = textField1.getText();

                if (text.trim().length() == 0) {
                    Main.mainForm.getRowSorter().setRowFilter(null);
                } else {
                    Main.mainForm.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textField1.getText();

                if (text.trim().length() == 0) {
                    Main.mainForm.getRowSorter().setRowFilter(null);
                } else {
                    Main.mainForm.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    private void button1ActionPerformed(ActionEvent e) {
        /*if(Main.mainForm.currTable.equals(CUSTOMER)) {
            Main.mainForm.getCustomerForm().search(textField1.getText());
        }
        if(Main.mainForm.currTable.equals(ORDER_CONTRACT)) {
            Main.mainForm.getOrderForm().search(textField1.getText());
        }
        if(Main.mainForm.currTable.equals(PLAN)) {
            Main.mainForm.getPlanForm().search(textField1.getText());
        }
        if(Main.mainForm.currTable.equals(PRODUCED)) {
            Main.mainForm.getProducedForm().search(textField1.getText());
        }
        if(Main.mainForm.currTable.equals(PRODUCTS)) {
            Main.mainForm.getProductsForm().search(textField1.getText());
        }
        if(Main.mainForm.currTable.equals(SENT)) {
            Main.mainForm.getSentForm().search(textField1.getText());
        }*/
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();

        //======== this ========
        setLayout(new TableLayout(new double[][] {
            {TableLayout.PREFERRED, TableLayout.FILL, 120},
            {TableLayout.PREFERRED, TableLayout.PREFERRED}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //---- label1 ----
        label1.setText("Search");
        label1.setFont(new Font("Consolas", Font.BOLD, 20));
        label1.setForeground(new Color(182, 66, 103));
        add(label1, new TableLayoutConstraints(1, 0, 1, 0, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

        //---- label2 ----
        label2.setText("By data");
        label2.setForeground(new Color(182, 66, 103));
        label2.setFont(new Font("Consolas", Font.BOLD, 15));
        add(label2, new TableLayoutConstraints(0, 1, 0, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        add(textField1, new TableLayoutConstraints(1, 1, 2, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
