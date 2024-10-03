package github.ag777.common.tool.swing.util.ui.layout;

import javax.swing.*;
import java.awt.*;


public class BorderLayoutHelper<T extends Container> {

	private final T mContainer;

	/**
	 * 创建一个BorderLayoutHelper实例，该实例封装了一个JPanel容器
	 * 此方法提供了一种简便的方式来自动生成并布局一个面板
	 *
	 * @return 返回一个BorderLayoutHelper实例，用于操作和布局JPanel容器
	 */
	public static BorderLayoutHelper<JPanel> panel(){
		return new BorderLayoutHelper<>(new JPanel());
	}

	/**
	 * 泛型方法，创建一个BorderLayoutHelper实例，该实例封装了任意类型的Container容器
	 * 此方法允许用户指定容器类型，提供了更高的灵活性和可扩展性
	 *
	 * @param container 要应用BorderLayout布局的容器实例
	 * @param <T>       容器类型，必须是Container的子类
	 * @return 返回一个BorderLayoutHelper实例，用于操作和布局指定类型的容器
	 */
	public static <T extends Container>BorderLayoutHelper<T> newInstance(T container){
		return new BorderLayoutHelper<>(container);
	}

	/**
	 * 构造函数，初始化BorderLayoutHelper时，将应用BorderLayout布局到传入的容器中
	 *
	 * @param container 要封装的容器实例，可以是任何实现了Container接口的类
	 */
	public BorderLayoutHelper(T container) {
		this.mContainer = container;
		container.setLayout(new BorderLayout());
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
	 * @param constraints 布局约束，用于指定组件在容器中的位置
	 * @return 返回当前BorderLayoutHelper实例，以便进行链式调用
	 */
	public BorderLayoutHelper<T> addComponent(Component view, Object constraints) {
	    mContainer.add(view, constraints);
	    return this;
	}

	/**
	 * 将组件添加到容器的中心区域
	 *
	 * @param view 要添加的组件
	 * @return 返回当前BorderLayoutHelper实例，以便进行链式调用
	 */
	public BorderLayoutHelper<T> addComponent2Center(Component view) {
	    return addComponent(view, BorderLayout.CENTER);
	}

	/**
	 * 将组件添加到容器的北部区域
	 *
	 * @param view 要添加的组件
	 * @return 返回当前BorderLayoutHelper实例，以便进行链式调用
	 */
	public BorderLayoutHelper<T> addComponent2North(Component view) {
	    return addComponent(view, BorderLayout.NORTH);
	}

	/**
	 * 将组件添加到容器的南部区域
	 *
	 * @param view 要添加的组件
	 * @return 返回当前BorderLayoutHelper实例，以便进行链式调用
	 */
	public BorderLayoutHelper<T> addComponent2South(Component view) {
	    return addComponent(view, BorderLayout.SOUTH);
	}

	/**
	 * 将组件添加到容器的东部区域
	 *
	 * @param view 要添加的组件
	 * @return 返回当前BorderLayoutHelper实例，以便进行链式调用
	 */
	public BorderLayoutHelper<T> addComponent2East(Component view) {
	    return addComponent(view, BorderLayout.EAST);
	}

	/**
	 * 将组件添加到容器的西部区域
	 *
	 * @param view 要添加的组件
	 * @return 返回当前BorderLayoutHelper实例，以便进行链式调用
	 */
	public BorderLayoutHelper<T> addComponent2West(Component view) {
	    return addComponent(view, BorderLayout.WEST);
	}
	
}
