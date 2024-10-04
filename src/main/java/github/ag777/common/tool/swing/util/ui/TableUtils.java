package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * JTable相关工具类
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/30 上午11:33
 */
public class TableUtils {

    /**
     * 创建一个具有指定列标题和自定义单元格可编辑性的表格
     *
     * @param titles       表格的列标题
     * @param isCellEditable   一个BiPredicate，用于确定特定单元格是否可编辑
     * @return 返回一个根据指定标题和可编辑性创建的JTable实例
     */
    public static JTable newTable(String[] titles, BiPredicate<Integer, Integer> isCellEditable) {
        DefaultTableModel dataModel = new DefaultTableModel(new Object[][]{}, titles) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isCellEditable.test(row, column); // 只有操作列是可编辑的
            }
        };
        return new JTable(dataModel);
    }

    /**
     * 创建一个具有指定列标题、自定义单元格可编辑性和自定义列宽的表格
     *
     * @param titles       表格的列标题
     * @param isCellEditable   一个BiPredicate，用于确定特定单元格是否可编辑
     * @param columnWidths     表格各列的宽度数组
     * @return 返回一个根据指定标题、可编辑性和列宽创建的JTable实例
     */
    public static JTable newTable(String[] titles, BiPredicate<Integer, Integer> isCellEditable, int[] columnWidths) {
        JTable table = newTable(titles, isCellEditable);
        autoWidths(table, columnWidths);
        return table;
    }

    /**
     * 读取 JTable 中的所有数据。
     * @param table 要读取数据的 JTable 实例
     * @return 包含所有行数据的列表，每个元素是一个包含一行数据的列表
     */
    public static List<List<Object>> readAllDataFromTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        List<List<Object>> allData = new ArrayList<>(table.getRowCount());

        for (int i = 0; i < rowCount; i++) {
            List<Object> rowData = new ArrayList<>(table.getColumnCount());
            for (int j = 0; j < columnCount; j++) {
                rowData.add(model.getValueAt(i, j));
            }
            allData.add(rowData);
        }

        return allData;
    }


    /**
     * 向JTable中添加一个新列
     *
     * @param table 表格对象，新列将被添加到该表格中
     * @param modelIndex 列在表格模型中的索引位置
     * @param title 列的标题
     * @param width 列的宽度
     * @param cellRenderer 单元格的渲染器，用于控制列中单元格的显示方式
     * @param cellEditor 单元格的编辑器，用于控制列中单元格的编辑行为
     * @return 返回新添加的TableColumn对象
     */
    public static TableColumn addColumn(JTable table, int modelIndex, String title, int width, TableCellRenderer cellRenderer,
                                        TableCellEditor cellEditor) {
        // 创建一个新的TableColumn对象，并设置其模型索引、宽度、单元格渲染器和编辑器
        TableColumn tableColumn = new TableColumn(
                modelIndex,
                width,
                cellRenderer,
                cellEditor);
        // 设置列的标题
        tableColumn.setHeaderValue(title);
        // 将新列添加到表中
        table.addColumn(tableColumn);
        // 返回新添加的列对象
        return tableColumn;
    }

    /**
     * 设置JTable的各列宽度
     * @param table JTable实例
     * @param widths 列宽度数组，-1表示该列宽度自动计算
     */
    public static void setColumnWidths(JTable table, int[] widths) {
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

   /**
    * 停止给定JTable的单元格编辑功能
    * 此方法通过获取当前正在编辑的单元格编辑器并调用其stopCellEditing方法来实现停止编辑的功能
    * 如果没有单元格正在编辑，则该方法不会进行任何操作
    *
    * @param table 要停止单元格编辑的JTable实例
    */
   public static void stopCellEditing(JTable table) {
       // 获取当前正在编辑的单元格编辑器
       TableCellEditor cellEditor = table.getCellEditor();
       // 如果存在正在编辑的单元格编辑器，则停止单元格编辑
       if (cellEditor!=null) {
           cellEditor.stopCellEditing();
       }
   }

}
