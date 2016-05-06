package com.shanlin.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shanlin.demo.exception.MyExecption;

/**
 * @author shazl
 */
public class DistributedLock{
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLock.class);
    /**
     * 锁的名称
     */
    private String lockName;
    /**
     * 最大锁定时间
     */
    private int maxLockTime;
    /**
     * 尝试获取锁，最大等待锁的时间
     */
    private long maxWaitTime;

    public DistributedLock(String lockName, int maxLockTime, long maxWaitTime) {
        this.lockName = lockName;
        this.maxLockTime = maxLockTime;
        this.maxWaitTime = maxWaitTime;
    }

    /**
     * 已经获取过锁
     */
    private ThreadLocal<Boolean>  hasAquired=new ThreadLocal<Boolean>();
    /**
     * 重新尝试获取锁的时间
     */
    private int retryTime = 10;

    public void lock() throws MyExecption {
        long current = System.currentTimeMillis();
        while (true) {
            try {
                // 获取锁
                Long lock = /*cacheService.setnx(lockName, "a")*/null;
                
                if (lock == null || lock == 0) {
                    // 锁失败，等待重试
                    Thread.sleep(retryTime);  //  重试时间
                    if (System.currentTimeMillis() - current > maxWaitTime) {
                        throw new MyExecption("no lock, too many wait");
                    }
                } else {
                    // 为锁加过期时间
                    hasAquired.set(true);
                    //cacheService.expire(lockName, maxLockTime);
                    return;  // 成功设置
                }
            } catch (MyExecption ex) {
                throw ex;
            } catch (Exception ex) {
                throw new MyExecption("get lock failed");
            }
        }
    }

    public void unlock() {
        if (!hasAquired.get()) {
            return;
        }
        // 删除锁
        try {
            //cacheService.del(lockName);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
