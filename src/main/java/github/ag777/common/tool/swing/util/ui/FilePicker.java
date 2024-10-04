package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.function.Consumer;
import java.util.function.Function;

public class FilePicker {

	private FilePicker() {}

	/**
	 * 选择目录的函数
	 * 此函数提供了一个文件选择器，专门用于选择目录，不包括文件
	 * 它通过给定的当前路径和文件过滤器来初始化选择器，并设置只能选择目录
	 *
	 * @param curPath 当前路径，用于初始化文件选择器的位置
	 * @param filter  文件过滤器，用于过滤显示在文件选择器中的项
	 * @return 返回用户选择的目录，如果用户取消操作或未选择任何目录，则返回null
	 */
	public static File chooseDir(String curPath, FileFilter filter) {
	    // 调用重载的choose函数，专门选择目录
	    return choose(curPath, filter, JFileChooser.DIRECTORIES_ONLY);
	}
	
	/**
	 * 弹出文件选择对话框，根据当前路径或默认位置、文件过滤器和选择模式来选择文件或目录
	 *
	 * @param curPath 当前路径，用于设置文件选择器的当前目录
	 * @param filter 文件过滤器，用于过滤显示的文件类型
	 * @param mode 文件选择模式，例如只读模式、保存模式等
	 * @return 用户选择的文件或目录，如果取消选择则返回null
	 */
	public static File choose(String curPath, FileFilter filter, int mode) {
	    JFileChooser jfc = new JFileChooser();
	    if (curPath != null) {
	        jfc.setCurrentDirectory(new File(curPath));
	    } else {
	        // 设置当前路径为桌面路径,否则将我的文档作为默认路径
	        FileSystemView fsv = FileSystemView.getFileSystemView();
	        jfc.setCurrentDirectory(fsv.getHomeDirectory());
	    }

	    // 设置文件过滤
	    jfc.setFileSelectionMode(mode);
	    if (filter != null) {
	        jfc.setFileFilter(filter);
	    }
	    // 弹出的提示框的标题
	    jfc.showDialog(new JLabel(), "确定");
	    // 用户选择的路径或文件
	    return jfc.getSelectedFile();
	}

	/**
	 * 创建一个不可编辑的文本输入框，用于选择文件或目录
	 * 该方法允许用户通过图形界面选择文件或目录，并在文本输入框中显示选择的路径
	 *
	 * @param path 初始路径，用于定位文件选择对话框的初始位置
	 * @param display 一个函数，用于处理显示在文本输入框中的文本如果为null，则使用默认处理
	 * @param filter 文件过滤器，用于在文件选择对话框中过滤文件和目录
	 * @param mode 文件选择模式，决定用户能够选择的是文件还是目录,比如:JFileChooser.DIRECTORIES_ONLY
	 * @param onSelected 当用户选择文件或目录时的回调函数
	 * @return 返回一个配置好的文本输入框，用于显示选择的文件或目录路径
	 */
	public static JTextField chooseFileInput(String path, Function<String, String> display, FileFilter filter, int mode, Consumer<String> onSelected) {
	    // 如果未提供显示处理函数，则使用默认函数，即直接返回路径
	    if (display == null) {
	        display = p -> p;
	    }
	    // 创建文本输入框并设置初始文本内容
	    JTextField tf = new JTextField(display.apply(path));
	    // 设置文本输入框为不可编辑，以确保用户不能直接输入文本
	    tf.setEditable(false);

	    // 自定义外观，使其看起来像一个按钮
	    tf.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	    tf.setBackground(Color.LIGHT_GRAY);
	    tf.setHorizontalAlignment(JTextField.CENTER);

		// 隐藏光标
		tf.setCaretColor(tf.getBackground()); // 将光标颜色设置为背景色
		tf.getCaret().setVisible(false); // 设置光标不可见

	    // 添加鼠标监听器以处理点击、鼠标移入和移出事件
	    Function<String, String> finalDisplay = display;
	    tf.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            // 根据文本框当前内容或默认路径打开文件选择对话框，只显示目录
	            File file = FilePicker.choose(tf.getText().trim().isEmpty() ? path : tf.getText().trim(), filter, mode);
	            if (file != null) {
	                String selectedPath = file.getAbsolutePath();
	                // 如果选择的是目录，则在路径后添加分隔符
	                if (file.isDirectory()) {
	                    selectedPath += "\\";
	                }
	                // 更新文本框内容为新的目录路径，并调用回调函数（如果提供）
	                tf.setText(finalDisplay.apply(selectedPath));
	                if (onSelected != null) {
	                    onSelected.accept(selectedPath);
	                }
	            }
	        }

	        @Override
	        public void mouseEntered(MouseEvent e) {
	            // 鼠标进入文本框时改变背景颜色和文字颜色，以提供视觉反馈
	            tf.setBackground(Color.DARK_GRAY);
	            tf.setForeground(Color.WHITE);
				// 隐藏光标
				tf.setCaretColor(tf.getBackground());
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            // 鼠标离开文本框时恢复原来的背景颜色和文字颜色
	            tf.setBackground(Color.LIGHT_GRAY);
	            tf.setForeground(Color.BLACK);
				// 隐藏光标
				tf.setCaretColor(tf.getBackground());
	        }
	    });
	    return tf;
	}

}
