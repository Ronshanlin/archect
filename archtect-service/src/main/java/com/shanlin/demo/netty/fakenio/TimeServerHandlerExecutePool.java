/**
 * 
 */
package com.shanlin.demo.netty.fakenio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 13073457
 *
 */
public class TimeServerHandlerExecutePool {
    private ExecutorService executor;
    
    /**
     * 
     */
    public TimeServerHandlerExecutePool(int maxPoolSize, int queuePoolSize) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(queuePoolSize);
        
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize, 120L, TimeUnit.SECONDS, workQueue);
    }
    
    public void execute(Runnable task){
        executor.execute(task);
    }
}
