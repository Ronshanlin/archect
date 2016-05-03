/**
 * 
 */
package com.shanlin.demo.netty.customize;

import com.shanlin.demo.netty.customize.encoder.Header;
import com.shanlin.demo.netty.customize.encoder.MyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 13073457
 *
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter{
    
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        MyMessage message = new MyMessage();
        Header header = new Header();
        header.setType(Byte.parseByte("3"));
        message.setHeader(header);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        MyMessage message = (MyMessage) msg;
        
        if (message.getHeader()!=null) {
            byte loginResult = (Byte) message.getBody();
            if (loginResult != 0) {
                ctx.close(); // 握手失败，关闭连接
            }else{
                System.out.println("login is ok:"+message);
                ctx.fireChannelRead(msg);
            }
        }else {
            ctx.fireChannelRead(msg);
        }
    }
}
