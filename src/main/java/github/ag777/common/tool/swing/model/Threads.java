package github.ag777.common.tool.swing.model;

import github.ag777.common.tool.swing.spring.SpringContextUtil;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @version 2024/11/15 上午8:47
 */
public class Threads {

    public static ThreadPoolExecutor getBackgroundPool() {
        return (ThreadPoolExecutor) SpringContextUtil.getBean("backgroundPool");
    }
}
