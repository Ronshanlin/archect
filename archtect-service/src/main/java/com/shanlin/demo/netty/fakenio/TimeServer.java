/**
 * 
 */
package com.shanlin.demo.netty.fakenio;

import java.net.ServerSocket;
import java.net.Socket;

import com.shanlin.demo.netty.bio.TimeServerHandler;

/**
 * @author 13073457
 *
 */
public class TimeServer {
    
    public static void main(String[] args) throws Exception{
        int port=199;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(20, 1000);
            
            while (true) {
                socket = serverSocket.accept();
                executePool.execute(new TimeServerHandler(socket));
            }
            
        } finally {
            if (serverSocket!=null) {
                serverSocket.close();
            }
        }
        
    }
}
