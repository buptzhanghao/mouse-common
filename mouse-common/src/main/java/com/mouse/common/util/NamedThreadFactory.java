package com.mouse.common.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/1/14
 */
public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private static final String DEFAULT_POOL_PRIFEX = "THREAD-POOL";
    private final ThreadGroup group;
    private final String namePrefix;
    private final boolean isDaemon;

    public NamedThreadFactory() {
        this(DEFAULT_POOL_PRIFEX, false);
    }

    public NamedThreadFactory(String name) {
        this(name, false);
    }

    public NamedThreadFactory(String preffix, boolean daemon) {

        StringBuffer sb = new StringBuffer();
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = sb.append(preffix).append("-").append(poolNumber.getAndIncrement()).append("-THREAD-").toString();
        isDaemon = daemon;
    }

    public Thread newThread(Runnable r) {

        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        t.setDaemon(isDaemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
