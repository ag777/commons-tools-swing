package github.ag777.common.tool.swing.util.ui;

import java.awt.*;


public class BorderLayoutHelper<T extends Container> {

	private final T mContainer;
	
	public static <T extends Container>BorderLayoutHelper<T> newInstance(T container){
		return new BorderLayoutHelper<>(container);
	}
	
	public BorderLayoutHelper(T container) {
		this.mContainer = container;
		container.setLayout(new BorderLayout());
	}

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
