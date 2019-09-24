package com.chuhui.blazers.dyproxy.staticproxy.aggregation;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * UserService
 *
 * @author: cyzi
 * @Date: 2019/9/24 0024
 * @Description:TODO
 */
public class UserServiceTest {

    static final String ID = "cyzi";


    @Test
    public void addUserCase(){
        UserService service=new UserServiceImpl();
        service.addUser(ID);
    }


    @Test
    public void addUserTimeCase(){
        UserService service=new UserServiceTimeImpl(new UserServiceImpl());
        service.addUser(ID);
    }

    @Test
    public void addUserAutorCase(){
        UserService service=new UserServiceAutorImpl(new UserServiceImpl());
        service.addUser(ID);
    }


    @Test
    public void addUserAutorAndTime(){
        UserService service=new UserServiceAutorImpl(new UserServiceTimeImpl(new UserServiceImpl()));
        service.addUser(ID);
    }

    @Test
    public void addUserTimeAndAutor(){
        UserService service=new UserServiceTimeImpl(new UserServiceAutorImpl(new UserServiceImpl()));
        service.addUser(ID);
    }

    @Test
    public void addUserTimeAndAutor2(){
        UserService service=new UserServiceTimeAutorImpl(new UserServiceTimeImpl(new UserServiceImpl()));
        service.addUser(ID);
    }




}
