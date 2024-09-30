package github.ag777.common.tool.swing.util.ui;

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
	
	private GridBagLayoutHelper(){}
	
	public static <T extends Container>GridBagLayoutHelper<T> newInstance(T container){
		return new GridBagLayoutHelper<>(container);
	}
	
	private GridBagLayoutHelper(T container){
		mContainer = container;
		
		gr = new GridBagLayout();
		mContainer.setLayout(gr);
		gc = new GridBagConstraints();
	}

	public T get() {
		return mContainer;
	}
	
	public GridBagLayoutHelper<T> addComponent(Component view, int row, int col){
		gc.gridx= col;
		gc.gridy = row;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		
		gr.setConstraints(view,gc);
		mContainer.add(view);
		
		return this;
	}
}
