package com.shanlin.demo.utils.mutithread;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shanlin.demo.exception.MyExecption;

/**
 * 多线程分批执行器<每个线程执行的任务不同><br>
 * 
 * <note>注意多线程操作同一对象问题</note>
 *
 * @author shazl
 */
public class MutiThreadExecutor<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MutiThreadExecutor.class);
    /**
     * 功能描述: 分批并发执行<br>
     *
     * @param callbacks
     * @return 每个线程执行结果的集合,如果线程发生异常，则不添加
     */
    public List<T> exec(final List<MutiThreadCallback<T>> callbacks){
        // 线程数与回调函数个数不同，直接返回
        if (callbacks == null || callbacks.isEmpty()) {
            return new ArrayList<T>();
        }
        List<T> restultList = new ArrayList<T>();

        List<MutiThreadRunnable<T>> runnableList = new ArrayList<MutiThreadRunnable<T>>();
        
        MutiThreadRunnable<T> runnable = null;
        for (int i = 0; i < callbacks.size(); i++) {
            // 获取对应的回调函数
            final MutiThreadCallback<T> callback = callbacks.get(i);
            // 创建线程
            runnable = new MutiThreadRunnable<T>(new MutiThreadCallback<T>() {
                @Override
                public T call() throws MyExecption {
                    try {
                        return callback.call();
                    } catch (Exception e) {
                        throw new MyExecption(e);
                    }
                }
            });
            
            // 启动线程
            MutiThreadPoolExecutor.getInstance().execute(runnable);
            
            runnableList.add(runnable);
        }
        
        boolean isDone = false;
        while (!isDone) {
            // 循环迭代，查看是否完成
            for (MutiThreadRunnable<T> mutiThread : runnableList) {
                if (!mutiThread.isDone()) {
                    isDone = false;
                    break;
                }
                isDone = true;
            }
        }
        
        // 添加到结果集
        for (MutiThreadRunnable<T> mutiThread : runnableList) {
            if (mutiThread.isErr()) {
                LOGGER.error("MutiThreadCallback exec err:"+mutiThread.getErrMsg());
                continue;
            }
            restultList.add(mutiThread.get());
        }
        
        return restultList;
    }
}
