/**
 * 
 */
package com.shanlin.demo.netty.nettyio;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 13073457
 *
 */
public class TimeServerHandler extends ChannelHandlerAdapter{
    private int counter;
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] reqBytes = new byte[buf.readableBytes()];
//        
//        buf.readBytes(reqBytes);
//        
//        String body = new String(reqBytes, "utf-8");
        String body = (String) msg;
        
        System.out.println("the time server recevie:"+body+"; counter is:"+ ++counter);
        
        // 写出
        String respMsg = "i am resp:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ByteBuf resp = Unpooled.copiedBuffer(respMsg.getBytes());
        ctx.writeAndFlush(resp);
    }
    
//    @Skip
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("channelReadComplete");
//        ctx.flush();
//    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server err");
        System.err.println(cause);
        
        ctx.close();
    }
}
