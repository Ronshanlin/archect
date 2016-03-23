/**
 * 
 */
package com.shanlin.demo.netty.jdknio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @author shazl
 */
public class ReactorTask implements Runnable{
    private Selector selector;
    private ServerSocketChannel socketChannel;
    private volatile boolean stop = false;
    
    /**
     * @throws IOException 
     */
    public ReactorTask(int port){
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port),1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
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
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    handInput(selectionKey);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 处理输入
     * 
     * @param selectionKey
     * @throws IOException
     */
    public void handInput(SelectionKey selectionKey) throws IOException{
        if (!selectionKey.isValid()) {
            return;
        }
        
        // 处理新接入的请求消息
        if (selectionKey.isAcceptable()) {
            // 接受新连接
            ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            
            // 添加新连接到selector
            sc.register(selector, SelectionKey.OP_READ);
        }
        
        if (selectionKey.isReadable()) {
            // 读取数据
            SocketChannel sc = (SocketChannel) selectionKey.channel();
            // 
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int readBytes = sc.read(buffer);
            
            if (readBytes > 0) {
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                
                String body = new String(bytes, "utf-8");
                
                System.out.println("request:"+ body);
                
                // DO write
                this.doWrite(sc, "current time:"+System.currentTimeMillis());
            }else if(readBytes < 0){
                // 链路关闭，需关闭socketChannel, 释放资源
                selectionKey.channel();
                sc.close();
            }else {
                // 属正常场景，异步非阻塞读取，可能会读取不到，忽略
            }
        }
    }
    
    /**
     * 写出
     * 
     * @param sc
     * @param response
     * @throws IOException
     */
    private void doWrite(SocketChannel sc, String response) throws IOException{
        if (StringUtils.isBlank(response)) {
            return;
        }
        
        byte[] bytes = response.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        
        byteBuffer.put(bytes);
        byteBuffer.flip();
        
        sc.write(byteBuffer);
    }
}
