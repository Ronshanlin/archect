/**
 * 
 */
package com.shanlin.demo.netty.nettyio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author 13073457
 *
 */
public class TimeClient {
    public static void main(String[] args) {
        try {
            new TimeClient().connect("127.0.0.1", 399);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connect(String host, int port) throws Exception{
        
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        
        try {
            Bootstrap bootstrap = new Bootstrap();
            
            bootstrap.group(loopGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new TimeClientHandler());
                    }
                });
            
            // 发起异步连接操作
            ChannelFuture future = bootstrap.connect(host, port).sync();
            // 
            future.channel().close().sync();
        } finally {
            loopGroup.shutdownGracefully();
        }
        
    }
}
