/**
 * 
 */
package com.shanlin.demo.netty.customize.encoder;

import io.netty.buffer.ByteBuf;

/**
 * @author shazl
 *
 */
public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER=new byte[4];
    
    protected void encode(Object msg, ByteBuf out){
        int lengthPos = out.writerIndex();
        out.writeBytes(LENGTH_PLACEHOLDER);
    }
}
