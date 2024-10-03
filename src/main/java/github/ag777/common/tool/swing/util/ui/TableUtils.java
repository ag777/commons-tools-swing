package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
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

    @FunctionalInterface
    public interface CellBuilder {
        Component accept(JTable table, Object value,
                                                boolean isSelected, Boolean hasFocus,
                                                int row, int column, boolean isRender);
    }

}
