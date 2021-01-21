package com.guyuan.dear.utils;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @description: 线程池工具类
 * @author: 许建宁
 * @since: 2021/1/21 21:20
 * @company: 固远（深圳）信息技术有限公司
 */
public final class ThreadPoolFactory {

    private static ThreadPoolFactory factory = new ThreadPoolFactory();

    //线程池默认配置设置
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 20;
    private static final long KEEP_ALIVE_TIME = 60L;

    private ThreadPoolFactory() {
    }

    public static ThreadPoolFactory getFactory() {
        if (factory == null) {
            factory = new ThreadPoolFactory();
        }
        return factory;
    }

    /**
     * 创建一个默认的线程池
     *
     * @return
     * @author cheng2839
     * @date 2018年11月16日
     */
    public ExecutorService getDefaultThreadPool() {
        //配置最大队列容量
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);
        return getCustomThreadPool(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, blockingQueue);
    }

    /**
     * 创建一个简单的线程池
     *
     * @return
     * @author cheng2839
     * @date 2018年11月16日
     */
    public ExecutorService getSimpleThreadPool(int corePoolSize, int maximumPoolSize) {
        return getCustomThreadPool(corePoolSize, maximumPoolSize, KEEP_ALIVE_TIME, new LinkedBlockingQueue<Runnable>());
    }

    /**
     * 创建一个指定队列的线程池
     *
     * @return
     * @author cheng2839
     * @date 2018年11月16日
     */
    public ExecutorService getCustomQueueThreadPool(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> blockingQueue) {
        return getCustomThreadPool(corePoolSize, maximumPoolSize, KEEP_ALIVE_TIME, blockingQueue);
    }

    /**
     * 创建可跟踪任务状态的执行器
     *
     * @return
     * @author cheng2839
     * @date 2018年11月16日
     */
    public ExecutorCompletionService getCompletionService(ExecutorService executorService) {
        return new ExecutorCompletionService(executorService);
    }

    /**
     * 创建一个定制化的线程池
     *
     * @return
     * @author cheng2839
     * @date 2018年11月16日
     */
    public ExecutorService getCustomThreadPool(int corePoolSize,
                                               int maximumPoolSize,
                                               Long keepAliveTime,
                                               BlockingQueue<Runnable> blockingQueue) {
        LogUtils.showLog("开始初始化线程池[corePoolSize={" + corePoolSize + "},maximumPoolSize={" + maximumPoolSize + "},keepAliveTime={" + keepAliveTime + "}s]...");

        RejectedExecutionHandler rejectedExecutionHandler = (Runnable r, ThreadPoolExecutor executor) -> {
            LogUtils.showLog("线程池已满，任务被丢弃...........................");
            return;
        };
        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                blockingQueue,
                rejectedExecutionHandler);
        LogUtils.showLog("初始化线程池完成！");
        return executorService;
    }

    /**
     * 等待任务执行完成 并 释放连接池
     *
     * @param futureList
     * @param completionService
     * @param <V>
     * @author cheng2839
     * @date 2018年11月16日
     */
    public <V> void awaitTasksFinished(List<Future> futureList, CompletionService<V> completionService) {
        try {
            if (futureList != null && futureList.size() != 0 && completionService != null) {
                LogUtils.showLog("等待批量任务[{}]执行。。。" + futureList.size());
                for (int n = 0; n < futureList.size(); n++) {
                    Future future = completionService.take();
                    if (future != null)
                        future.get();
                }
            }
        } catch (Exception e) {
            LogUtils.showLog("多线程获取结果异常: {}" + e);
        }
    }

    /**
     * 关闭线程池
     *
     * @param executorService
     * @author cheng2839
     * @date 2018年11月16日
     */
    public void shutdown(ExecutorService executorService) {
        try {
            if (executorService != null) {
                LogUtils.showLog("关闭线程池:{}" + executorService);
                executorService.shutdown();
            }
        } catch (Exception e) {
            if (!executorService.isTerminated()) {
                executorService.shutdownNow();
            }
        } finally {
            try {
                if (executorService != null && !executorService.isShutdown()) {
                    executorService.shutdown();
                }
            } catch (Exception e) {
                LogUtils.showLog("线程池关闭异常：{}" + e);
            }
        }
    }

}