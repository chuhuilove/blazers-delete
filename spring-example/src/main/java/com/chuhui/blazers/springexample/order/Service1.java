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
@Order(200)
public class Service1   {

    public Service1(){
        System.err.println("Service1 has created");
    }


}
