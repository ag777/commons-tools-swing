# Commons-Tools-Swing

个人用的基于 Spring Boot 的 Swing 界面框架，提供了一套应用开发工具集。

## 特性

- 基于 Spring Boot 框架，享受 Spring 生态的便利
- 内置菜单切换功能，支持动态配置菜单项
- 提供几个自封装的 UI 组件和工具类
- 支持主题颜色配置
- 集成加载动画组件
- 内置线程池管理
- 统一的异常处理机制

## 主要组件

- MainFrame：主窗口框架，支持菜单切换
- BasePanel：基础面板组件
- LoadingComponent：加载动画组件
- TabPanel：标签页面板
- 各种工具类：
  - ViewUtils：窗口工具类
  - PathUtils：路径工具类
  - Commons：通用工具类
  - UiConfigUtils：UI 配置工具类

## 示例界面

目前包含一个简单的示例界面，展示了以下功能：
- 左侧菜单栏
- 多标签页切换
- 表格组件示例
- 加载动画效果

## 开发说明

1. 创建新页面：
   - 继承 BasePanel 类
   - 实现必要的界面初始化方法

2. 配置菜单：
   - 在 application.yml 中配置菜单项
   - 支持配置菜单标题、图标、跳转路径等

3. 主题定制：
   - 支持在配置文件中自定义主题颜色
   - 包括主色、成功色、错误色等

## 环境要求

- JDK 17+
- Maven 3.6+
- Spring Boot 3.x

## 构建运行

1. 克隆项目
2. 执行 Maven 构建
3. 运行 App 类的 main 方法

## 作者

ag777 <837915770@vip.qq.com>