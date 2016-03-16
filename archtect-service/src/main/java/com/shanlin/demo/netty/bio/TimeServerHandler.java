/**
 * 
 */
package com.shanlin.demo.netty.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * @author 13073457
 */
public class TimeServerHandler implements Runnable{
    
    private Socket socket;
    
    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        
        try {
            reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            
            String line = null;
            while (true) {
                line = reader.readLine();
                if (line==null) {
                    break;
                }
                
                System.out.println("Time Server receive:"+line);
                
                writer.write(""+System.currentTimeMillis());
                writer.newLine();
                writer.flush();
                
                System.out.println("server resoponse");
            }
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader!=null) {
                    reader.close();
                }
                if (writer!=null) {
                    writer.close();
                }
                if (socket!=null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
