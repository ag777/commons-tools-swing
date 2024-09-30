package github.ag777.common.tool.swing.util;

import com.ag777.util.file.FileUtils;
import com.ag777.util.lang.DateUtils;
import com.ag777.util.lang.RandomUtils;
import com.ag777.util.lang.StringUtils;
import com.ag777.util.lang.exception.model.ValidateException;
import github.ag777.common.tool.swing.App;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 常量类
 * @author ag777 <837915770@vip.qq.com>
 * @version 2021/9/22 15:24
 */
@Slf4j
public class Commons {
    private Commons() {}
    /** 包名 */
    public static final String PACKAGE_BASE = App.class.getPackage().getName();
    /** jar包同级目录 */
    public static final String DIR_BASE = App.getRootPath();
    /** 下载文件路径 */
    public static final String DIR_DOWNLOAD = DIR_BASE+"download/";
    /** 用户上传文件路径 */
    public static final String DIR_UPLOAD = DIR_BASE + "upload/";
    /** 临时目录 */
    public static final String DIR_TEMP = DIR_BASE+"temp/";
    /** 模板目录 */
    public static final String DIR_TEMPLATE = DIR_BASE+"template/";
    /** 依赖库目录 */
    public static final String DIR_LIB = DIR_BASE+"lib/";
    /** 日志路径 */
    public static final String DIR_LOGS = DIR_BASE+"logs/";
    /** 脚本目录 */
    public static final String DIR_GROOVY = DIR_BASE+"groovy/";
    public static final String DIR_SCRIPT = DIR_BASE+"script/";
    public static final String DIR_SCRIPT_FLOW = DIR_SCRIPT+"flow/";
    /** 声音目录 */
    public static final String DIR_VOICE = DIR_BASE+"voice/";
    public static final String FILE_DB_SQLITE = DIR_BASE+"data.db";

    public static File getUploadBaseDir() {
        String absPath = DateUtils.getNow("yyyyMM") + "/";
        return new File(Commons.DIR_UPLOAD+absPath);
    }

    /**
     *
     * @param extension 文件拓展名
     * @return 用户上传文件转存后的文件
     */
    public static File getUploadFile(String extension) {
        // 纳秒数+随机数，尽量降低并发情况下的重复概率
        String targetFileName = String.valueOf(System.nanoTime()) + RandomUtils.rInt(0, 10000);
        if (!StringUtils.isEmpty(extension)) {
            targetFileName+="."+extension;
        }
        File targetFile = new File(getUploadBaseDir(), targetFileName);
        // 创建目录以免报错
        FileUtils.makeDir(targetFile.getParent(), true);
        return targetFile;
    }

    /**
     * 获取临时路径
     * @param module 模块名
     * @return 临时文件夹路径
     */
    public static String getModuleTempDir(String module) {
       String dir = DIR_TEMP+module+"/";
       new File(dir).mkdirs();
       return dir;
    }

    /**
     * 获取临时文件, 掉员工该方法会在临时目录下获取uuid+文件尾缀的文件
     * @param module 模块名
     * @param fileExtension 临时文件后缀
     * @return 临时文件
     */
    public static File getModuleTempFile(String module, String fileExtension) {
        String filePath = getModuleTempDir(module)+ StringUtils.uuid()+StringUtils.emptyIfNull(fileExtension);
        return new File(filePath);
    }

    /**
     * @param path 路径,如: /db/app-alarm.db
     * @return 资源文件输入流
     * @throws IOException io异常
     */
    public static InputStream getResourceInputStream(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        return resource.getInputStream();
    }

    /**
     * 验证文件后缀(接收用户上传文件时)
     * @param fileName 文件名称
     * @param extensions 期望的后缀
     * @throws ValidateException 用户上传了期望外的文件类型
     */
    public static void validateFileExtension(String fileName, String... extensions) throws ValidateException {
        if (StringUtils.isEmpty(fileName)) {
            throw new ValidateException("上传的文件名称为空");
        }
        if (extensions == null) {
            throw new IllegalArgumentException("参数extensions不能为空");
        }
        fileName = fileName.toLowerCase();
        boolean isValidate = false;
        for (String extension : extensions) {
            if (fileName.endsWith(extension)) {
                isValidate = true;
                break;
            }
        }

        if (!isValidate) {
            throw new ValidateException(fileName+", 仅支持"+String.join(",", extensions)+"格式的文件");
        }
    }

