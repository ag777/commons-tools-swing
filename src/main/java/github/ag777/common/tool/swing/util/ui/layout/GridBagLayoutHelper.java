package github.ag777.common.tool.swing.util.ui.layout;

import javax.swing.*;
import java.awt.*;

/**
 * 支持链式调用的包布局辅助类
 * @author ag777
 *
 */
public class GridBagLayoutHelper<T extends Container> {

	private T mContainer;
	private GridBagLayout gr;
	private GridBagConstraints gc;

	/**
	 * 创建一个GridBagLayoutHelper实例，用于辅助JPanel容器的GridBagLayout布局管理
	 * 该方法通过泛型指定容器类型为JPanel，并返回一个针对JPanel类型的GridBagLayoutHelper实例
	 *
	 * @return GridBagLayoutHelper<JPanel>类型的实例，用于辅助JPanel的布局管理
	 */
	public static GridBagLayoutHelper<JPanel> panel(){
		return new GridBagLayoutHelper<>(new JPanel());
	}

	/**
	 * 泛型方法，创建一个GridBagLayoutHelper实例，用于辅助任意继承自Container的容器类型
	 * 该方法允许外部指定容器类型T，并返回一个针对该容器类型的GridBagLayoutHelper实例
	 *
	 * @param container 要进行布局管理的容器实例，必须继承自Container
	 * @param <T> 容器的类型，必须继承自Container
	 * @return GridBagLayoutHelper<T>类型的实例，用于辅助指定容器的布局管理
	 */
	public static <T extends Container>GridBagLayoutHelper<T> newInstance(T container){
		return new GridBagLayoutHelper<>(container);
	}

	/**
	 * 构造函数，初始化GridBagLayoutHelper实例
	 * 该构造函数接受一个容器实例，并为该容器设置GridBagLayout布局类型
	 * 同时初始化GridBagConstraints对象，用于后续控制组件在容器中的布局属性
	 *
	 * @param container 要进行布局管理的容器实例，必须继承自Container
	 */
	public GridBagLayoutHelper(T container){
		mContainer = container;

		gr = new GridBagLayout(); // 创建GridBagLayout实例，并设置给容器
		mContainer.setLayout(gr);
		gc = new GridBagConstraints(); // 初始化GridBagConstraints对象，用于控制布局
	}

	/**
	 * 获取GridBagLayoutHelper管理的容器实例
	 *
	 * @return 返回被管理的容器实例，类型为泛型T
	 */
	public T get() {
		return mContainer;
	}
	
	/**
	 * 添加一个组件到布局中
	 *
	 * @param view 要添加的组件
	 * @param row 组件所在的行号
	 * @param col 组件所在的列号
	 *
	 * @return 返回GridBagLayoutHelper的实例，支持链式调用
	 */
	public GridBagLayoutHelper<T> addComponent(Component view, int row, int col){
	    return addComponent(view, row, col, 1, 1, 1, 1);
	}

	/**
	 * 添加组件到网格布局中
	 *
	 * @param view 组件，要添加到网格布局中的GUI元素
	 * @param row 行，组件在网格中的行位置
	 * @param col 列，组件在网格中的列位置
	 * @param gridwidth 网格宽度，组件在网格中占用的列数
	 * @param gridheight 网格高度，组件在网格中占用的行数
	 * @param weightX 水平权重，组件在水平方向上扩展的权重
	 * @param weightY 垂直权重，组件在垂直方向上扩展的权重
	 * @return 返回当前GridBagLayoutHelper实例，支持链式调用
	 */
	public GridBagLayoutHelper<T> addComponent(Component view, int row, int col, int gridwidth, int gridheight, int weightX, int weightY){
	    // 设置组件在网格中的位置
	    gc.gridx= col;
	    gc.gridy = row;
	    // 组件占用单元格
	    gc.gridwidth = gridwidth;
	    gc.gridheight = gridheight;
	    // 设置组件的权重，用于动态分配空间
	    gc.weightx = weightX;
	    gc.weighty = weightY;
	    // 设置组件填充方式，BOTH表示同时填充水平和垂直方向
	    gc.fill = GridBagConstraints.BOTH;

	    // 设置组件的约束
	    gr.setConstraints(view,gc);
	    // 将组件添加到容器中
	    mContainer.add(view);

	    return this;
	}
}
