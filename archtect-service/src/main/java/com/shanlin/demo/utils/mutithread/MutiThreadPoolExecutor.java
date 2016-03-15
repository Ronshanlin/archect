package com.shanlin.demo.utils.mutithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 多线程并发，线程池
 * 
 * @author shazl
 */
public class MutiThreadPoolExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MutiThreadPoolExecutor.class);
    private static final int THREAD_POOL_SIZE = 5;
    private static final MutiThreadPoolExecutor THREADPOOL = new MutiThreadPoolExecutor();
    private ExecutorService executorService;
    
    private MutiThreadPoolExecutor(){
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE, new MutiThreadFactory());
    }
    
    public static MutiThreadPoolExecutor getInstance(){
        return THREADPOOL;
    }
    /**
     * 执行线程
     * 
     * @param command
     */
    public void execute(Runnable command){
        executorService.execute(command);
    }
    
    /**
     * 线程工厂
     * 
     * @author shazl
     */
    public static class MutiThreadFactory implements ThreadFactory {
        private static final String THREADNAME = "mutithread-";
        private AtomicInteger threadNumber = new AtomicInteger();
        private boolean daemon = false;
        
        private ThreadGroup group;
        
        public MutiThreadFactory(){
            this(false);
        }
        
        public MutiThreadFactory(boolean daemon){
            SecurityManager manager = System.getSecurityManager();
            this.group = (manager==null)?Thread.currentThread().getThreadGroup():manager.getThreadGroup();
            
            this.daemon = daemon;
        }
        
        /* (non-Javadoc)
         * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
         */
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, THREADNAME+threadNumber.getAndIncrement(), 0);
            thread.setDaemon(daemon);
            
            LOGGER.info("thread name:"+thread.getName()+", thread group:"+group.getName());
            
            return thread;
        }
        
        public ThreadGroup getThreadGroup(){
            return this.group;
        }
        
    }
}
