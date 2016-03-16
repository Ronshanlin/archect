/**
 * 
 */
package com.shanlin.demo.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 13073457
 */
public class TimeServer {
    
    public static void main(String[] args) {
        ServerSocket server = null;
        int port = 99;
        try {
            server = new ServerSocket(port);
            
            Socket socket = null;
            
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server!=null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
