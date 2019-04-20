package com.chuhui.blazers.asm.tutorial.visitor.example2;

import com.chuhui.blazers.asm.tutorial.visitor.example1.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/20 0020.
 */
public class ObjectStructure {

    private List<Element> elements = new ArrayList<>();

    public void addElement(Element element) {
        elements.add(element);
    }

    public void remove(Element element) {
        elements.remove(element);
    }

    public void accept(final Visitor visitor) {

        if (CollectionUtils.isNotEmpty(elements)) {

            elements.forEach(e -> e.accecp(visitor));

        }
    }


}
