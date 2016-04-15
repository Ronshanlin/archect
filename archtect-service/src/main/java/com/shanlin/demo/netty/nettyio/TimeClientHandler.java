/**
 * 
 */
package com.shanlin.demo.netty.nettyio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 13073457
 *
 */
public class TimeClientHandler extends ChannelHandlerAdapter{
    private byte[] req;
    private int counter;
    /**
     * 
     */
    public TimeClientHandler() {
        req = ("time client request msg"+System.getProperty("line.seperator")).getBytes();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = null;
        
        for (int i = 0; i < 100; i++) {
            buf = Unpooled.buffer(req.length);
            buf.writeBytes(req);
            
            ctx.writeAndFlush(req);
        }
        
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client channelRead");
//        
//        ByteBuf buf = (ByteBuf) msg;
//        
//        byte[] bytes = new byte[buf.readableBytes()];
//        buf.readBytes(bytes);
//        
//        String body = new String(bytes, "utf-8");
        String body = (String)msg;
        
        System.out.println("client receive response is:"+body+"; counter is:"+ ++counter);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client err");
        System.err.println(cause);
        
        ctx.close();
    }

}
