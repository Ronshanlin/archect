/**
 * 
 */
package com.shanlin.demo.netty.customize;

import java.util.concurrent.TimeUnit;

import com.shanlin.demo.netty.customize.encoder.Header;
import com.shanlin.demo.netty.customize.encoder.MyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * @author 13073457
 *
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter{
    private volatile ScheduledFuture<?> heartbeat;
    
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
        
        heartbeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 
                0, 5000, TimeUnit.MICROSECONDS);
    }
    
    private class HeartBeatTask implements Runnable{
        private ChannelHandlerContext ctx;
        
        /**
         * 
         */
        public HeartBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            MyMessage heartBeatMsg = new MyMessage();
            ctx.writeAndFlush(heartBeatMsg);
        }
        
    }
}
