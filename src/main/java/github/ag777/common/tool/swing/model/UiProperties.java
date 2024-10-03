package github.ag777.common.tool.swing.model;

import com.ag777.util.lang.StringUtils;
import github.ag777.common.tool.swing.util.awt.ColorUtils;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/9/27 上午9:34
 */
@Data
@Component
@ConfigurationProperties(prefix = "ui")
public class UiProperties {
    // 标题
    private String title;
    private Colors colors;
    // 基础ui配置
    private UiConfig baseUiConfig;
    // 菜单项
    private Menu menu = new Menu();

    @PostConstruct
    public void init() {
        if (!StringUtils.isEmpty(colors.getPrimary())) {
            Theme.COLOR_PRIMARY = ColorUtils.toColor(colors.getPrimary());
        }
        if (!StringUtils.isEmpty(colors.getSuccess())) {
            Theme.COLOR_SUCCESS = ColorUtils.toColor(colors.getSuccess());
        }
        if (!StringUtils.isEmpty(colors.getError())) {
            Theme.COLOR_ERROR = ColorUtils.toColor(colors.getError());
        }

        if (menu != null) {
            if (menu.getUiConfig() != null) {
                menu.setUiConfig(baseUiConfig);
            }
            if (menu.items != null) {
                for (MenuItem item : menu.items) {
                    if (item.getUiConfig() == null) {
                        item.setUiConfig(baseUiConfig); // 设置默认值
                    }
                }
            }
        }

    }

    @Data
    public static class Colors {
        private String primary;
        private String success;
        private String error;
    }

    @Data
    public static class Menu {
        private UiConfig uiConfig = new UiConfig();
        private List<MenuItem> items = Collections.emptyList();
    }

    @Data
    public static class MenuItem {
        private String name;
        private String path;
        private UiConfig uiConfig;
        private Map<String, Object> params = Collections.emptyMap();
    }

    @Data
    public static class UiConfig {
        private Font font;
    }

    @Data
    public static class Font {
        private int textSize=14;
        private boolean bold=false;
    }
}
