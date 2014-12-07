/*
 * Created by JFormDesigner on Sat Nov 15 17:06:09 EET 2014
 */

package forms.plan;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import forms.Fill;
import info.clearthought.layout.*;

/**
 * @author ad ad
 */
public class PlanForm extends JPanel{
    public PlanForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        this2 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        textField6 = new JTextField();
        label3 = new JLabel();
        textField7 = new JTextField();
        label7 = new JLabel();
        textField8 = new JTextField();
        label4 = new JLabel();
        textField9 = new JTextField();
        label5 = new JLabel();
        textField10 = new JTextField();
        label6 = new JLabel();
        textField11 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setLayout(new TableLayout(new double[][] {
            {TableLayout.FILL, TableLayout.PREFERRED},
            {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
        ((TableLayout)getLayout()).setHGap(5);
        ((TableLayout)getLayout()).setVGap(5);

        //======== this2 ========
        {
            this2.setLayout(new TableLayout(new double[][] {
                {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL},
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)this2.getLayout()).setHGap(5);
            ((TableLayout)this2.getLayout()).setVGap(5);

            //---- label1 ----
            label1.setText("Plan Form");
            label1.setFont(new Font("Consolas", Font.BOLD, 20));
            label1.setForeground(new Color(182, 66, 103));
            this2.add(label1, new TableLayoutConstraints(3, 0, 6, 0, TableLayoutConstraints.CENTER, TableLayoutConstraints.CENTER));

            //---- label2 ----
            label2.setText("Id order");
            label2.setForeground(new Color(182, 66, 103));
            label2.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label2, new TableLayoutConstraints(0, 2, 2, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField6, new TableLayoutConstraints(3, 2, 6, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label3 ----
            label3.setText("Date");
            label3.setForeground(new Color(182, 66, 103));
            label3.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label3, new TableLayoutConstraints(0, 4, 2, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField7, new TableLayoutConstraints(3, 4, 6, 4, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label7 ----
            label7.setText("Id customer");
            label7.setForeground(new Color(182, 66, 103));
            label7.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label7, new TableLayoutConstraints(0, 6, 1, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField8, new TableLayoutConstraints(3, 6, 6, 6, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label4 ----
            label4.setText("Id production");
            label4.setForeground(new Color(182, 66, 103));
            label4.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label4, new TableLayoutConstraints(0, 8, 1, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField9, new TableLayoutConstraints(3, 8, 6, 8, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label5 ----
            label5.setText("Count ");
            label5.setForeground(new Color(182, 66, 103));
            label5.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label5, new TableLayoutConstraints(0, 10, 2, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField10, new TableLayoutConstraints(3, 10, 6, 10, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

            //---- label6 ----
            label6.setText("Month deliver");
            label6.setForeground(new Color(182, 66, 103));
            label6.setFont(new Font("Consolas", Font.BOLD, 15));
            this2.add(label6, new TableLayoutConstraints(0, 12, 1, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
            this2.add(textField11, new TableLayoutConstraints(3, 12, 6, 12, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        }
        add(this2, new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- button1 ----
        button1.setText("Add");
        add(button1, new TableLayoutConstraints(0, 1, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //---- button2 ----
        button2.setText("Delete");
        add(button2, new TableLayoutConstraints(0, 2, 1, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel this2;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField6;
    private JLabel label3;
    private JTextField textField7;
    private JLabel label7;
    private JTextField textField8;
    private JLabel label4;
    private JTextField textField9;
    private JLabel label5;
    private JTextField textField10;
    private JLabel label6;
    private JTextField textField11;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
