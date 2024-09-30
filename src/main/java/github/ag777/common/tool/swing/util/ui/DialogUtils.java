package github.ag777.common.tool.swing.util.ui;

import github.ag777.common.tool.swing.util.awt.ClipboardUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 弹窗工具类
 * @author ag777
 *
 */
public class DialogUtils {

	private DialogUtils(){}
	
	/**
	 * 显示消息对话框
	 * 该方法用于向用户显示一个带有警告图标的消息对话框，对话框的标题和内容可以通过参数进行设置
	 *
	 * @param parentComponent 作为对话框父组件的GUI组件，通常用于确定对话框的位置和所有权
	 * @param title 对话框的标题，用来简要说明对话框的主题或内容
	 * @param content 对话框中要显示的具体内容，可以是警告信息或其他需要通知用户的信息
	 */
	public static void showMsgDialog(Component parentComponent, String title, String content){
	    // 使用JOptionPane类的showMessageDialog方法显示一个消息对话框
	    // 参数包括父组件、要显示的内容、对话框的标题以及消息类型（这里使用WARNING_MESSAGE表示警告信息）
	    JOptionPane.showMessageDialog(parentComponent, content, title, JOptionPane.WARNING_MESSAGE);
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
	public static void showMsgDialog(JFrame parentComponent, String title, Map<String, String> msgMap, Dimension size) {
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
	public static void showMsgDialog(JFrame parentComponent, String title, JPanel content, Dimension size) {
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
	}
	
    /**
     * 显示一个文件选择对话框，允许用户输入一个文件路径，并提供复制和保存操作
     * 此对话框用于让用户确认和选择文件路径，并提供了复制路径到剪贴板以及保存路径的功能
     *
     * @param parentComponent 调用此对话框的父组件，用于定位对话框位置
     * @param curPath 当前文件路径，用作输入提示
     * @param onInput 输入验证回调，用于验证用户输入的路径是否有效
     * @param onSuccess 成功回调，当用户点击保存时调用，传递用户输入的路径
     */
    public static void showFileChooserDialog(JFrame parentComponent, String curPath,  Predicate<String> onInput, Consumer<String> onSuccess) {
        // 创建一个模态对话框
        final JDialog dialog = new JDialog(parentComponent, "提示", false);
        // 设置对话框的宽高
        dialog.setSize(450, 80);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(parentComponent);

        // 创建一个标签显示消息内容
        JPanel panel_filePath = new JPanel();
        // 使用FilePicker工具类创建一个文件路径输入字段，并应用验证回调
        JTextField tf_filePath = FilePicker.chooseDirInput(parentComponent, curPath, curPath, onInput);
        // 创建一个按钮用于复制文件路径到剪贴板
        JButton b_copy = new JButton("复制");
        // 使用BorderLayout布局管理器布局文件路径输入面板
        BorderLayoutHelper.newInstance(panel_filePath)
            .addComponent(new JLabel("工程WebRoot路径: "), BorderLayout.WEST)
            .addComponent(tf_filePath, BorderLayout.CENTER)
            .addComponent(b_copy, BorderLayout.EAST);

        // 为复制按钮添加动作监听器，当点击时复制路径到剪贴板
        b_copy.addActionListener(e->{
            ClipboardUtils.copyTextToClipboard(tf_filePath.getText().trim());
            DialogUtils.showMsgDialog(parentComponent, "系统提示", "已经复制到剪贴板");
        });

        // 创建按钮组面板
        JPanel panel_btnGroup = new JPanel();
        // 创建一个按钮用于关闭对话框并提交路径
        JButton okBtn = new JButton("保存");
        // 为保存按钮添加动作监听器，当点击时调用成功回调并关闭对话框
        okBtn.addActionListener(e->{
            onSuccess.accept(tf_filePath.getText().trim());
            dialog.dispose();
        });

        // 创建一个按钮用于取消操作并关闭对话框
        JButton cancelBtn = new JButton("取消");
        // 为取消按钮添加动作监听器，当点击时关闭对话框
        cancelBtn.addActionListener(e->{
            dialog.dispose();
        });

        // 使用GridBagLayout布局管理器布局按钮组面板
        GridBagLayoutHelper.newInstance(panel_btnGroup)
            .addComponent(okBtn, 0, 0)
            .addComponent(cancelBtn, 0, 1);

        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();
        // 设置内容面板的布局为BorderLayout
        panel.setLayout(new BorderLayout());
        // 在内容面板中心区域添加文件路径输入面板
        panel.add(panel_filePath,BorderLayout.CENTER);
        // 在内容面板南边区域添加按钮组面板
        panel.add(panel_btnGroup,BorderLayout.SOUTH);
        
        // 设置对话框的内容面板
        dialog.setContentPane(panel);
        // 显示对话框
        dialog.setVisible(true);
    }
}
