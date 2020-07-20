package cc.ghast.packet.wrapper.buffer.types.java;

import cc.ghast.packet.wrapper.buffer.BufConverter;
import io.netty.buffer.ByteBuf;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class LongConverter extends BufConverter<Long> {

    public LongConverter() {
        super("Long", Long.class);
    }

    @Override
    public void write(ByteBuf buffer, Long value) {
        buffer.writeLong(value);
    }

    @Override
    public Long read(ByteBuf buffer) {
        return buffer.readLong();
    }
}