/**
 * 
 */
package com.shanlin.demo.netty.customize.decoder;

import java.util.HashMap;
import java.util.Map;

import com.shanlin.demo.netty.customize.encoder.Header;
import com.shanlin.demo.netty.customize.encoder.MyMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author 13073457
 *
 */
public class MyMessageDecoder extends LengthFieldBasedFrameDecoder{
    private MarshallingDecoder decoder;
    
    /**
     * @param maxFrameLength
     * @param lengthFieldOffset
     * @param lengthFieldLength
     */
    public MyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        decoder = new MarshallingDecoder();
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception{
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame==null) {
            return null;
        }
        
        MyMessage message = new MyMessage();
        Header header  = new Header();
        header.setCrCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionID(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());
        
        int size = in.readInt();
        if (size>0) {
            Map<String, Object> attach = new HashMap<String, Object>(size);
            
            String key = null;
            byte[] bytesKey = null;
            for (int i = 0; i < size; i++) {
                bytesKey = new byte[in.readInt()];
                in.readBytes(bytesKey);
                
                key = new String(bytesKey);
                attach.put(key, decoder.decode(in));
            }
            
            header.setAttachment(attach);
        }
        if (in.readableBytes()>4) {
            message.setBody(decoder.decode(in));
        }
        
        message.setHeader(header);
        
        return message;
    }
}
