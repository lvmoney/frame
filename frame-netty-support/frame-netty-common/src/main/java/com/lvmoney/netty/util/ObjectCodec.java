package com.lvmoney.netty.util;

import com.lvmoney.common.utils.ObjectSerializerUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * <p>
 * create by lvmoney at 2018/3/3-下午1:42
 */
public class ObjectCodec extends MessageToMessageCodec<ByteBuf, Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) {
        byte[] data = ObjectSerializerUtil.serilizer(msg);
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(data);
        out.add(buf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        Object deSerilizer = ObjectSerializerUtil.deSerilizer(bytes);
        out.add(deSerilizer);
    }
}
