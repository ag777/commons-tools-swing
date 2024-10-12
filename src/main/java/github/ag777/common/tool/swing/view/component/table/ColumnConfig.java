package github.ag777.common.tool.swing.view.component.table;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.function.BiConsumer;
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

    // 用于获取指定行和列的单元格值的函数接口
    private Function<T, Object> valGetter;

    // 用于设置指定行和列的单元格值的函数接口
    private BiConsumer<T, Object> valSetter;

    // 用于渲染表格单元格的自定义组件
    private TableCellRenderer cellRenderer;

    // 用于编辑表格单元格的自定义组件
    private TableCellEditor cellEditor;
}
