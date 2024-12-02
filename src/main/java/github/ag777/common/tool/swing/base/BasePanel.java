package github.ag777.common.tool.swing.base;

import github.ag777.common.tool.swing.model.Threads;
import github.ag777.common.tool.swing.model.UiProperties;
import github.ag777.common.tool.swing.util.ui.DialogUtils;
import github.ag777.common.tool.swing.util.ui.Toast;
import github.ag777.common.tool.swing.view.component.loading.LoadingComponent;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.io.Serial;
import java.util.function.Consumer;

@Slf4j
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
    public BasePanel(UiProperties.MenuItem config) {
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
        if (mPresenter != null) {
            mPresenter.detachView(); // 如果Presenter实例存在，则解绑View，进行资源清理
        }
        // 强制清除loading状态
        setLoading(false);
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
     * 调用业务演示层对象的方法，并处理可能的异常
     *
     * @param action   一个CallPresenter接口实例，用于调用业务演示层的方法
     * @param onCancel 当方法执行被中断时的回调，接收一个InterruptedException参数
     */
    public void callPresenter(CallPresenter action, Consumer<InterruptedException> onCancel) {
        // 获取当前业务演示层对象
        BasePresenter<?> mPresenter = getPresenter();
        if (mPresenter != null) {
            // 设置正在加载状态为true，表示开始进行某种操作
            setLoading(true);
            Threads.getBackgroundPool().execute(() -> {
                try {
                    // 执行传入的业务演示层方法
                    action.call();
                } catch (InterruptedException e) {
                    // 当方法执行被中断时，记录日志并执行取消回调
                    log.debug("业务被中断");
                    if (onCancel != null) {
                        onCancel.accept(e);
                    }
                } catch (Exception e) {
                    // 捕获其他异常，记录异常信息并显示错误
                    log.debug(e.getMessage(), e);
                    showErr(e.getMessage());
                } finally {
                    // 无论结果如何，最后确保将加载状态设置为false
                    setLoading(false);
                }
            });

        }
    }

    /**
     * 显示错误对话框
     *
     * @param errMsg 错误消息文本
     *               当遇到错误需要提示用户时，此方法使用DialogUtils显示一个警告对话框
     *               该对话框标题为"错误"，并显示传入的错误消息
     */
    public void showErr(String errMsg) {
        DialogUtils.showWarningDialog(this, "错误", errMsg);
    }

    /**
     * 显示普通的提示信息
     *
     * @param msg      提示信息的内容
     * @param duration 提示信息显示的持续时间，以毫秒为单位
     */
    public void toastNormal(String msg, int duration) {
        showToast(Toast.normal(getFrame(), msg, duration));
    }

    /**
     * 显示成功的提示信息
     *
     * @param msg      提示信息的内容
     * @param duration 提示信息显示的持续时间，以毫秒为单位
     */
    public void toastSuccess(String msg, int duration) {
        showToast(Toast.success(getFrame(), msg, duration));
    }

    /**
     * 显示错误的提示信息
     *
     * @param msg      提示信息的内容
     * @param duration 提示信息显示的持续时间，以毫秒为单位
     */
    public void toastError(String msg, int duration) {
        showToast(Toast.error(getFrame(), msg, duration));
    }

    /**
     * 显示给定的提示信息
     *
     * @param toast 要显示的提示信息对象
     */
    protected void showToast(Toast toast) {
        toast.showInCenter();
    }

    /**
     * 初始化视图，需要由子类实现
     *
     * @param container 包含视图的JPanel容器
     * @param config    菜单项的配置信息，用于指导视图的初始化
     *                  此方法用于设置组件的界面布局和元素，是组件初始化过程的一部分
     */
    protected abstract void initView(JPanel container, UiProperties.MenuItem config);

    /**
     * 初始化事件/数据，需要由子类实现
     *
     * @param config 菜单项的配置信息，用于指导数据或事件的初始化
     *               此方法用于初始化组件相关的数据或事件处理逻辑，是组件初始化过程的一部分
     */
    protected abstract void initData(UiProperties.MenuItem config);

    /**
     * 获取与组件关联的Presenter实例，需要由子类实现
     *
     * @return 组件关联的BasePresenter的子类实例
     * 此方法用于返回负责组件业务逻辑处理的Presenter实例，以便进行MVC模式下的逻辑处理
     */
    public abstract BasePresenter<?> getPresenter();

    @FunctionalInterface
    public interface CallPresenter {
        void call() throws Exception;
    }

}
