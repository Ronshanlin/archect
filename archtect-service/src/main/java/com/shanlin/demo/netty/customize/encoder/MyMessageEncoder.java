/**
 * 
 */
package com.shanlin.demo.netty.customize.encoder;

import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author 13073457
 *
 */
public class MyMessageEncoder extends MessageToMessageEncoder<MyMessage> {
    private MarshallingEncoder marshallingEncoder;
    
    /* (non-Javadoc)
     * @see io.netty.handler.codec.MessageToMessageEncoder#encode(io.netty.channel.ChannelHandlerContext, java.lang.Object, java.util.List)
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, MyMessage msg, List<Object> out) throws Exception {
        Assert.notNull(msg);
        
        ByteBuf byteBuf = Unpooled.buffer();
        
        byteBuf.writeInt(msg.getHeader().getCrCode());
        byteBuf.writeInt(msg.getHeader().getLength());
        byteBuf.writeLong(msg.getHeader().getSessionID());
        byteBuf.writeByte(msg.getHeader().getType());
        byteBuf.writeByte(msg.getHeader().getPriority());
        byteBuf.writeInt(msg.getHeader().getAttachment().size());
        
        byte[] keyArr;
        
        for (Map.Entry<String, Object> entry : msg.getHeader().getAttachment().entrySet()) {
            keyArr = entry.getKey().getBytes(CharsetUtil.UTF_8);
            byteBuf.writeInt(keyArr.length);
            byteBuf.writeBytes(keyArr);
            
            marshallingEncoder.encode(entry.getValue(), byteBuf);
        }
    }

}
