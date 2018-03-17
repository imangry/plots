package com.qunar.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Date: 18/02/27
 * User: lvshi
 */
public class ByteBufSliceAndRetain {

    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        buffer.retain();
        System.out.println(buffer.capacity() + " ; " + buffer.refCnt());
        buffer.release();
        System.out.println(buffer.capacity() + " ; " + buffer.refCnt());
        buffer.setByte(0, 1);
        System.out.println(buffer.capacity() + " ; " + buffer.refCnt());

        //slice 不会retain；如果要增加引用计数的话，需要手动retain()
        ByteBuf slice = buffer.slice(5, 1);
        System.out.println(slice.capacity() + " ; " + slice.refCnt());

    }
}
