package github.ag777.common.tool.swing.presenter;

import com.ag777.util.lang.RandomUtils;
import github.ag777.common.tool.swing.base.BasePresenter;
import github.ag777.common.tool.swing.view.interf.EmptyView;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/27 14:42
 */
public class EmptyPresenter extends BasePresenter<EmptyView> {

    public void test() {
        int r =  RandomUtils.rInt(100);
        toView(v->v.show(String.valueOf(r)));
    }

    @Override
    public void attachView(EmptyView container) {
        super.attachView(container);
    }

    @Override
    public void detachView() {
        super.detachView();

    }
}
