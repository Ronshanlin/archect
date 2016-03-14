package com.suning.gcps.utils.mutithread;

import com.suning.gcps.exception.AppException;

/**
 * 多线程分批执行回调函数<br> 
 *
 * @author shazl
 */
public interface MutiThreadCallback<T> {
    public T call() throws AppException;
}
