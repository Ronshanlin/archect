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
    
    private final ByteBuf firstMsg;
    
    /**
     * 
     */
    public TimeClientHandler() {
        byte[] req = "time client request msg".getBytes();
        
        firstMsg = Unpooled.buffer(req.length);
        firstMsg.writeBytes(req);
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMsg);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client channelRead");
        
        ByteBuf buf = (ByteBuf) msg;
        
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        
        String body = new String(bytes, "utf-8");
        
        System.out.println("client receive response is:"+body);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause);
        
        ctx.close();
    }

}
