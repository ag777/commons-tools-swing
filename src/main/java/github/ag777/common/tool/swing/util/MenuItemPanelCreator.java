package github.ag777.common.tool.swing.util;

import com.ag777.util.lang.exception.model.ValidateException;
import github.ag777.common.tool.swing.base.BasePanel;
import github.ag777.common.tool.swing.model.UiProperties;

import java.lang.reflect.InvocationTargetException;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/27 下午2:54
 */
public class MenuItemPanelCreator {

    public static BasePanel getPanel(String basePackage, UiProperties.MenuItem config) throws ValidateException {
        String classPath = basePackage + "." + config.getPath();
        try {
            Class<?> clazz = Class.forName(classPath);
            if (clazz.isAssignableFrom(BasePanel.class)) {
                throw new ValidateException("目标类必须是JPanel的子类或实现类，当前类: " + clazz.getName());
            }
            return (BasePanel) clazz.getConstructor(UiProperties.UiConfig.class).newInstance(config.getConfig());
        } catch (ClassNotFoundException e) {
            throw new ValidateException("没有找到对应类：" + classPath, e);
        } catch (InstantiationException e) {
            throw new ValidateException("实例化目标类失败，可能是因为类没有默认构造器：" + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ValidateException("访问目标类构造器时发生错误：" + e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new ValidateException("调用构造器时抛出异常：" + e.getCause().getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new ValidateException("未找到与参数匹配的构造器：" + e.getMessage(), e);
        }

    }
}
