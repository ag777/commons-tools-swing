package github.ag777.common.tool.swing.view.component;

import com.ag777.util.lang.collection.ListUtils;
import github.ag777.common.tool.swing.util.ui.TableUtils;
import github.ag777.common.tool.swing.view.component.table.ColumnConfig;
import github.ag777.common.tool.swing.view.component.table.CustomTableModel;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/10/10 下午5:45
 */
public class CustomTable<T> extends JTable {
    @Getter
    private final CustomTableModel<T> model;

    public CustomTable(List<ColumnConfig<T>> configs) {
        this.model = new CustomTableModel<>(configs);
        setModel(model);
        columnWidths(
                configs.stream()
                        .mapToInt(c->c.width()!=null?c.width():-1)
                        .toArray()
        );
    }

    public CustomTable<T> setData(List<T> list) {
        model.setData(list);
        return this;
    }

    public CustomTable<T> columnWidths(int[] columnWidths) {
        TableUtils.setColumnWidths(this, columnWidths);
        return this;
    }

    public void fireTableDataChanged() {
        model.fireTableDataChanged();
    }

    public List<T> getAllData() {
        return model.getList();
    }

    public void addRow(T row) {
        model.getList().add(row);
    }

    public void removeRow(int rowNo) {
        model.getList().remove(rowNo);
    }

    public void removeIf(Predicate<? super T> filter) {
        model.getList().removeIf(filter);
    }

    public T getRowData(int rowNo) {
        return ListUtils.get(model.getList(), rowNo);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer renderer = model.getCellRenderer(column);
        return renderer != null ? renderer : super.getCellRenderer(row, column);
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        TableCellEditor editor = model.getCellEditor(column);
        return editor != null ? editor : super.getCellEditor(row, column);
    }
}
