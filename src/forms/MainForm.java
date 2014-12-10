package forms;
import MAIN.Main;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.*;
import com.toedter.calendar.*;

import forms.customer.CustomerForm;
import forms.order.OrderForm;
import forms.plan.PlanForm;
import forms.produced.ProducedForm;
import forms.products.ProductsForm;
import forms.search.Search;
import forms.sent.SentForm;
import info.clearthought.layout.*;
import org.jooq.*;
import org.jooq.impl.DSL;
import connect.*;

import static test.generated.Tables.*;
import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
    public static Table currTable;
    private TableRowSorter<TableModel> rowSorter;

    private CustomerForm customerForm = new CustomerForm();
    private ProductsForm productsForm = new ProductsForm();
    private PlanForm planForm = new PlanForm();
    private ProducedForm producedForm = new ProducedForm();
    private SentForm sentForm = new SentForm();
    private OrderForm orderForm = new OrderForm();
    private ArrayList<Object> objects = new ArrayList<Object>();

    private DSLContext create = DSL.using(DBConnect.getConnect(), SQLDialect.MYSQL);
    public MainForm() {
        initComponents();
        tabbedPane1.addTab("Customer", customerForm);
        tabbedPane1.addTab("Product", productsForm);
        tabbedPane1.addTab("Plan", planForm);
        tabbedPane1.addTab("Produced",producedForm);
        tabbedPane1.addTab("Sent",sentForm);
        tabbedPane1.addTab("Order",orderForm);
        table1.setColumnSelectionInterval(table1.getColumnCount()-1,table1.getColumnCount()-1);
        table1.setRowSelectionInterval(table1.getRowCount()-1, table1.getRowCount()-1);
        panel2.add(new Search(),new TableLayoutConstraints(0, 0, 0, 0, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        panel2.validate();

        //TableCellListener tcl;
        //tcl = new TableCellListener(table1, actionTable());
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
    
    public void updateTable() {
        if(tabbedPane1.getSelectedIndex() == 0) {
            table1.setModel(setDBTableModel(customerForm.customerSelect(), CUSTOMER));
        }
        if(tabbedPane1.getSelectedIndex() == 1) {
            table1.setModel(setDBTableModel(productsForm.productsSelect(), PRODUCTS));
        }
        if(tabbedPane1.getSelectedIndex() == 2) {
            table1.setModel(setDBTableModel(planForm.planInnerJoin(), PLAN));
            planForm.updateComboBoxes();
            planForm.colorForColunms();
        }
        if(tabbedPane1.getSelectedIndex() == 3) {
            table1.setModel(setDBTableModel(producedForm.producedInnerJoin(), PRODUCED));
            producedForm.updateComboBoxes();
        }
        if(tabbedPane1.getSelectedIndex() == 4) {
            table1.setModel(setDBTableModel(sentForm.sentInnerJoin(), SENT));
            sentForm.updateComboBoxes();
        }
        if(tabbedPane1.getSelectedIndex() == 5) {
            table1.setModel(setDBTableModel(orderForm.orderInnerJoin(), ORDER_CONTRACT));
            orderForm.updateComboBoxes();
            orderForm.colorForColunms();
        }
        rowSorter = new TableRowSorter(table1.getModel());
        table1.setRowSorter(rowSorter);
    }


    public TableRowSorter<TableModel> getRowSorter() {
        return rowSorter;
    }

    public JTable getTable1() {
        return table1;
    }

    /*public Action actionTable(){
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
    }*/


    private void tabbedPane1StateChanged(ChangeEvent e) {
           this.updateTable();
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
                updateTable();
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
            planForm.fill(objects);
        }
        else
        if(tabbedPane1.getSelectedIndex() == 3) {
            producedForm.fill(objects);
        }
        else
        if(tabbedPane1.getSelectedIndex() == 4) {
            sentForm.fill(objects);
        }
        else
        if(tabbedPane1.getSelectedIndex() == 5) {
            orderForm.fill(objects);
        }
        objects.clear();
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        int customerID = (Integer)table1.getValueAt(selectedRow,0);
        tabbedPane1.setSelectedIndex(5);
        ResultSet resultSet = create.select(ORDER_CONTRACT.IDORDER,ORDER_CONTRACT.DATE,ORDER_CONTRACT.IDCUSTOMER, CUSTOMER.NAME,ORDER_CONTRACT.IDPRODUTION, PRODUCTS.NAME,ORDER_CONTRACT.COUNT,ORDER_CONTRACT.MONTHDELIVER, ORDER_CONTRACT.PERCENT)
                .from(ORDER_CONTRACT)
                .leftOuterJoin(CUSTOMER)
                .on(CUSTOMER.IDCUSTOMER.equal(ORDER_CONTRACT.IDCUSTOMER))
                .join(PRODUCTS)
                .on(PRODUCTS.IDPRODUCTS.equal(ORDER_CONTRACT.IDPRODUTION))
                .where(ORDER_CONTRACT.IDCUSTOMER.equal(customerID))
                .fetchResultSet();
        table1.setModel(setDBTableModel(resultSet, ORDER_CONTRACT));
        orderForm.colorForColunms();
        try {
            resultSet.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        int customerID = (Integer)table1.getValueAt(selectedRow, 0);
        create.delete(CUSTOMER).where(CUSTOMER.IDCUSTOMER.equal(customerID)).execute();
        this.updateTable();
    }

    private void menuItem5ActionPerformed(ActionEvent e) {
        try {
            ExcelExporter.writeToExcell(table1,"qwe.xls");
            System.out.println("dsfsdf");
        } catch (IOException e1) {
            System.out.println(e1);
        }

    }

    private void menuItem4ActionPerformed(ActionEvent e) {
        try
        {
            java.util.Locale en = new java.util.Locale("en");
            String header = currTable.getName();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm");
            String footer = "Надруковано: "+sdf.format(new Date()) + " (стр.{0})";
            table1.print(javax.swing.JTable.PrintMode.FIT_WIDTH,
                    new java.text.MessageFormat(header, en),
                    new java.text.MessageFormat(footer, en));
        }
        catch (Exception e1) {
            System.out.println(e1);
            JOptionPane.showMessageDialog(null,"Error while print");
        }
    }

    public CustomerForm getCustomerForm() {
        return customerForm;
    }

    public ProductsForm getProductsForm() {
        return productsForm;
    }

    public PlanForm getPlanForm() {
        return planForm;
    }

    public ProducedForm getProducedForm() {
        return producedForm;
    }

    public SentForm getSentForm() {
        return sentForm;
    }

    public OrderForm getOrderForm() {
        return orderForm;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        tabbedPane1 = new JTabbedPane();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        panel2 = new JPanel();
        popupMenu1 = new JPopupMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();

        //======== this ========
        setBackground(Color.white);
        Container contentPane = getContentPane();
        contentPane.setLayout(new TableLayout(new double[][] {
            {TableLayout.MINIMUM, TableLayout.MINIMUM, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL},
            {TableLayout.FILL, 270, TableLayout.PREFERRED}}));
        ((TableLayout)contentPane.getLayout()).setHGap(5);
        ((TableLayout)contentPane.getLayout()).setVGap(5);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Export");

                //---- menuItem4 ----
                menuItem4.setText("Print");
                menuItem4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menuItem4ActionPerformed(e);
                    }
                });
                menu1.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("To Exel");
                menuItem5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menuItem5ActionPerformed(e);
                    }
                });
                menu1.add(menuItem5);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== tabbedPane1 ========
        {
            tabbedPane1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    tabbedPane1StateChanged(e);
                }
            });
        }
        contentPane.add(tabbedPane1, new TableLayoutConstraints(0, 0, 1, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

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
        contentPane.add(scrollPane1, new TableLayoutConstraints(2, 0, 4, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== panel1 ========
        {
            panel1.setLayout(new TableLayout(new double[][] {
                {TableLayout.PREFERRED, TableLayout.PREFERRED},
                {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
            ((TableLayout)panel1.getLayout()).setHGap(5);
            ((TableLayout)panel1.getLayout()).setVGap(5);
        }
        contentPane.add(panel1, new TableLayoutConstraints(3, 1, 4, 1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

        //======== panel2 ========
        {
            panel2.setLayout(new TableLayout(new double[][] {
                {TableLayout.FILL},
                {TableLayout.PREFERRED}}));
            ((TableLayout)panel2.getLayout()).setHGap(5);
            ((TableLayout)panel2.getLayout()).setVGap(5);
        }
        contentPane.add(panel2, new TableLayoutConstraints(0, 2, 1, 2, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
        setSize(1070, 515);
        setLocationRelativeTo(getOwner());

        //======== popupMenu1 ========
        {

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
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JTabbedPane tabbedPane1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JPanel panel2;
    private JPopupMenu popupMenu1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
