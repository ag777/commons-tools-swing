package github.ag777.common.tool.swing.view.component;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * JComboBox二次封装
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/11/15 下午2:39
 */
public class MyComboBox<E> extends JComboBox<E> {

    public MyComboBox(ComboBoxModel<E> model) {
        super(model);
    }

    public MyComboBox(E[] items) {
        super(items);
    }

    public MyComboBox(Vector<E> items) {
        super(items);
    }

    public MyComboBox() {
    }

    /**
     * 设置组合框的渲染器，用于自定义每个项目的显示内容
     * 此方法允许通过提供一个ItemStrRender函数式接口的实现来定义如何渲染每个项目
     *
     * @param renderer 实现了ItemStrRender接口的渲染器，用于设置每个列表项的显示文本
     * @return 返回当前的MyComboBox实例，支持链式调用
     */
    public MyComboBox<E> itemStrRenderer(Function<E, String> renderer) {
        // 设置自定义的列表单元格渲染器
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // 调用父类方法初始化组件
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // 使用提供的渲染器转换值为显示文本
                setText(renderer.apply((E) value));;
                return this;
            }
        });
        return this;
    }

    /**
     * 设置组合框的渲染器，用于自定义每个项目的显示内容
     * 此方法允许通过提供一个Function函数式接口的实现来定义如何渲染每个项目
     * 与前一个方法不同，这个方法直接返回一个Component对象，提供了更大的灵活性
     *
     * @param renderer 实现了Function接口的渲染器，用于设置每个列表项的显示组件
     * @return 返回当前的MyComboBox实例，支持链式调用
     */
    public MyComboBox<E> itemRenderer(Function<E, Component> renderer) {
        // 设置自定义的列表单元格渲染器
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // 直接使用提供的渲染器生成组件
                return renderer.apply((E) value);
            }
        });
        return this;
    }

    /**
     * 设置选中项时的回调操作
     * 当用户选择下拉框中的一个项时，会触发此回调方法
     * 主要用于处理选中事件，将选中的项传递给回调函数
     *
     * @param onSelected 一个消费型函数，用于处理选中的项
     * @return 返回当前的MyComboBox实例，支持链式调用
     */
    public MyComboBox<E> onSelected(Consumer<E> onSelected) {
        addActionListener(e-> onSelected.accept(getSelectedItem()));
        return this;
    }

    /**
     * 向组合框中添加多个元素
     *
     * @param items 要添加的元素集合
     * @return 返回当前组合框实例，支持链式调用
     */
    public MyComboBox<E> addItems(Collection<E> items) {
        for (E item : items) {
            addItem(item);
        }
        return this;
    }

    /**
     * 设置组合框中的元素
     * 此方法会先清除当前所有元素，再添加新元素
     *
     * @param items 要设置的元素集合
     * @return 返回当前组合框实例，支持链式调用
     */
    public MyComboBox<E> items(Collection<E> items) {
        removeAllItems();
        addItems(items);
        return this;
    }

    /**
     * 选择指定元素
     *
     * @param item 要选择的元素
     * @return 返回当前组合框实例，支持链式调用
     */
    public MyComboBox<E> select(E item) {
        setSelectedItem(item);
        return this;
    }

    /**
     * 通过索引选择元素
     *
     * @param index 要选择的元素的索引
     * @return 返回当前组合框实例，支持链式调用
     */
    public MyComboBox<E> select(int index) {
        setSelectedIndex(index);
        return this;
    }

    /**
     * 获取当前选择的元素
     * 此方法用于从组件中获取用户当前选择的项
     * 它封装了对getSelectedItem方法的调用，并确保返回值是泛型E的实例
     *
     * @return E 当前选择的元素，其类型为E
     * @see #getSelectedItem()
     */
    public E getSelectedItem() {
        return (E) super.getSelectedItem();
    }


}
