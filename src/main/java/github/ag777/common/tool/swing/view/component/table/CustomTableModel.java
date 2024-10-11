package github.ag777.common.tool.swing.view.component.table;

import lombok.Getter;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/10/10 下午5:31
 */
public class CustomTableModel<T> extends AbstractTableModel {
    @Getter
    private final List<T> list;
    @Getter
    private final List<ColumnConfig<T>> columnConfigs;

    public CustomTableModel(List<ColumnConfig<T>> columnConfigs) {
        this.list = new ArrayList<>();
        this.columnConfigs = columnConfigs;
    }

    public CustomTableModel(List<T> list, List<ColumnConfig<T>> columnConfigs) {
        this.list = new ArrayList<>();
        this.columnConfigs = columnConfigs;
        if (list != null && !list.isEmpty()) {
            this.list.addAll(list);
        }
    }

    public void setData(List<T> list) {
        this.list.addAll(list);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnConfigs.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnConfigs.get(column).title();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T item = list.get(rowIndex);
        Function<T, Object> getter = columnConfigs.get(columnIndex).valGetter();
        if (getter == null) {
            return null;
        }
        return getter.apply(item);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnConfigs.get(columnIndex).cellEditor() != null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // 根据需求实现设置值的方法
        BiConsumer<T, Object> valSetter = columnConfigs.get(columnIndex).valSetter();
        if (valSetter != null) {
            valSetter.accept(list.get(rowIndex), aValue);
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // 返回列中对象的类
        for (int i = 0; i < getRowCount(); i++) {
            if (getValueAt(i, columnIndex) != null) {
                return getValueAt(i, columnIndex).getClass();
            }
        }
        return Object.class;
    }

    public TableCellRenderer getCellRenderer(int columnIndex) {
        return columnConfigs.get(columnIndex).cellRenderer();
    }

    public TableCellEditor getCellEditor(int columnIndex) {
        return columnConfigs.get(columnIndex).cellEditor();
    }
}
