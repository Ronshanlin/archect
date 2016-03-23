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
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] reqBytes = new byte[buf.readableBytes()];
        
        buf.readBytes(reqBytes);
        
        String body = new String(reqBytes, "utf-8");
        System.out.println("the time server recevie:"+body);
        
        // 写出
        String respMsg = "i am resp:"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ByteBuf resp = Unpooled.copiedBuffer(respMsg.getBytes());
        ctx.write(resp);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        ctx.flush();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
