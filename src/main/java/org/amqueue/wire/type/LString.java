package org.amqueue.wire.type;

import java.io.UnsupportedEncodingException;

/**
 * used to hold chunks of binary data
 * @author thundercumt
 *
 */
public class LString {
    private byte[] value; //utf8 encoding
    private int offset;
    private int len;

    public LString(byte[] octets, int offset, int len) {
        if (octets == null
                || offset < 0 || offset >= octets.length
                || len < 0 || len > octets.length) {
            throw new IllegalArgumentException();
        }

        this.value = octets;
        this.offset = offset;
        this.len = len;
    }

    public LString(LString s) {
        value = new byte[s.len];
    }

    public String toString() {
        try {
            return new String(value, offset, len, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("invalid value encoding", e);
        }
    }
}
