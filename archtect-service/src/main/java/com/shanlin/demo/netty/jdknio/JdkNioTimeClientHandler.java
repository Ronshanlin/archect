/**
 * 
 */
package com.shanlin.demo.netty.jdknio;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author 13073457
 *
 */
public class JdkNioTimeClientHandler implements Runnable{
    private int port;
    private String host;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop = false;
    
    /**
     * 
     */
    public JdkNioTimeClientHandler(String host, int port) {
        this.host = (host==null?"127.0.0.1":host);
        this.port = port;
        
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            this.socketChannel.configureBlocking(false);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void stop(){
        this.stop = true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        
    }

}
