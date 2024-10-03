package github.ag777.common.tool.swing.util.ui;

import java.awt.*;

/**
 * 窗口定位工具类
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/10/03 下午17:30
 */
public class ViewUtils {

	private ViewUtils(){}

	/**
	 * 在屏幕中心显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInCenter(Component component) {
		showInCenter(component, getWorkArea());
	}

	/**
	 * 在工作区域的中心显示组件
	 *
	 * @param component 要显示的组件，它可以是任何实现了Component接口的对象
	 * @param workArea 屏幕工作区域的矩形表示，它定义了可用的屏幕空间
	 */
	public static void showInCenter(Component component, Rectangle workArea) {
		int x = (workArea.width - component.getWidth()) / 2 + workArea.x;
		int y = (workArea.height - component.getHeight()) / 2 + workArea.y;
		component.setLocation(x, y);
		component.setVisible(true);
	}

	/**
	 * 在屏幕左上角显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInTopLeft(Component component) {
		showInTopLeft(component, getWorkArea());
	}

	/**
	 * 在工作区域的左上角显示组件
	 *
	 * @param component 要显示的组件，它可以是任何实现了Component接口的对象
	 * @param workArea 屏幕工作区域的矩形表示，它定义了可用的屏幕空间
	 */
	public static void showInTopLeft(Component component, Rectangle workArea) {
		int x = workArea.x;
		int y = workArea.y;
		component.setLocation(x, y);
		component.setVisible(true);
	}

	/**
	 * 在屏幕顶部中央显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInTopCenter(Component component) {
		showInTopCenter(component, getWorkArea());
	}

	/**
	 * 在工作区域的顶部中央显示组件
	 *
	 * @param component 要显示的组件，它可以是任何实现了Component接口的对象
	 * @param workArea 屏幕工作区域的矩形表示，它定义了可用的屏幕空间
	 */
	public static void showInTopCenter(Component component, Rectangle workArea) {
		int x = workArea.x + (workArea.width - component.getWidth()) / 2;
		int y = workArea.y;
		component.setLocation(x, y);
		component.setVisible(true);
	}

	/**
	 * 在屏幕右上角显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInTopRight(Component component) {
		showInTopRight(component, getWorkArea());
	}

	/**
	 * 在工作区域的右上角显示组件
	 *
	 * @param component 要显示的组件，它可以是任何实现了Component接口的对象
	 * @param workArea 屏幕工作区域的矩形表示，它定义了可用的屏幕空间
	 */
	public static void showInTopRight(Component component, Rectangle workArea) {
		int x = workArea.x + workArea.width - component.getWidth();
		int y = workArea.y;
		component.setLocation(x, y);
		component.setVisible(true);
	}

	/**
	 * 在屏幕左侧中央显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInLeftCenter(Component component) {
		showInLeftCenter(component, getWorkArea());
	}

	/**
	 * 在工作区域的左侧中央显示组件
	 *
	 * @param component 要显示的组件，它可以是任何实现了Component接口的对象
	 * @param workArea 屏幕工作区域的矩形表示，它定义了可用的屏幕空间
	 */
	public static void showInLeftCenter(Component component, Rectangle workArea) {
		int x = workArea.x;
		int y = workArea.y + (workArea.height - component.getHeight()) / 2;
		component.setLocation(x, y);
		component.setVisible(true);
	}

	/**
	 * 在屏幕右侧中央显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInRightCenter(Component component) {
		showInRightCenter(component, getWorkArea());
	}

	/**
	 * 在工作区域的右侧中央显示组件
	 *
	 * @param component 要显示的组件，它可以是任何实现了Component接口的对象
	 * @param workArea 屏幕工作区域的矩形表示，它定义了可用的屏幕空间
	 */
	public static void showInRightCenter(Component component, Rectangle workArea) {
		int x = workArea.x + workArea.width - component.getWidth();
		int y = workArea.y + (workArea.height - component.getHeight()) / 2;
		component.setLocation(x, y);
		component.setVisible(true);
	}

	/**
	 * 在屏幕左下角显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInBottomLeft(Component component) {
		showInBottomLeft(component, getWorkArea());
	}


	/**
	 * 在工作区域的左下角显示组件
	 *
	 * @param component 要显示的组件，它可以是任何实现了Component接口的对象
	 * @param workArea 屏幕工作区域的矩形表示，它定义了可用的屏幕空间
	 */
	public static void showInBottomLeft(Component component, Rectangle workArea) {
	    // 计算组件的x坐标，使其位于屏幕工作区域的最左侧
	    int x = workArea.x;
	    // 计算组件的y坐标，使其紧贴在屏幕工作区域的底部，考虑了组件的高度以确保不会超出工作区域
	    int y = workArea.y + workArea.height - component.getHeight();
	    // 设置组件的位置
	    component.setLocation(x, y);
	    // 设置组件为可见状态
	    component.setVisible(true);
	}

	/**
	 * 在屏幕底部中央显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInBottomCenter(Component component) {
		showInBottomCenter(component, getWorkArea());
	}

	/**
	 * 在工作区域的底部中心显示组件
	 *
	 * @param component 需要显示的组件，如JFrame或JDialog
	 * @param workArea 工作区的矩形区域，通常是指屏幕可用区域减去任务栏等非工作区部分
	 */
	public static void showInBottomCenter(Component component, Rectangle workArea) {
	    // 计算组件的x坐标，使其中心点在工作区宽度的中心位置
	    int x = workArea.x + (workArea.width - component.getWidth()) / 2;
	    // 计算组件的y坐标，使其底部紧贴工作区的底部
	    int y = workArea.y + workArea.height - component.getHeight();
	    // 设置组件的位置
	    component.setLocation(x, y);
	    // 显示组件
	    component.setVisible(true);
	}


	/**
	 * 在屏幕右下角显示指定组件
	 * @param component 要显示的组件
	 */
	public static void showInBottomRight(Component component) {
		showInBottomRight(component, getWorkArea());
	}

	/**
	 * 在工作区域的右下角显示组件
	 *
	 * @param component 需要显示的组件，例如一个弹出窗口
	 * @param workArea 工作区的矩形区域，通常是指屏幕可工作区域
	 */
	public static void showInBottomRight(Component component, Rectangle workArea) {
	    // 计算组件应放置的x坐标，使其右边缘与工作区右边缘对齐
	    int x = workArea.x + workArea.width - component.getWidth();
	    // 计算组件应放置的y坐标，使其下边缘与工作区下边缘对齐
	    int y = workArea.y + workArea.height - component.getHeight();
	    // 设置组件的位置
	    component.setLocation(x, y);
	    // 设置组件为可见状态
	    component.setVisible(true);
	}

	/**
	 * 获取屏幕工作区域的矩形范围
	 *
	 * 该方法通过GraphicsEnvironment和GraphicsDevice类来获取当前系统的默认屏幕设备的工作区域
	 * 它反映了屏幕的实际可用区域，对于跨平台应用来说，此信息可用于确定窗口放置和大小策略
	 *
	 * @return 返回一个Rectangle对象，该对象表示默认屏幕设备的默认配置的工作区域
	 */
	private static Rectangle getWorkArea() {
	    // 获取本地图形环境
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    // 获取默认屏幕设备
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    // 返回默认配置的工作区域边界
	    return gd.getDefaultConfiguration().getBounds();
	}
	
}
