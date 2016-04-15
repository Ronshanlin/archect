/**
 * 
 */
package com.shanlin.demo.netty.nettyio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author 13073457
 */
public class TimeServer {
    public static void main(String[] args) {
        try {
            new TimeServer().bind(399);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChildChannelHandler());
            System.out.println("start server");
            
            // 绑定端口，同步等待返回
            ChannelFuture future = bootstrap.bind(port).sync();
            
            // 等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
