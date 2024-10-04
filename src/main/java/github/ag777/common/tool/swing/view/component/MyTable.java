package github.ag777.common.tool.swing.view.component;

import github.ag777.common.tool.swing.util.ui.TableUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Vector;
import java.util.function.BiPredicate;

public class MyTable extends JTable {
    // 定义一个不可变的默认表格模型
    private final DefaultTableModel dm;

    /**
     * 构造函数，初始化MyTable对象
     * @param titles 列标题数组
     * @param isCellEditable 用于判断单元格是否可编辑的谓词
     */
    public MyTable(String[] titles, BiPredicate<Integer, Integer> isCellEditable) {
        // 使用默认表格模型初始化super类(JTable)，并设置列标题
        super(new DefaultTableModel(new Object[][]{}, titles) {
            /**
             * 重写isCellEditable方法，以自定义单元格的可编辑性
             * @param row 行索引
             * @param column 列索引
             * @return 如果单元格可编辑则返回true，否则返回false
             */
            @Override
            public boolean isCellEditable(int row, int column) {
                // 使用提供的BiPredicate判断单元格是否可编辑
                return isCellEditable.test(row, column); // 只有操作列是可编辑的
            }
        });
        // 将数据模型转换为DefaultTableModel并赋值给dm
        dm = (DefaultTableModel) dataModel;
    }

    /**
     * 读取 JTable 中的所有数据。
     * @return 包含所有行数据的列表，每个元素是一个包含一行数据的列表
     */
    public List<List<Object>> getAllData() {
        return TableUtils.readAllDataFromTable(this);
    }

    /**
     * 设置JTable的各列宽度
     * @param columnWidths 列宽度数组，-1表示该列宽度自动计算
     */
    public MyTable columnWidths(int[] columnWidths) {
        TableUtils.setColumnWidths(this, columnWidths);
        return this;
    }


    /**
     * 向表格中添加一个新列
     *
     * @param title 列标题
     * @param modelIndex 列在模型中的索引位置
     * @param width 列的宽度
     * @param cellRenderer 单元格的渲染器，用于控制单元格的显示样式
     * @param cellEditor 单元格的编辑器，用于控制单元格的编辑行为
     * @return 返回当前MyTable对象，支持链式调用
     */
    public MyTable addColumn(String title, int modelIndex, int width,
                             TableCellRenderer cellRenderer, TableCellEditor cellEditor) {
        // 调用TableUtils的addColumn方法，将新列添加到当前表格对象中
        // 参数this表示当前表格对象，0表示新列将被插入到表格的最前面，title表示列标题，10表示列宽，cellBuilder表示单元格构建器
        TableUtils.addColumn(this, modelIndex, title, 10, cellRenderer, cellEditor);
        // 支持链式调用
        return this;
    }

    /**
     * 在指定位置插入一个新列
     *
     * @param columnIndex 指定的插入位置索引
     * @param title 列标题
     * @param modelIndex 列在模型中的索引位置
     * @param width 列的宽度
     * @param cellRenderer 单元格的渲染器，用于控制单元格的显示样式
     * @param cellEditor 单元格的编辑器，用于控制单元格的编辑行为
     * @return 返回当前MyTable对象，支持链式调用
     */
    public MyTable insertColumn(int columnIndex, String title, int modelIndex, int width,
                                TableCellRenderer cellRenderer, TableCellEditor cellEditor) {
        // 添加一个新的列，但尚未确定列的插入位置
        addColumn(title, modelIndex, width, cellRenderer, cellEditor);
        // 将新添加的列移动到指定的插入位置
        moveColumn(getColumnCount()-1, columnIndex);
        // 返回当前对象，支持链式调用
        return this;
    }

    /**
     * 设置指定列的单元格渲染器和编辑器
     *
     * @param columIndex 列索引
     * @param cellRenderer 单元格渲染器，用于定制化绘制表格单元格的外观
     * @param cellEditor 单元格编辑器，用于定制化编辑表格单元格的逻辑
     * @return 返回当前的MyTable对象，支持链式调用
     */
    public MyTable setColumn(int columIndex, TableCellRenderer cellRenderer, TableCellEditor cellEditor) {
        // 获取指定索引的列
        TableColumn column = getColumnModel().getColumn(columIndex);
        // 设置列的单元格渲染器
        column.setCellRenderer(cellRenderer);
        // 设置列的单元格编辑器
        column.setCellEditor(cellEditor);
        // 返回当前的MyTable对象，支持链式调用
        return this;
    }

    /**
     * 移动列1到目标列位置
     * 此方法用于在表格中将指定的列1移动到另一个指定的列位置，以实现列的重新排序
     *
     * @param column 当前要移动的列1的索引
     * @param targetColumn 目标列索引，即要移动到的位置
     * @return 返回当前MyTable对象，支持链式调用
     */
    public MyTable moveColumn1(int column, int targetColumn) {
        super.moveColumn(column, targetColumn);
        return this;
    }

    /**
     * 当表格失去焦点时停止编辑器
     * 这个方法通过设置一个默认的单元格编辑器来实现，当编辑器失去焦点时，它会自动停止编辑
     * 主要用于提高表格的用户体验，防止意外的数据修改
     *
     * @return 返回当前的MyTable对象，支持链式调用
     */
    public MyTable stopEditorOnFocusOut() {
        // 设置Object类的默认单元格编辑器
        setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                // 调用父类方法获取单元格编辑器组件
                Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);
                // 为组件添加焦点监听器
                c.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        // 当组件获得焦点时，不执行任何操作
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // 当组件失去焦点时，停止单元格编辑
                        stopCellEditing();
                    }
                });
                return c;
            }
        });
        return this;
    }


    /**
     * 向表格添加新行
     * @param rowData 行数据数组
     */
    public void newRow(Object[] rowData) {
        // 使用dm的addRow方法添加新行
        dm.addRow(rowData);
    }

    /**
     * 在指定位置插入新行
     * @param row 插入位置的行号
     * @param rowData 行数据数组
     */
    public void insertRow(int row, Object[] rowData) {
        dm.insertRow(row, rowData);
    }

    /**
     * 删除指定行
     *
     * @param row 要删除的行的索引
     */
    public void removeRow(int row) {
        dm.removeRow(row);
    }

    /**
     * 清空表格中的所有数据
     * 此方法通过清除数据向量中的内容并通知表格数据已更改来实现清空
     */
    public void clear() {
        dm.getDataVector().clear();
        dm.fireTableDataChanged();
    }

    /**
     * 设置表格的数据为给定的二维对象数组
     * 此方法首先清空当前表格的数据，然后将新的数据行按顺序添加到表格中
     *
     * @param list 二维对象数组，包含要设置到表格中的数据
     */
    public void setData(Object[][] list) {
        clear(); // 清空原有数据
        for (Object[] row : list) {
            dm.addRow(row); // 添加新的数据行
        }
    }

    /**
     * 设置表格的数据为给定的Vector类型的二维列表
     * 此方法首先清空当前表格的数据，然后将新的数据行按顺序添加到表格中
     *
     * @param list Vector类型的二维列表，包含要设置到表格中的数据
     */
    public <T>void setData(Vector<Vector<T>> list) {
        clear(); // 清空原有数据
        for (Vector<T> l : list) {
            dm.addRow(l); // 添加新的数据行
        }
    }

    /**
     * 停止当前的单元格编辑
     * 此方法通过TableUtils停止任何正在进行的单元格编辑
     */
    public void stopCellEditing() {
        TableUtils.stopCellEditing(this);
    }
}
