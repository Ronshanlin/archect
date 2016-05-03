/**
 * 
 */
package com.shanlin.demo.netty.customize.encoder;

/**
 * @author 13073457
 *
 */
public final class MyMessage {
    private Header header;
    private Object body;
    
    public Header getHeader() {
        return header;
    }
    public void setHeader(Header header) {
        this.header = header;
    }
    public Object getBody() {
        return body;
    }
    public void setBody(Object body) {
        this.body = body;
    }
    
    @Override
    public String toString() {
        return "MyMessage [header=" + header + ", body=" + body + "]";
    }
}
