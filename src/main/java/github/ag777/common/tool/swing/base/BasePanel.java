package github.ag777.common.tool.swing.base;


import github.ag777.common.tool.swing.model.UiProperties;
import github.ag777.common.tool.swing.view.component.loading.LoadingComponent;

import javax.swing.*;
import java.io.Serial;

public abstract class BasePanel extends LoadingComponent<JPanel> {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -4818485273815570584L;

	/**
	 * 构造函数，用于初始化BasePanel
	 *
	 * @param config UiProperties.MenuItem对象，包含菜单项的配置信息
	 */
	public BasePanel(UiProperties.MenuItem config){
	    super(new JPanel()); // 调用父类JPanel的构造函数初始化组件
	    setLoading(true); // 设置组件为加载中状态，通常用于显示加载动画
	    try {
	        initView(component, config); // 初始化视图，具体实现由子类提供
	        initData(config); // 初始化数据或事件，具体实现由子类提供
	    } finally {
	        setLoading(false); // 确保无论成功与否，组件最终处于非加载中状态
	    }
	}

	/**
	 * 组件挂载完成后调用，用于执行组件挂载后需要进行的操作
	 * 此方法在组件完全加载并渲染到DOM后被调用，可以用于初始化与组件相关的操作
	 */
	public void componentDidMount() {
	}

	/**
	 * 移除组件时调用，主要用于解绑事件或者清理资源
	 */
	public void remove() {
	    BasePresenter<?> mPresenter = getPresenter(); // 获取与组件关联的Presenter实例
	    if(mPresenter != null) {
	        mPresenter.detachView(); // 如果Presenter实例存在，则解绑View，进行资源清理
	    }
	}

	/**
	 * 获取当前组件所在的JFrame窗口
	 *
	 * @return 当前组件所在的JFrame实例，如果不存在则返回null
	 */
	protected JFrame getFrame() {
	    return (JFrame) SwingUtilities.getWindowAncestor(this); // 获取当前组件所属的顶级窗口（JFrame）
	}

	/**
	 * 初始化视图，需要由子类实现
	 *
	 * @param container 包含视图的JPanel容器
	 * @param config    菜单项的配置信息，用于指导视图的初始化
	 * 此方法用于设置组件的界面布局和元素，是组件初始化过程的一部分
	 */
	protected abstract void initView(JPanel container, UiProperties.MenuItem config);

	/**
	 * 初始化事件/数据，需要由子类实现
	 *
	 * @param config 菜单项的配置信息，用于指导数据或事件的初始化
	 * 此方法用于初始化组件相关的数据或事件处理逻辑，是组件初始化过程的一部分
	 */
	protected abstract void initData(UiProperties.MenuItem config);

	/**
	 * 获取与组件关联的Presenter实例，需要由子类实现
	 *
	 * @return 组件关联的BasePresenter的子类实例
	 * 此方法用于返回负责组件业务逻辑处理的Presenter实例，以便进行MVC模式下的逻辑处理
	 */
	public abstract BasePresenter<?> getPresenter();
	
}
