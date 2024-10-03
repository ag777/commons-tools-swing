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

	public BasePanel(UiProperties.MenuItem config){
		super(new JPanel());
		setLoading(true);
		try {
			initView(component, config);
			initData(config);
		} finally {
			setLoading(false);
		}
	}

	/**
	 * 切换完成时调用
	 */
	public void componentDidMount() {
	}

	public void remove() {
		BasePresenter<?> mPresenter = getPresenter();
		if(mPresenter != null) {
			mPresenter.detachView();
		}
	}

	protected abstract void initView(JPanel container, UiProperties.MenuItem config);		//初始化视图

	protected abstract void initData(UiProperties.MenuItem config);		//初始化事件/数据

	public abstract BasePresenter<?> getPresenter();
	
}
