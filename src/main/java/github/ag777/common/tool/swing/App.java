package github.ag777.common.tool.swing;

import github.ag777.common.tool.swing.model.UiProperties;
import github.ag777.common.tool.swing.util.Commons;
import github.ag777.common.tool.swing.util.PathUtils;
import github.ag777.common.tool.swing.util.ui.ViewUtils;
import github.ag777.common.tool.swing.view.MainFrame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.setProperty("log.path", Commons.DIR_LOGS);
        System.setProperty("log.charset", isInJar()? "GBK" : "UTF-8");

        SpringApplication app = new SpringApplicationBuilder(App.class)
                .headless(false).build();
        app.run(args);
    }

    @Bean
    public CommandLineRunner run(ConfigurableApplicationContext context, UiProperties uiProperties) {
        String viewBasePackage = getClass().getPackageName() + ".view";
        return args -> SwingUtilities.invokeLater(() -> {
            MainFrame mf = new MainFrame(viewBasePackage, uiProperties);
            mf.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    context.close();
                }
            });
            mf.switch2DefaultView();

            //放在屏幕中央
            ViewUtils.showInCenter(mf);
        });
    }

    @Bean
    public ApplicationListener<ContextClosedEvent> contextClosedEventApplicationListener() {
        return event -> {
            System.exit(0);  // 关闭整个 Java 进程，包括 Swing GUI
        };
    }

    public static String getRootPath() {
        return PathUtils.getRootPath(App.class);
    }

    public static boolean isInJar() {
        return PathUtils.inJar(App.class);
    }
}
