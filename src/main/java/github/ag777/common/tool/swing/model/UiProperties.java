package github.ag777.common.tool.swing.model;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

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
    // 基础ui配置
    private UiConfig baseConfig;
    // 菜单项
    private Menu menu = new Menu();

    @PostConstruct
    public void init() {
        if (menu != null) {
            if (menu.getConfig() != null) {
                menu.setConfig(baseConfig);
            }
            if (menu.items != null) {
                for (MenuItem item : menu.items) {
                    if (item.getConfig() == null) {
                        item.setConfig(baseConfig); // 设置默认值
                    }
                }
            }
        }

    }

    @Data
    public static class Menu {
        private UiConfig config = new UiConfig();
        private List<MenuItem> items = Collections.emptyList();
    }

    @Data
    public static class MenuItem {
        private String name;
        private String path;
        private UiConfig config;
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
