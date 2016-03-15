package com.shanlin.demo.utils.mutithread;

import com.shanlin.demo.exception.MyExecption;

/**
 * 多线程分批执行回调函数<br> 
 *
 * @author shazl
 */
public interface MutiThreadCallback<T> {
    public T call() throws MyExecption;
}
