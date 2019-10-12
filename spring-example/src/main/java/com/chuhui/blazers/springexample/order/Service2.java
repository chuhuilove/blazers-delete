package com.chuhui.blazers.springexample.order;

import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * Service1
 *
 * @author: cyzi
 * @Date: 2019/10/12 0012
 * @Description:TODO
 */
@Service
@Order(100)
public class Service2 {

    public Service2() {
        System.err.println("Service2 has created");
    }


}
