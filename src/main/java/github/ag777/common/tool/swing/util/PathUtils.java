package github.ag777.common.tool.swing.util;

import com.ag777.util.lang.StringUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

/**
 * 路径工具类
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/3/12 10:59
 */
public class PathUtils {

    /**
     * 类是否在jar包中
     * @param clazz 参照类，用于获取路径
     * @return 当前代码是否处于jar包中
     */
    public static boolean inJar(Class<?> clazz) {
        String protocol = clazz.getResource("").getProtocol();
        return "jar".equals(protocol);
    }

    /**
     * 获取classes路径，如果是jar包，获取到jar包的同级路径;反之，获取target/
     * @param clazz 参照类，用于获取路径
     * @return jar包的同级目录或target/classes/目录,如果出异常则返回项目根目录
     */
    public static String getRootPath(Class<?> clazz) {
        String path;
        if (inJar(clazz)) {
            path = new ApplicationHome(clazz) + "/";
        } else {
            try {
                path = ResourceUtils.getURL("classpath:").getPath();
            } catch (FileNotFoundException e) {
                path = new ApplicationHome(clazz)+"/";
            }
        }
        if (!StringUtils.isEmpty(path)) {
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
            // 标准化路径，避免windows系统下的路径开头出现/
            return new File(path).getAbsolutePath()+ FileSystems.getDefault().getSeparator();
        }
        return null;
    }

    private PathUtils() {}
}
