package com.chuhui.blazers.collection.map;

import com.sun.tools.attach.*;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * Scanner java agent 的 agentmain
 *
 * @author: 纯阳子
 * @Date: 2019/4/27
 * @Description:TODO
 */
public class Scanner {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {


        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {


            if (vmd.displayName().endsWith("BatchTest")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("F:\\chunyangzi-program\\customercode\\javaagent\\target\\javaagent-1.0.jar", "12314144");
                System.out.println("ok");
                virtualMachine.detach();
            }
        }


    }




}
