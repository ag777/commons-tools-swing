package github.ag777.common.tool.swing.util.ui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.function.Predicate;

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
	 * 创建一个绑定到特定路径的文本输入框，专门用于目录选择
	 * 该方法主要用于让用户输入一个目录路径，并通过提供的验证函数进行校验
	 *
	 * @param parentComponent 父组件，用于确定文本输入框的位置
	 * @param value 输入框的初始文本内容，通常为上次保存的目录路径
	 * @param path 与输入框绑定的配置项路径，用于保存选择的目录路径
	 * @param onInput 目录路径的验证函数，用于校验输入的路径是否有效
	 *
	 * @return 返回一个配置好并绑定了事件的文本输入框，用于接收用户输入的目录路径
	 *
	 * @see JFileChooser#DIRECTORIES_ONLY 仅显示目录的文件选择器模式
	 */
	public static JTextField chooseDirInput(Component parentComponent, String value, String path, Predicate<String> onInput) {
	    // 创建文本输入框并设置初始文本内容
	    JTextField tf = new JTextField(value);
	    // 绑定文本输入框到配置项路径，并设置目录路径验证函数，禁用普通文件选择，仅允许目录
	    return bind(tf, path, onInput, null, JFileChooser.DIRECTORIES_ONLY);
	}
	
	
	/**
	 * 绑定一个文件路径选择器到给定的JTextField
	 * 该方法允许用户通过点击文本框来选择文件或目录，并根据提供的过滤器和模式设置文本框的值
	 *
	 * @param tf 要绑定的JTextField
	 * @param path 默认文件路径
	 * @param onInput 输入验证回调，用于在文件路径设置到文本框之前进行检查
	 * @param filter 文件过滤器，用于限制可以选择的文件类型
	 * @param mode 打开模式，决定用户能选择的是文件还是目录
	 * @return 返回绑定后的JTextField
	 */
	public static JTextField bind(JTextField tf, String path, Predicate<String> onInput, FileFilter filter, int mode) {
	    // 添加鼠标事件监听器，主要处理文件选择操作
	    tf.addMouseListener(new MouseListener() {
	        // 以下方法留空，因为在这个场景中不需要这些具体的鼠标事件处理逻辑
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        }
	        @Override
	        public void mousePressed(MouseEvent e) {
	        }
	        @Override
	        public void mouseExited(MouseEvent e) {
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        }

	        // 当用户点击文本框时，触发文件选择对话框
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            // 根据文本框当前内容或默认路径打开文件选择对话框
	            File file = FilePicker.choose(tf.getText().trim().isEmpty()?path:tf.getText().trim(), filter, mode);
	            if(file != null) {
	                String path = file.getAbsolutePath();
	                // 如果选择的是目录，则在路径后添加分隔符
	                if(file.isDirectory()) {
	                    path+="\\";
	                }
	                // 如果输入验证通过，设置文本框的值为选中的文件或目录的路径
	                if(onInput.test(path)) {
	                    tf.setText(path);
	                }
	            }
	        }
	    });

	    // 添加键盘事件监听器，阻止文本框的键盘输入
	    tf.addKeyListener(new KeyListener() {
	        // 以下方法中e.consume()用于阻止默认的键盘事件处理
	        @Override
	        public void keyTyped(KeyEvent e) {
	            e.consume();
	        }
	        @Override
	        public void keyReleased(KeyEvent e) {
	            e.consume();
	        }
	        @Override
	        public void keyPressed(KeyEvent e) {
	            e.consume();
	        }
	    });

	    // 返回绑定后的文本框
	    return tf;
	}
}
