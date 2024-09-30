package github.ag777.common.tool.swing.view;


import github.ag777.common.tool.swing.base.BasePanel;
import github.ag777.common.tool.swing.model.UiProperties;
import github.ag777.common.tool.swing.view.component.loading.LoadingFrame;
import github.ag777.common.tool.swing.view.interf.MainView;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Optional;

public class MainFrame extends LoadingFrame implements MainView {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -4686487273039225125L;

	private BasePanel curPanel;	//当前界面
	private final Menu menu;

	public MainFrame(String viewBasePackage, UiProperties uiProperties) {
		super(uiProperties.getTitle());
		menu = new Menu(this, viewBasePackage, uiProperties.getMenu());
		initView();
		initData();
	}

	public void initView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		setJMenuBar(menu);
		
		this.setSize(800,800);
	}

	public void initData() {
	}

	public void switch2DefaultView() {
		Optional<BasePanel> firstPanel = menu.getFirstPanel();
        firstPanel.ifPresent(this::switchView);
	}

	@Override
	public void switchView(BasePanel panel) {
		setLoading(true);
		try {
			if(curPanel!=null) {
				curPanel.remove();
				remove(curPanel);
			}
			add(panel,BorderLayout.CENTER);
			this.curPanel = panel;
			panel.componentDidMount();
			//重画页面
			validate();
			repaint();
		} finally {
			setLoading(false);
		}
	}
	

}
