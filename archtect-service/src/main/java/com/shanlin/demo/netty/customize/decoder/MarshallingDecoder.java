/**
 * 
 */
package com.shanlin.demo.netty.customize.decoder;

import io.netty.buffer.ByteBuf;

/**
 * @author shazl
 *
 */
public class MarshallingDecoder {
    private static final byte[] LENGTH_PLACEHOLDER=new byte[4];
    
    protected Object decode(ByteBuf in){
        int objectSize = in.readInt();
        ByteBuf buf = in.slice(in.readerIndex(), objectSize);
        
        
        return null;
    }
}
