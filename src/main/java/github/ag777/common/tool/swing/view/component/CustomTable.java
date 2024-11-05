package github.ag777.common.tool.swing.view.component;

import com.ag777.util.lang.collection.ListUtils;
import github.ag777.common.tool.swing.util.ui.TableUtils;
import github.ag777.common.tool.swing.view.component.table.ColumnConfig;
import github.ag777.common.tool.swing.view.component.table.CustomTableModel;
import lombok.Getter;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/10/10 下午5:45
 */
public class CustomTable<T> extends JTable {
    @Getter
    private CustomTableModel<T> model;

    public CustomTable(List<ColumnConfig<T>> configs) {
        setConfig(configs);
    }

    public CustomTable(List<T> list, List<ColumnConfig<T>> configs) {
        this(configs);
        setData(list);
    }

    public CustomTable<T> setData(List<T> list) {
        return setList(list);
    }

    public CustomTable<T> setList(List<T> list) {
        model.setData(list);
        return this;
    }

    public CustomTable<T> setConfig(List<ColumnConfig<T>> configs) {
        this.model = new CustomTableModel<>(configs);
        setModel(model);
        columnWidths(
                configs.stream()
                        .mapToInt(c->c.width()!=null?c.width():-1)
                        .toArray()
        );
        return this;
    }

    public CustomTable<T> setConfig(List<ColumnConfig<T>> configs, List<T> list) {
        this.model = new CustomTableModel<>(list, configs);
        setModel(model);
        columnWidths(
                configs.stream()
                        .mapToInt(c->c.width()!=null?c.width():-1)
                        .toArray()
        );
        return this;
    }

    /**
     * 设置表格各列的宽度
     *
     * @param columnWidths 一个整型数组，定义了每列的宽度
     * @return 返回CustomTable实例，用于链式调用
     */
    public CustomTable<T> columnWidths(int[] columnWidths) {
        TableUtils.setColumnWidths(this, columnWidths);
        return this;
    }

    /**
     * 禁用列的移动功能
     *
     * @return 返回CustomTable实例，用于链式调用
     */
    public CustomTable<T> disableMoveColumn() {
        getTableHeader().setReorderingAllowed(false);
        return this;
    }

    /**
     * 禁用列的拉动调整列宽功能
     *
     * @return 返回CustomTable实例，用于链式调用
     */
    public CustomTable<T> disableResizingColumn() {
        getTableHeader().setResizingAllowed(false);
        return this;
    }

    /**
     * 通知所有监听器，表格数据已更改
     * 此方法通常在多处数据发生变化时调用，以重新计算并显示所有数据
     */
    public void fireTableDataChanged() {
        model.fireTableDataChanged();
    }

    /**
     * 通知所有监听器，表格中特定行已更新
     *
     * @param row 更新的行号
     */
    public void fireTableRowUpdated(int row) {
        model.fireTableRowsUpdated(row, row);
    }

    /**
     * 通知所有监听器，表格中一行或多行已更新
     *
     * @param firstRow 更新的第一行号
     * @param lastRow 更新的最后一行号
     */
    public void fireTableRowsUpdated(int firstRow, int lastRow) {
        model.fireTableRowsUpdated(firstRow, lastRow);
    }

    /**
     * 通知所有监听器，表格中特定单元格已更新
     *
     * @param row 更新的行号
     * @param column 更新的列号
     */
    public void fireTableCellUpdated(int row, int column) {
        model.fireTableCellUpdated(row, column);
    }

    /**
     * 通知所有监听器，表格中插入了一行或多行
     *
     * @param firstRow 插入的第一行号
     * @param lastRow 插入的最后一行号
     */
    public void fireTableRowsInserted(int firstRow, int lastRow) {
        model.fireTableRowsInserted(firstRow, lastRow);
    }

    /**
     * 通知所有监听器，表格中插入了一行
     *
     * @param row 插入的行号
     */
    public void fireTableRowInserted(int row) {
        model.fireTableRowsInserted(row, row);
    }

    /**
     * 通知所有监听器，表格中删除了一行或多行
     *
     * @param firstRow 删除的第一行号
     * @param lastRow 删除的最后一行号
     */
    public void fireTableRowsDeleted(int firstRow, int lastRow) {
        model.fireTableRowsDeleted(firstRow, lastRow);
    }

    /**
     * 通知所有监听器，表格中删除了一行
     *
     * @param row 删除的行号
     */
    public void fireTableRowDeleted(int row) {
        model.fireTableRowsDeleted(row, row);
    }

    /**
     * 通知所有监听器，表格结构或数据已更改
     *
     * @param e TableModelEvent事件，提供更改的详细信息
     */
    public void fireTableChanged(TableModelEvent e) {
        model.fireTableChanged(e);
    }

    /**
     * 获取表格中的所有数据
     *
     * @return 返回一个包含表格所有数据的List
     */
    public List<T> getAllData() {
        return model.getList();
    }

    /**
     * 向表格中添加一行数据
     *
     * @param row 要添加的数据行对象
     */
    public void addRow(T row) {
        model.addRow(row);
    }

    /**
     * 从表格中删除指定行
     *
     * @param rowNo 要删除的行号
     */
    public void removeRow(int rowNo) {
        model.removeRow(rowNo);
    }

    /**
     * 使用Predicate过滤器删除表格中的行
     *
     * @param filter Predicate过滤器，用于确定要删除的行
     */
    public void removeIf(Predicate<? super T> filter) {
        model.removeRowIf(filter);
    }

    /**
     * 获取表格中指定行的数据
     *
     * @param rowNo 要获取数据的行号
     * @return 返回指定行的数据对象
     */
    public T getRowData(int rowNo) {
        return ListUtils.get(model.getList(), rowNo);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        column = convertColumnIndexToModel(column);
        TableCellRenderer renderer = model.getCellRenderer(column);
        if (renderer == null) {
            renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    // 调用父类方法来获取渲染组件
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    // 设置水平对齐
                    setHorizontalAlignment(model.getAlignHorizontal(column));
                    // 设置垂直居中
                    setVerticalAlignment(SwingConstants.CENTER);
                    return this;
                }
            };
        }
        return renderer;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        column = convertColumnIndexToModel(column);
        TableCellEditor editor = model.getCellEditor(column);
        return editor != null ? editor : super.getCellEditor(row, column);
    }
}
