package com.chuhui.blazers.socket.nio.example;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

/**
 * CommonUtil
 *
 * @author: 纯阳子
 * @Date: 2019/5/26
 * @Description:TODO
 */
public final class CommonUtil {

    public static final void commonMethod(Selector selector, Consumer<SelectionKey> consumer){

        try {
            selector.select(1000L);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey key = null;
            while (iterator.hasNext()) {

                key = iterator.next();
                iterator.remove();
                consumer.accept(key);
                if (key != null) {
                    key.cancel();
                    if (key.channel() != null) {
                        key.channel().close();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
