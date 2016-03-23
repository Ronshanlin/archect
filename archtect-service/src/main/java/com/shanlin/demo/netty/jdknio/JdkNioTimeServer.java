/**
 * 
 */
package com.shanlin.demo.netty.jdknio;

import java.io.IOException;

/**
 * @author 13073457
 *
 */
public class JdkNioTimeServer {
    
    public static void main(String[] args) throws IOException {
        new Thread(new ReactorTask(299)).start();
    }
}
