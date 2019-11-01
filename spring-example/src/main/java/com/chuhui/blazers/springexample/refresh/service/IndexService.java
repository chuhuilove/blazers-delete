package com.chuhui.blazers.springexample.refresh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IndexService
 *
 * @author: cyzi
 * @Date: 2019/11/1 0001
 * @Description:TODO
 */
@Service
public class IndexService {


    @Autowired
    private LoginService service;

    public String getIndexService(){
        System.err.println(IndexService.class.getTypeName());
        return getClass().getSimpleName();
    }

}