    public static void copyResourceAndOverride(String resourceAbsPath, String outputPath, boolean isAbsPath) throws IOException {
        copyResourceAndOverride(resourceAbsPath, outputPath, isAbsPath, null);
    }

    public static void copyResourceAndOverride(String resourceAbsPath, String outputPath, boolean isAbsPath, Consumer<File> afterCopy) throws IOException {
        copyResource(resourceAbsPath, outputPath, isAbsPath, false, afterCopy);
    }

    public static void copyResourceWhenNotExists(String resourceAbsPath, String outputPath, boolean isAbsPath) throws IOException {
        copyResourceWhenNotExists(resourceAbsPath, outputPath, isAbsPath, null);
    }

    /**
     * 复制resource下文件到目标路径下
     * @param resourceAbsPath 资源文件路径
     * @param outputPath 目标路径
     * @param isAbsPath 目标路径是否是相对于jar包的路径
     * @param afterCopy 复制完成后执行
     * @throws IOException 出现异常
     */
    public static void copyResourceWhenNotExists(String resourceAbsPath, String outputPath, boolean isAbsPath, Consumer<File> afterCopy) throws IOException {
        copyResource(resourceAbsPath, outputPath, isAbsPath, true, afterCopy);
    }

    /**
     * 复制外部文件到目标路径下
     * @param fileAbsPath 外部文件路径
     * @param outputPath 目标路径
     * @param isAbsPath 目标路径是否是相对于jar包的路径
     * @throws IOException 出现异常
     */
    public static void copyFileWhenNotExists(String fileAbsPath, String outputPath, boolean isAbsPath) throws IOException {
        copyFileWhenNotExists(fileAbsPath, outputPath, isAbsPath, null);
    }

    /**
     * 复制外部文件到目标路径下
     * @param fileAbsPath 外部文件路径
     * @param outputPath 目标路径
     * @param isAbsPath 目标路径是否是相对于jar包的路径
     * @param afterCopy 复制完成后执行
     * @throws IOException 出现异常
     */
    public static void copyFileWhenNotExists(String fileAbsPath, String outputPath, boolean isAbsPath, Consumer<File> afterCopy) throws IOException {
        copyFile(fileAbsPath, outputPath, isAbsPath, true, afterCopy);
    }

    /**
     * 复制resource下文件到目标路径下
     * @param resourceAbsPath 资源文件路径
     * @param outputPath 目标路径
     * @param isAbsPath 目标路径是否是相对于jar包的路径
     * @param skipWhenExists 目标文件已存在时是否跳过复制
     * @param afterCopy 复制完成后执行
     * @throws IOException 出现异常
     */
    public static void copyResource(String resourceAbsPath, String outputPath, boolean isAbsPath, boolean skipWhenExists, Consumer<File> afterCopy) throws IOException {
        if (isAbsPath) {
            outputPath = DIR_BASE+outputPath;
        }
        File outputFile = new File(outputPath);
        // 判断外部文件是否已存在，存在则退出
        if (outputFile.exists()) {
            if (skipWhenExists) {
                log.info(outputFile+" 已存在，不进行文件复制:"+outputFile.getAbsolutePath());
                return;
            }

        }
        // 读取resource下文件
        InputStream in = getResourceInputStream(resourceAbsPath);
        // 写出到目标路径
        FileUtils.write(in, outputFile.getAbsolutePath());
        log.debug("复制文件: "+outputFile.getAbsolutePath());
        if (afterCopy != null) {
            afterCopy.accept(outputFile);
        }
    }

