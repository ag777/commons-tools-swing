package github.ag777.common.tool.swing.util.ui;

import github.ag777.common.tool.swing.util.ui.layout.GridBagLayoutHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * JRadioButton链式调用封装
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/11/5 上午9:32
 */
public class RadioButtonHelper<T extends Container> {
    private final T mContainer;
    private final GridBagLayoutHelper<T> layout;
    private final ButtonGroup buttonGroup;
    private boolean vertical;

    public RadioButtonHelper(T mContainer) {
        this.vertical = false;
        this.mContainer = mContainer;
        this.buttonGroup = new ButtonGroup();
        this.layout = new GridBagLayoutHelper<>(mContainer);
    }

    public static RadioButtonHelper<JPanel> panel(){
        return new RadioButtonHelper<>(new JPanel());
    }

    public T get() {
        return mContainer;
    }

    public RadioButtonHelper<T> vertical() {
        this.vertical = true;
        return this;
    }

    public RadioButtonHelper<T> select(int index) {
        if (index>=buttonGroup.getButtonCount()) {
            return this;
        }
        int i=0;
        Iterator<AbstractButton> iter = buttonGroup.getElements().asIterator();
        while (iter.hasNext()) {
            AbstractButton btn = iter.next();
            if (i == index) {
                btn.setSelected(true);
                return this;
            }
            i++;
        }
        return this;
    }

    public RadioButtonHelper<T> select(Predicate<AbstractButton> filter) {
        Iterator<AbstractButton> iter = buttonGroup.getElements().asIterator();
        while (iter.hasNext()) {
            AbstractButton btn = iter.next();
            if (filter.test(btn)) {
                btn.setSelected(true);
                return this;
            }
        }
        return this;
    }

    public RadioButtonHelper<T> add(String text, BiConsumer<AbstractButton, Integer> onSelected) {
        return add(new JRadioButton(text), onSelected);
    }

    public RadioButtonHelper<T> add(String text, BiConsumer<AbstractButton, Integer> onSelected, int row, int col) {
        return add(new JRadioButton(text), onSelected, row, col);
    }

    public RadioButtonHelper<T> add(AbstractButton btn, BiConsumer<AbstractButton, Integer> onSelected) {
        int componentCount = buttonGroup.getButtonCount();
        if (!vertical) {
            add(btn, onSelected, 0, componentCount);
        } else {
            add(btn, onSelected, componentCount, 0);
        }
        return this;
    }

    public RadioButtonHelper<T> add(AbstractButton btn, BiConsumer<AbstractButton, Integer> onSelected, int row, int col) {
        layout.addComponent(btn, row, col);
        int index = buttonGroup.getButtonCount()-1;
        if (onSelected != null) {
            btn.addItemListener(e->{
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    onSelected.accept(btn, index);
                }
            });
        }
        buttonGroup.add(btn);
        return this;
    }


}
