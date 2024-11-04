package github.ag777.common.tool.swing.view.component.table;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/10/10 下午5:15
 */
@Data
@Accessors(chain = true, fluent = true)
public class ColumnConfig<T> {
    // 表示表格列的标题
    private String title;

    // 列宽度
    private Integer width;

    // 水平对齐方式
    private int alignHorizontal= SwingConstants.CENTER;

    // 用于获取指定行和列的单元格值的函数接口
    private BiFunction<T, Integer, Object> valGetter;

    // 用于设置指定行和列的单元格值的函数接口
    private ValSetter<T> valSetter;

    // 用于渲染表格单元格的自定义组件
    private TableCellRenderer cellRenderer;

    // 用于编辑表格单元格的自定义组件
    private TableCellEditor cellEditor;

    public ColumnConfig<T> valGetter(BiFunction<T, Integer, Object> valGetter) {
        this.valGetter = valGetter;
        return this;
    }

    public ColumnConfig<T> valGetter(Function<T, Object> valGetter) {
        this.valGetter = (item, index)-> valGetter.apply(item);
        return this;
    }

    public ColumnConfig<T> valSetter(ValSetter<T> valSetter) {
        this.valSetter = valSetter;
        return this;
    }

    public ColumnConfig<T> valSetter(ValSetterSimple<T> valSetter) {
        this.valSetter = valSetter;
        return this;
    }

    public ColumnConfig<T> alignLeft() {
        alignHorizontal = SwingConstants.LEFT;
        return this;
    }

    public ColumnConfig<T> alignRight() {
        alignHorizontal = SwingConstants.RIGHT;
        return this;
    }

    @FunctionalInterface
    public interface ValSetter<T> {
        Object apply(T rowItem, Object val, List<T> list, int rowIndex, int colIndex);
    }

    @FunctionalInterface
    public interface ValSetterSimple<T> extends ValSetter<T> {
        Object apply(T rowItem, Object val);

        @Override
        default Object apply(T rowItem, Object val, List<T> list, int rowIndex, int colIndex) {
            return apply(rowItem, val);
        }
    }
}
