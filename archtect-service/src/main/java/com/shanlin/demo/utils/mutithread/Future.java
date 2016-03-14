package com.shanlin.demo.utils.mutithread;

/**
 * 多线程分批执行<br> 
 *
 * @author shazl
 */
public interface Future<T>{
    public boolean isDone();
    public boolean isErr();
    public T get();
    public String getErrMsg();
}
