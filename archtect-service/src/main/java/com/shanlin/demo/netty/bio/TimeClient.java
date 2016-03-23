/**
 * 
 */
package com.shanlin.demo.netty.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 13073457
 *
 */
public class TimeClient {
    
    public static void main(String[] args) {
        int port = 199;
        Socket socket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        
        try {
            socket = new Socket("127.0.0.1", port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            writer.write("query current time");
            writer.newLine();
            writer.flush();
            System.out.println("cilent write");
            
            String resp = reader.readLine();
            System.out.println("client receive respones:"+resp);
        } catch (UnknownHostException e) {
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
