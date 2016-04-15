/**
 * 
 */
package com.shanlin.demo.netty.nettyio;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author 13073457
 *
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
     */
    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
        arg0.pipeline().addLast(new StringDecoder());
        arg0.pipeline().addLast(new TimeServerHandler());
    }

}
