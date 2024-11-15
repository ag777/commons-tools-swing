package github.ag777.common.tool.swing.spring.thread;

import com.ag777.util.lang.thread.ThreadPoolUtils;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置中心
 * 知识点:
 * <p>线程池策略
 * 1. CallerRunsPolicy ：这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功。
 * 2. AbortPolicy ：对拒绝任务抛弃处理，并且抛出异常。
 * 3. DiscardPolicy ：对拒绝任务直接无声抛弃，没有异常信息。
 * 4. DiscardOldestPolicy ：对拒绝任务不抛弃，而是抛弃队列里面等待最久的一个线程，然后把拒绝任务加到队列。
 * <p>
 * ThreadPoolExecutor 的默认策略就是 ThreadPoolExecutor.AbortPolicy()
 * 该策略使你在添加任务时抛出RejectedExecutionException异常，请注意捕获!!!
 * <p>
 * ------
 * <p>
 * 在《Java Concurrency in Practice》一书中，给出了估算线程池大小的公式：
 * Nthreads=Ncpu*Ucpu*(1+w/c)，其中
 * Ncpu=CPU核心数
 * Ucpu=cpu使用率，0~1
 * W/C=等待时间与计算时间的比率
 * 实际需要通过压力测试才能得出最终数据,小公司请自行裁定
 *
 * @author ag777 <837915770@vip.qq.com>
 * @version  2021/3/16 8:55
 */
@Configuration
public class PoolConfig {

    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * @return 后台任务线程池
     */
    @Bean("backgroundPool")
    public ThreadPoolExecutor backgroundPool() {
        threadPoolExecutor = new ThreadPoolExecutor(
                1,
                10,
                30,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                r -> new Thread(r, "pool-process"),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        return threadPoolExecutor;
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        if (threadPoolExecutor != null) {
            // 优雅关闭线程池
            threadPoolExecutor.shutdownNow();
            ThreadPoolUtils.waitFor(threadPoolExecutor);
        }
    }
}
