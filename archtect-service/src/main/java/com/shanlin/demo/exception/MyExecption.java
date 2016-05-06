/**
 * 
 */
package com.shanlin.demo.exception;

/**
 * @author 13073457
 *
 */
public class MyExecption extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public MyExecption(Throwable e){
        super(e);
    }

    /**
     * @param string
     */
    public MyExecption(String msg) {
        super(msg);
    }
}
