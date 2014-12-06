package forms.order;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by Vorona on 06.12.2014.
 */
public class Renderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        try{
            if ((Double)value == 100) {
                component.setBackground(new Color(123,187,130));
            }else if ((Double)value > 70 && (Double)value < 100 ){
                component.setBackground(new Color(198, 187, 66));
            }else if ((Double)value > 40 && (Double)value < 70 ){
                component.setBackground(new Color(198, 114, 70));
            }else if ((Double)value >= 0 && (Double)value < 40 ){
                component.setBackground(new Color(198, 48, 49));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return component;
    }
}
