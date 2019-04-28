package com.chuhui.blazers.collection.map;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

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
                if (vmd.displayName().endsWith("MapTest")) {
                    VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                    virtualMachine.loadAgent("D:\\MyLife\\Code\\person\\java\\javaagent\\target\\javaagent-1.0.jar","12314144");
                    System.out.println("ok");
                    virtualMachine.detach();
                }


        }
    }

}
