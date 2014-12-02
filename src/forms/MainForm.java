package forms;
import MAIN.Main;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;

import forms.customer.CustomerForm;
import forms.order.OrderForm;
import forms.produced.ProducedForm;
import forms.products.ProductsForm;
import forms.sent.SentForm;
import info.clearthought.layout.*;
import org.jooq.*;
import org.jooq.impl.DSL;
import connect.*;

import static test.generated.Tables.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
/*
 * Created by JFormDesigner on Fri Nov 07 17:21:10 EET 2014
 */


/**
 * @author aa aa
 */
public class MainForm extends JFrame {
    private Object oldID;
    DatabaseTableModel databaseTableModel = new DatabaseTableModel();
    private int selectedRow;
    private static Table currTable;
    CustomerForm customerForm = new CustomerForm();
    ProductsForm productsForm = new ProductsForm();
    ProducedForm producedForm = new ProducedForm();
    SentForm sentForm = new SentForm();
    OrderForm orderForm = new OrderForm();
    private ArrayList<Object> objects = new ArrayList<Object>();

    private DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);
    public MainForm() {
        initComponents();
        tabbedPane1.addTab("Customer", customerForm);
        tabbedPane1.addTab("Product", productsForm);
        tabbedPane1.addTab("Produced",producedForm);
        tabbedPane1.addTab("Sent",sentForm);
        tabbedPane1.addTab("Order",orderForm);
        table1.setColumnSelectionInterval(table1.getColumnCount()-1,table1.getColumnCount()-1);
        table1.setRowSelectionInterval(table1.getRowCount()-1, table1.getRowCount()-1);
        TableCellListener tcl;
        tcl = new TableCellListener(table1, actionTable());
    }

    public DatabaseTableModel setDBTableModel(ResultSet rs, Table s) {
        currTable = s;
        try {
            databaseTableModel.setDataSource(rs);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return databaseTableModel;
    }
    
    public void updateTable(Table s) {
        ResultSet result = create.select().from(s).fetchResultSet();
        table1.setModel(setDBTableModel(result, s));
        try {
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JTable getTable1() {
        return table1;
    }

    public Action actionTable(){
        return new AbstractAction()
       {
           public void actionPerformed(ActionEvent e) {
               ArrayList<String> columnNames = new ArrayList<String>();
               TableCellListener tcl = (TableCellListener) e.getSource();
              // System.out.println("Row   : " + tcl.getRow());
              // System.out.println("Column: " + tcl.getColumn());
              // System.out.println("Old   : " + tcl.getOldValue());
              // System.out.println("New   : " + tcl.getNewValue());
               ResultSet rs = create.select().from(currTable).fetchResultSet();
              // System.out.println("CURTABLE"+currTable);
               try {
                   ResultSetMetaData rsmd = rs.getMetaData();
                   int columnCount = rsmd.getColumnCount();
                   for (int i = 0; i < columnCount; i++) {
                       columnNames.add(rsmd.getColumnName(i + 1));

                   }
               } catch (SQLException e1) {
                   e1.printStackTrace();
               }
               //System.out.println(columnNames.get(tcl.getColumn()));
               //currTable.field(columnNames.get)
               try {
                  // System.out.println(currTable.field(columnNames.get(tcl.getColumn())));
                   //System.out.println(columnNames.get(tcl.getColumn()));
                  // Object i = table1.getValueAt(tcl.getRow(), 0);
                   //System.out.println(i);
                   create.update(currTable).set(currTable.field(columnNames.get(tcl.getColumn())), tcl.getNewValue()).where(currTable.field(0).equal(oldID)).execute();

               }catch (Exception exp) {
                   JOptionPane.showMessageDialog(Main.mainForm,exp);
               }
               updateTable(currTable);
           }
       };
    }


    private void tabbedPane1StateChanged(ChangeEvent e) {
        if(tabbedPane1.getSelectedIndex() == 0) {
           this.updateTable(CUSTOMER);
        }

        if(tabbedPane1.getSelectedIndex() == 1) {
            this.updateTable(PRODUCTS);
        }

        if(tabbedPane1.getSelectedIndex() == 2) {
            this.updateTable(PRODUCED);
            producedForm.updateComboBoxes();
        }

        if(tabbedPane1.getSelectedIndex() == 3) {
            this.updateTable(SENT);
            sentForm.updateComboBoxes();
        }

        if(tabbedPane1.getSelectedIndex() == 4) {
            this.updateTable(ORDER_CONTRACT);
            orderForm.updateComboBoxes();
        }

    }

    private void table1MouseClicked(MouseEvent e) {
        int column = table1.columnAtPoint(e.getPoint());
        selectedRow = table1.rowAtPoint(e.getPoint());
        table1.setColumnSelectionInterval(column, column);
        table1.setRowSelectionInterval(selectedRow, selectedRow);
        oldID = table1.getValueAt(selectedRow,0);
        if (e.getClickCount() == 2) {
            final ArrayList<String> columnNames = new ArrayList<String>();
            System.out.println("double clicked");
            String dataStr = JOptionPane.showInputDialog(Main.mainForm,"Enter date in format dd-MM-yyyy");
            ResultSet rs = create.select().from(currTable).fetchResultSet();
            try {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    columnNames.add(rsmd.getColumnName(i + 1));

                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date parsed;
            try {
                parsed = format.parse(dataStr);
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                create.update(currTable).set(currTable.field(columnNames.get(column)),sql).where(currTable.field(0).equal(table1.getValueAt(selectedRow,0))).execute();
                updateTable(currTable);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getButton() == MouseEvent.BUTTON3) {
            if(tabbedPane1.getSelectedIndex() == 0) {
                popupMenu1.show(table1, e.getX(), e.getY());
            }
        }
        for(int i = 0; i < table1.getColumnCount(); i++) {
            if(table1.getValueAt(selectedRow, i) == null) {
                objects.add(i,"");
            }else {
                objects.add(i, table1.getValueAt(selectedRow, i));
            }
        }
        if(tabbedPane1.getSelectedIndex() == 0) {
            customerForm.fill(objects);
        }
        else
        if(tabbedPane1.getSelectedIndex() == 1) {
            productsForm.fill(objects);
        }
        else
        if(tabbedPane1.getSelectedIndex() == 2) {
            producedForm.fill(objects);
        }
        else
        if(tabbedPane1.getSelectedIndex() == 3) {
            sentForm.fill(objects);
        }
        else
        if(tabbedPane1.getSelectedIndex() == 4) {
            orderForm.fill(objects);
        }
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        int customerID = (Integer)table1.getValueAt(selectedRow,0);
        tabbedPane1.setSelectedIndex(4);
        ResultSet result = create.select().from(ORDER_CONTRACT).where(ORDER_CONTRACT.IDCUSTOMER.equal(customerID)).fetchResultSet();
        table1.setModel(setDBTableModel(result, ORDER_CONTRACT));
        try {
            result.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        int customerID = (Integer)table1.getValueAt(selectedRow, 0);
        create.delete(CUSTOMER).where(CUSTOMER.IDCUSTOMER.equal(customerID)).execute();
        this.updateTable(CUSTOMER);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        tabbedPane1 = new JTabbedPane();
        popupMenu1 = new JPopupMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();

        //======== this ========
        setBackground(Color.white);
        Container contentPane = getContentPane();
        contentPane.setLayout(new TableLayout(new double[][] {
            {TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL},
            {TableLayout.FILL, 270}}));
        ((TableLayout)contentPane.getLayout()).setHGap(5);
        ((TableLayout)contentPane.getLayout()).setVGap(5);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, "", null, null},
                    {null, null, null, null, null},
                },
                new String[] {
                    "id", "name", "adress", "phone", "bank"
                }
            ));
            table1.setBackground(Color.lightGray);
            table1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(80, 36, 56), new Color(129, 39, 72)));
            table1.setAutoCreateRowSorter(true);
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, new TableLayoutConstraints(0, 0, 4, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== tabbedPane1 ========
        {
            tabbedPane1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    tabbedPane1StateChanged(e);
                }
            });
        }
        contentPane.add(tabbedPane1, new TableLayoutConstraints(0, 1, 2, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        setSize(895, 490);
        setLocationRelativeTo(getOwner());

        //======== popupMenu1 ========
        {

            //---- menuItem1 ----
            menuItem1.setText("text");
            popupMenu1.add(menuItem1);

            //---- menuItem2 ----
            menuItem2.setText("Delete Row");
            menuItem2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuItem2ActionPerformed(e);
                }
            });
            popupMenu1.add(menuItem2);

            //---- menuItem3 ----
            menuItem3.setText("Show orders for client");
            menuItem3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuItem3ActionPerformed(e);
                }
            });
            popupMenu1.add(menuItem3);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JTabbedPane tabbedPane1;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
