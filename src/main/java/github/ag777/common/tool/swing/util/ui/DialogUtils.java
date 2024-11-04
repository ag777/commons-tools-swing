package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 弹窗工具类
 * @author ag777
 *
 */
public class DialogUtils {

	private DialogUtils(){}

	/**
	 * 显示一个带有标题和内容的消息对话框
	 * 该对话框主要用于向用户显示一般的提示信息
	 *
	 * @param parentComponent 父组件，用于确定消息对话框的显示位置
	 * @param title 对话框的标题
	 * @param content 对话框的内容
	 */
	public static void showPlainDialog(Component parentComponent, String title, String content){
	    JOptionPane.showMessageDialog(parentComponent, content, title, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * 显示一个带有标题和内容的 informational 消息对话框
	 * 该对话框主要用于向用户显示信息性的提示，比如操作成功等
	 *
	 * @param parentComponent 父组件，用于确定消息对话框的显示位置
	 * @param title 对话框的标题
	 * @param content 对话框的内容
	 */
	public static void showInfoDialog(Component parentComponent, String title, String content){
	    JOptionPane.showMessageDialog(parentComponent, content, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * 显示警告对话框
	 * 此方法用于向用户展示警告信息，可以在应用程序中提醒用户某些重要但非关键的问题
	 *
	 * @param parentComponent 调用此方法的父组件，用于决定警告对话框的显示位置
	 * @param title 对话框的标题，用于简洁描述警告的性质或来源
	 * @param content 警告的具体内容，详细说明了警告的原因或建议的操作
	 */
	public static void showWarningDialog(Component parentComponent, String title, String content){
	    JOptionPane.showMessageDialog(parentComponent, content, title,JOptionPane.WARNING_MESSAGE);
	}
	

	/**
	 * 显示一个确认对话框，并根据用户的响应执行相关操作
	 * 此方法用于在图形用户界面应用程序中显示一个确认对话框，对话框的内容和标题由参数指定
	 * 用户可以通过点击对话框的确认(OK)或取消(Cancel)按钮来做出选择
	 * 根据用户的选择，通过传递给方法的Consumer函数式接口，以函数式编程的方式执行相应的操作
	 *
	 * @param parentComponent 父组件，通常是一个JFrame实例，用于确定对话框的拥有者
	 * @param title 对话框的标题
	 * @param content 对话框的内容
	 * @param consumer 接受用户响应的Consumer函数式接口实现
	 */
	public static void showConfirmDialog(JFrame parentComponent, String title, String content, Consumer<Boolean> consumer) {
	    // 显示确认对话框，提供确认(OK)和取消(Cancel)两个选项
	    int result = JOptionPane.showConfirmDialog(parentComponent, content, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

	    // 根据用户的响应，判断用户是否点击了确认(OK)按钮，并通过Consumer接口传递给调用方
	    consumer.accept(result == JOptionPane.OK_OPTION);
	}
	
	/**
	 * 显示消息对话框
	 * 该方法用于创建一个居中的消息对话框，根据传入的标题和消息映射显示信息
	 *
	 * @param parentComponent 父组件，用于确定消息对话框的归属
	 * @param title 对话框标题，用于告诉用户对话框的主题
	 * @param msgMap 消息映射，包含要显示的信息的键值对
	 * @param size 对话框尺寸，确定对话框的大小
	 */
	public static void showDialog(JFrame parentComponent, String title, Map<String, String> msgMap, Dimension size) {
	    // 创建一个模态对话框，用户必须关闭此对话框才能与父组件交互
	    JDialog jd = new JDialog(parentComponent, title, true);
	    // 获取对话框的内容面板
	    Container c = jd.getContentPane();
	    // 设置内容面板的布局为流式布局，组件将从左到右，从上到下排列
	    c.setLayout(new FlowLayout());
	    // 遍历消息映射的键值对，添加到内容面板上
	    for (String key : msgMap.keySet()) {
	        Object value = msgMap.get(key);
	        // 创建标签并格式化键值对信息，然后添加到内容面板上
	        c.add(new JLabel(String.format("%s : %s", key, value)));
	    }
	    // 设置对话框的尺寸
	    jd.setSize(size);
	    // 将对话框显示在屏幕中央
	    ViewUtils.showInCenter(jd);
	}
	
	/**
	 * 显示一个带有标题和自定义内容的模态对话框
	 *
	 * @param parentComponent 对话框的父组件，用于继承其样式和位置
	 * @param title 对话框的标题，将显示在标题栏上
	 * @param content 对话框的内容面板
	 * @param size 对话框的尺寸，包括宽度和高度
	 */
	public static JDialog showDialog(JFrame parentComponent, String title, Component content, Dimension size) {
	    // 创建一个模态对话框，模态意味着用户必须关闭此对话框才能与父组件交互
	    JDialog jd = new JDialog(parentComponent, title, true);

	    // 获取对话框的内容面板
	    Container c = jd.getContentPane();

	    // 设置内容面板的布局为边界布局，以便于灵活放置内容
	    c.setLayout(new BorderLayout());

	    // 将自定义的内容添加到内容面板的中心区域
	    c.add(content, BorderLayout.CENTER);

	    // 设置对话框的尺寸
	    jd.setSize(size);

	    // 将对话框显示在屏幕中央
	    ViewUtils.showInCenter(jd);
		return jd;
	}
}
