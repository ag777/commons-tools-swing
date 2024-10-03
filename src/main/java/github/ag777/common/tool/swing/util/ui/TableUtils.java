package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/30 上午11:33
 */
public class TableUtils {

    /**
     * 读取 JTable 中的所有数据。
     * @param table 要读取数据的 JTable 实例
     * @return 包含所有行数据的列表，每个元素是一个包含一行数据的列表
     */
    public static List<List<Object>> readAllDataFromTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        List<List<Object>> allData = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            List<Object> rowData = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) {
                rowData.add(model.getValueAt(i, j));
            }
            allData.add(rowData);
        }

        return allData;
    }

    public static TableColumn addColumn(JTable table, int modelIndex, String title, int width, CellBuilder cellBuilder) {
        TableColumn tableColumn = new TableColumn(
                modelIndex,
                width,
                (table1, value, isSelected, hasFocus, row, column) -> cellBuilder.accept(table1, value, isSelected, hasFocus, row, column, true),
                new DefaultCellEditor(new JCheckBox()) {
                    @Override
                    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                        return cellBuilder.accept(table, value, isSelected, null, row, column, false);
                    }
                });
        tableColumn.setHeaderValue(title);
        table.addColumn(tableColumn);
        return tableColumn;
    }

    public static void setColumn(TableColumn tableColumn, CellBuilder cellBuilder) {
        tableColumn.setCellRenderer((table, value, isSelected, hasFocus, row, column) -> cellBuilder.accept(table, value, isSelected, hasFocus, row, column, true));
        tableColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return cellBuilder.accept(table, value, isSelected, null, row, column, false);
            }
        });
    }

    /**
     * 设置JTable的各列宽度
     * @param table JTable实例
     * @param widths 列宽度数组，-1表示该列宽度自动计算
     */
    public static void setTableWidths(JTable table, int[] widths) {
        // 调用autoWidths方法设置表格列宽
        autoWidths(table, widths);

        // 如果需要的话，可以添加一个组件大小改变监听器来动态更新列宽
        table.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // 当组件大小改变时，重新计算并设置列宽
                autoWidths(table, widths);
            }
        });
    }

    /**
     * 自动计算并设置表格的列宽度
     * @param table JTable实例
     * @param widths 列宽度数组，-1表示该列宽度自动计算
     */
     private static void autoWidths(JTable table, int[] widths) {
       // 获取表格的列模型
       TableColumnModel columnModel = table.getColumnModel();

       int numAutoColumns = 0; // 需要自动调整宽度的列数
       int totalFixedWidth = 0; // 所有固定宽度的列的总宽度
       int numColumns = columnModel.getColumnCount(); // 总列数
       int[] specifiedWidths = new int[numColumns]; // 存储指定的宽度

       // 初始化指定宽度数组
       for (int i = 0; i < numColumns; i++) {
           specifiedWidths[i] = (widths.length > i) ? widths[i] : -1;

           if (specifiedWidths[i] == -1) {
               numAutoColumns++; // 计算需要自动调整宽度的列数
           } else {
               totalFixedWidth += specifiedWidths[i]; // 计算所有非自动调整宽度的列的总宽度
           }
       }

       // 计算剩余宽度，用于自动调整宽度的列
       int remainingWidth = table.getWidth() - totalFixedWidth;
       int autoWidth = (numAutoColumns == 0) ? 0 : remainingWidth / numAutoColumns;

       // 设置列宽度
       for (int i = 0; i < numColumns; i++) {
           if (specifiedWidths[i] == -1) {
               // 对于需要自动调整宽度的列，设置其首选宽度为计算得到的autoWidth
               columnModel.getColumn(i).setPreferredWidth(autoWidth);
           } else {
               // 对于有指定宽度的列，设置其首选宽度为指定的宽度
               columnModel.getColumn(i).setPreferredWidth(specifiedWidths[i]);
           }
       }
   }


    @FunctionalInterface
    public interface CellBuilder {
        Component accept(JTable table, Object value,
                boolean isSelected, Boolean hasFocus,
                int row, int column, boolean isRender);
    }

}
