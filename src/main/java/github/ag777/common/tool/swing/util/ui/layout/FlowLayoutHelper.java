package github.ag777.common.tool.swing.util.ui.layout;

import javax.swing.*;
import java.awt.*;


public class FlowLayoutHelper<T extends Container> {

	private final T mContainer;
	private final FlowLayout layout;

	/**
	 * 创建一个FlowLayoutHelper实例，该实例封装了一个JPanel容器
	 * 此方法提供了一种简便的方式来自动生成并布局一个面板
	 *
	 * @return 返回一个FlowLayoutHelper实例，用于操作和布局JPanel容器
	 */
	public static FlowLayoutHelper<JPanel> panel(){
		return new FlowLayoutHelper<>(new JPanel());
	}

	/**
	 * 泛型方法，创建一个FlowLayoutHelper实例，该实例封装了任意类型的Container容器
	 * 此方法允许用户指定容器类型，提供了更高的灵活性和可扩展性
	 *
	 * @param container 要应用FlowLayout布局的容器实例
	 * @param <T>       容器类型，必须是Container的子类
	 * @return 返回一个FlowLayoutHelper实例，用于操作和布局指定类型的容器
	 */
	public static <T extends Container> FlowLayoutHelper<T> newInstance(T container){
		return new FlowLayoutHelper<>(container);
	}

	/**
	 * 构造函数，初始化FlowLayoutHelper时，将应用FlowLayout布局到传入的容器中
	 *
	 * @param container 要封装的容器实例，可以是任何实现了Container接口的类
	 */
	public FlowLayoutHelper(T container) {
		this.mContainer = container;
		this.layout = new FlowLayout();
		container.setLayout(layout);
	}

	/**
	 * 获取容器中存储的元素
	 *
	 * @return 返回容器中存储的元素
	 */
	public T get() {
		return mContainer;
	}
	
	/**
	 * 添加组件到容器，并指定布局约束
	 *
	 * @param view 要添加的组件
	 * @return 返回当前FlowLayoutHelper实例，以便进行链式调用
	 */
	public FlowLayoutHelper<T> add(Component view) {
	    mContainer.add(view);
	    return this;
	}

	public FlowLayoutHelper<T> alignLeft() {
		layout.setAlignment(FlowLayout.LEFT);
		return this;
	}

	public FlowLayoutHelper<T> alignCenter() {
		layout.setAlignment(FlowLayout.CENTER);
		return this;
	}

	public FlowLayoutHelper<T> alignRight() {
		layout.setAlignment(FlowLayout.RIGHT);
		return this;
	}

	public FlowLayoutHelper<T> alignLeading() {
		layout.setAlignment(FlowLayout.LEADING);
		return this;
	}

	public FlowLayoutHelper<T> alignTrailing() {
		layout.setAlignment(FlowLayout.TRAILING);
		return this;
	}
	
}
