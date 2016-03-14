package com.suning.gcps.utils.mutithread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多线程runnable<br>
 * 
 * @author shazl
 */
public class MutiThreadRunnable<T> implements Runnable, Future<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MutiThreadRunnable.class); 
    private static final long TIMEOUT = 3000L;
    private boolean isDone = false;
    private boolean isErr = false;
    private String errMsg;
    private T data;
    
    private MutiThreadCallback<T> callback;

    public MutiThreadRunnable(MutiThreadCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        // 创建计时器,仅且执行一次
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(timer), new Date(), TIMEOUT);
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public T get() {
        return this.data;
    }

    @Override
    public boolean isErr() {
        return this.isErr;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }
    
    /**
     * 内部类，计时器任务<br> 
     *
     * @author shazl
     */
    private class MyTimerTask extends TimerTask{
        private boolean isTimeOut = false;
        private Timer timer;
        
        public MyTimerTask(Timer timer){
            this.timer = timer;
        }
        
        @Override
        public void run() {
           // 超时
           if (isTimeOut) {
                isDone = true;
                isErr = true;
                errMsg = "time out!";
                this.timer.cancel();
                return;
            }
            
            isTimeOut = true;
            try {
                // 调用回调函数
                data = callback.call();
                isDone = true;
            } catch (Exception e) {
                isDone = true;
                isErr = true;
                errMsg = e.getMessage();
                LOGGER.error("多线程并发，执行回调发生异常：", e);
            } finally{
                this.timer.cancel();
            }
        }
    }
}
