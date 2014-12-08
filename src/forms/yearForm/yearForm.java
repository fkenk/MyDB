/*
 * Created by JFormDesigner on Mon Dec 08 16:24:20 EET 2014
 */

package forms.yearForm;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import com.toedter.calendar.*;
import info.clearthought.layout.*;

/**
 * @author Arthur
 */
public class yearForm extends JFrame {
    public yearForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new TableLayout(new double[][] {
            {TableLayout.PREFERRED, TableLayout.PREFERRED},
            {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
        ((TableLayout)contentPane.getLayout()).setHGap(5);
        ((TableLayout)contentPane.getLayout()).setVGap(5);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, "", null},
                },
                new String[] {
                    "Customer", "Quantity ordered", "Product", "not fulfilled"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, new TableLayoutConstraints(0, 1, 0, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
