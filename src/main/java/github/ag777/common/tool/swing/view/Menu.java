package github.ag777.common.tool.swing.view;


import com.ag777.util.lang.exception.model.ValidateException;
import github.ag777.common.tool.swing.base.BasePanel;
import github.ag777.common.tool.swing.model.UiProperties;
import github.ag777.common.tool.swing.util.MenuItemPanelCreator;
import github.ag777.common.tool.swing.util.UiConfigUtils;
import github.ag777.common.tool.swing.util.ui.DialogUtils;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class Menu extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2431174444645849642L;

	private final MainFrame mContainer;
	
	private final JMenu menu_tool = new JMenu("工具");
		private final JMenuItem item_help = new JMenuItem("帮助");

		private final JMenuItem item_about = new JMenuItem("关于");
	
	private final JMenu menu_module = new JMenu("模块切换");

	private final Map<JMenuItem, UiProperties.MenuItem> menuItemConfigMap;

	/** 面板类的基础路径 */
	private final String viewBasePackage;

	public Menu(MainFrame mainFrame, String viewBasePackage, UiProperties.Menu config) {
		super();
		this.viewBasePackage = viewBasePackage;
		mContainer = mainFrame;
		menuItemConfigMap = new HashMap<>(config.getItems().size());

		initView(config.getConfig(), config.getItems());
		
		initData();
	}

	public void initView(UiProperties.UiConfig uiConfig, List<UiProperties.MenuItem> menuItems) {
		menu_tool.add(item_help);
		menu_tool.add(item_about);
		
			add(menu_tool);

		// 初始化菜单
		for (UiProperties.MenuItem menuItem : menuItems) {
			JMenuItem mi = new JMenuItem(menuItem.getName());
			menuItemConfigMap.put(mi, menuItem);
			menu_module.add(mi);
		}
		add(menu_module);

		initFontSize(
				uiConfig.getFont(),
				menu_tool, menu_module,
				item_help, item_about
		);
	}

	private void initFontSize(UiProperties.Font fontConfig, JComponent... components) {
		for (JComponent component : components) {
			UiConfigUtils.initFontSize(component, fontConfig);
		}

	}

	public void initData() {
		item_help.addActionListener(this);

		item_about.addActionListener(this);
		// 给菜单项增加监听
		for (JMenuItem item : menuItemConfigMap.keySet()) {
			item.addActionListener(this);
		}
	}

	private void showHelp() {
		String basicMsg = "暂无";

		JPanel panel_help = new JPanel();
		JTextArea basic = new JTextArea(basicMsg);

		basic.setEditable(false);
		basic.setLineWrap(true);
		basic.setPreferredSize(new Dimension(580, 190));

		basic.setBorder(new TitledBorder(new EtchedBorder(), "基本操作"));
		panel_help.add(basic);


		DialogUtils.showDialog(mContainer, "帮助", panel_help,new Dimension(600, 240));
	}
	

	
	private void showAbout() {
		Map<String, String> map = new HashMap<>();
		
		map.put("版本", "1.0.0");
		map.put("编译环境", "JDK 21.0.2");
		map.put("作者", "ag777");

		DialogUtils.showDialog(mContainer, "关于", map, new Dimension(150, 120));
	}

	public Optional<BasePanel> getFirstPanel() {
		Optional<JMenuItem> jMenuItem = menuItemConfigMap.keySet().stream().findFirst();
		try {
			if (jMenuItem.isPresent()) {
				return Optional.of(MenuItemPanelCreator.getPanel(viewBasePackage, menuItemConfigMap.get(jMenuItem.get())));
			}
		} catch (ValidateException e) {
			log.error("实例化模块失败: {}", e.getMessage());
		}
		return Optional.empty();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object v = e.getSource();
		if (!(v instanceof JMenuItem)) {
			return;
		}
		if(item_help.equals(v)) {
			showHelp();
		} else if(item_about.equals(v)) {
			showAbout();
		}
		if (menuItemConfigMap.containsKey(v)) {
			try {
				BasePanel panel = MenuItemPanelCreator.getPanel(viewBasePackage, menuItemConfigMap.get(v));
				mContainer.switchView(panel);
			} catch (ValidateException ex) {
				DialogUtils.showWarningDialog(
						mContainer,
						"错误",
						ex.getMessage());
			}
		}

		System.gc();
	}

}