    /**
     * 将指定的文件复制到输出路径。
     *
     * @param fileAbsPath     源文件的绝对路径。
     * @param outputPath      目标输出路径。
     * @param isAbsPath       outputPath是否是相对路径
     * @param skipWhenExists  如果目标文件已存在，当此参数为 true 时跳过复制，为 false 时覆盖文件。
     * @param afterCopy       文件复制后的回调函数，允许对复制的文件执行额外的操作。
     * @throws IOException    如果复制过程中发生 I/O 错误。
     */
    public static void copyFile(String fileAbsPath, String outputPath, boolean isAbsPath, boolean skipWhenExists, Consumer<File> afterCopy) throws IOException {
        // 如果是相对路径，则将 DIR_BASE 添加到 outputPath 前
        if (isAbsPath) {
            outputPath = DIR_BASE + outputPath;
        }

        Path sourcePath = Paths.get(fileAbsPath);
        Path targetPath = Paths.get(outputPath);

        // 检查目标文件是否存在
        if (Files.exists(targetPath)) {
            if (skipWhenExists) {
                // 如果文件存在且设置为跳过，则直接返回
                log.info(targetPath.getFileName()+" 已存在，不进行文件复制:"+targetPath);
                return;
            } else {
                // 如果文件存在且不跳过，则覆盖文件
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } else {
            // 如果文件不存在，则正常复制
            Files.copy(sourcePath, targetPath);
        }

        // 调用 afterCopy 消费函数
        if (afterCopy != null) {
            afterCopy.accept(targetPath.toFile());
        }
    }


    /**
     * 获取指定资源文件夹下的所有文件资源的相对路径。
     *
     * @param resourceFolder 资源文件夹路径，例如 "script"
     * @param recursive 是否递归遍历子文件夹
     * @return 资源文件夹下的所有文件的相对路径列表，不包含传入的文件夹名称
     * @throws IOException 如果读取资源时发生错误
     */
    public static List<String> getAllResourcePaths(String resourceFolder, boolean recursive) throws IOException {
        List<String> resourcePaths = new ArrayList<>();

        // 标准化路径，确保它以 '/' 开头但不以 '/' 结尾
        if (!resourceFolder.startsWith("/")) {
            resourceFolder = "/" + resourceFolder;
        }
        if (resourceFolder.endsWith("/")) {
            resourceFolder = resourceFolder.substring(0, resourceFolder.length() - 1);
        }

        // 根据是否递归遍历子文件夹，设置不同的搜索模式
        String locationPattern = recursive
                ? "classpath*:" + resourceFolder + "/**/*"
                : "classpath*:" + resourceFolder + "/*";

        // 创建资源模式解析器
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // 获取匹配模式的所有资源
        Resource[] resources = resolver.getResources(locationPattern);

        // 计算需要移除的前缀长度，包括资源文件夹名称和额外的一个字符（即 '/'）
        int prefixLength = resourceFolder.length() + 1;

        // 遍历资源并添加到资源路径列表
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                // 获取资源的URI并转换为字符串
                String resourceUri = resource.getURI().toString();

                // 找到资源文件夹名称在URI中的起始位置
                int startIndex = resourceUri.indexOf(resourceFolder) + prefixLength;
                if (startIndex >= prefixLength && startIndex < resourceUri.length()) {
                    // 获取相对路径，即去除前面的 'file:' 和类路径部分以及资源文件夹名称
                    String relativePath = resourceUri.substring(startIndex);

                    resourcePaths.add(relativePath);
                }
            }
        }

        return resourcePaths;
    }

    /**
     * 处理文件路径的方法。
     * 根据输入的文件路径，将其分割并根据前缀（如temp、base）决定文件的最终路径。
     * 如果路径包含支持的前缀，将会拼接对应的目录路径。
     *
     * @param filePath 输入的文件路径字符串，可能包含一个特定的前缀和路径分隔符“|”。
     * @return 返回处理后的文件路径。如果路径包含已知前缀，将其转换为完整的路径。
     * @throws ValidateException 如果路径前缀不被支持，抛出异常。
     */
    public static String handleFilePath(String filePath) throws ValidateException {
        String[] group = filePath.split("\\|", 2);
        if (group.length == 2) {
            return switch (group[0]) {
                case "", "temp" -> Commons.DIR_TEMP + group[1];
                case "base" -> Commons.DIR_BASE + group[1];
                case "template" -> Commons.DIR_TEMPLATE + group[1];
                default -> throw new ValidateException("不支持的路径类型:" + filePath);
            };
        }
        return filePath;
    }
}
