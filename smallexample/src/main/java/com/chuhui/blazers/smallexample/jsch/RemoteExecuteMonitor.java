package com.chuhui.blazers.smallexample.jsch;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * RemoteExecuteMonitor
 *
 * @author: cyzi
 * @Date: 2019/10/8 0008
 * @Description:TODO
 */
public class RemoteExecuteMonitor {


    public static void main(String[] args) throws IOException, JSchException {

        String remoteString = getRemoteString();

        System.out.println(remoteString);


    }




    static String getRemoteString() throws JSchException, IOException {
        JSch jsch = new JSch();

        Session cyziSession = jsch.getSession("cyzi", "172.16.23.115", 22);
        cyziSession.setPassword("cyzi");
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //
        config.put("PreferredAuthentications", "password");
        cyziSession.setConfig(config);
        System.err.println(LocalDateTime.now() + " session invoke connect start");
        cyziSession.connect();
        System.err.println(LocalDateTime.now() + " session invoke connect end");
        Channel channel = cyziSession.openChannel("exec");



        String command = "source .bash_profile; mdbClusterShow admin/admin@ATD -p";

        ((ChannelExec) channel).setCommand(command);

        Session session = channel.getSession();


        channel.setInputStream(null);

        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();

        channel.connect();

        StringBuilder builder = new StringBuilder();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
                builder.append(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) {
                    continue;
                }
                System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }

        String s = builder.toString();
        channel.disconnect();
        session.disconnect();


        return s;

    }



}
