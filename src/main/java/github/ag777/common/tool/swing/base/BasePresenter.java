package github.ag777.common.tool.swing.base;


import javax.swing.*;
import java.util.function.Consumer;

public class BasePresenter<T extends BaseView> implements Presenter<T>{

    // 绑定的视图对象
	private volatile T mContainer;

	/**
	 * 获取当前绑定的视图对象
	 * @return 返回当前绑定的视图对象
	 */
	public T getView() {
		return mContainer;
	}

	public void toView(Consumer<T> action) {
		if (mContainer != null) {
			SwingUtilities.invokeLater(()-> {
				if (mContainer != null) {
					action.accept(mContainer);
				}
			});
		}
	}

	/**
	 * 绑定视图对象
	 * @param container 要绑定的视图对象
	 */
	@Override
	public void attachView(T container) {
		mContainer = container;
	}

	/**
	 * 解绑视图对象
	 */
	@Override
	public void detachView() {
		mContainer = null;
	}

	/**
	 * 检查视图对象是否已绑定
	 * @return 如果视图对象已绑定，则返回true；否则返回false
	 */
	public boolean isViewAttached() {
		return mContainer != null;
	}

	/**
	 * 检查视图对象是否已绑定，如果未绑定，则抛出异常
	 */
	public void checkViewAttached() {
		if (!isViewAttached()) throw new MvpViewNotAttachedException();
	}

	/**
	 * 自定义异常类MvpViewNotAttachedException，继承自RuntimeException，
	 * 用于在视图对象未绑定时抛出异常
	 */
	public static class MvpViewNotAttachedException extends RuntimeException {
		/**
		 *
		 */
		private static final long serialVersionUID = 3009240321098767728L;

		public MvpViewNotAttachedException() {
			super("请先调用attachView(container)方法绑定视图" +
					" requesting data to the Presenter");
		}
	}
}
