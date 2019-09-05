package com.chuhui.blazers.smallexample.jsch;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * RemoteExecuteAwk
 *
 * @author: cyzi
 * @Date: 2019/8/8 0008
 * @Description:TODO
 */
public class RemoteExecuteAwk {


    static final DateTimeFormatter yyyyMMddHHmmssFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    static final DateTimeFormatter yyyyMMddHHmmssFormatterConver = DateTimeFormatter.ofPattern("HH:mm:ss");
    static String computed = "20190808134406";

    public static void main(String[] args) throws JSchException, IOException {

        String remoteString = getRemoteString();
        int hours = 3;


        String[] split = remoteString.split("\\n");

        Map<String, List<PressureVO>> collect = Arrays.stream(split).map(e -> {
            String[] datas = e.split(" ");

            // 时间 ip  group_id   insert     update     delete     write      query

            PressureVO pressureVO = new PressureVO();
            // 转换时间格式
            pressureVO.setTimePoint(LocalDateTime.parse(datas[0], yyyyMMddHHmmssFormatter).format(yyyyMMddHHmmssFormatterConver));
            pressureVO.setIp(datas[1]);
            pressureVO.setGroupID(datas[2]);
            pressureVO.setInsert(datas[3]);
            pressureVO.setUpdate(datas[4]);
            pressureVO.setDelete(datas[5]);
            pressureVO.setWrite(datas[6]);
            pressureVO.setQuery(datas[7]);
            pressureVO.computeTotal();

            return pressureVO;
        }).collect(Collectors.groupingBy(PressureVO::getIp));


        System.err.println("pause");
        System.err.println("pause");
        System.err.println("pause");


    }

    static String getRemoteString() throws JSchException, IOException {
        JSch jsch = new JSch();

        Session cyziSession = jsch.getSession("cyzi", "172.16.23.115", 22);
        cyziSession.setPassword("cyzi");
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //
        config.put("PreferredAuthentications","password");
        cyziSession.setConfig(config);
        System.err.println(LocalDateTime.now()+" session invoke connect start");
        cyziSession.connect();
        System.err.println(LocalDateTime.now()+" session invoke connect end");
        Channel channel = cyziSession.openChannel("exec");

        String fileName = " $QuickMDB_HOME/qmdbcluster/atd/statistic/workload.txt";

        String awkFile = " awk 'BEGIN{diffTime=strftime(\"%Y%m%d%H%M%S\",systime()-6*3600); print diffTime}{if($1>diffTime) print  $1,$2,$3,$4,$5,$6,$7,$8 \n}' " + fileName;


        String command = " source .bash_profile;" + awkFile;

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
                if (i < 0) break;
                builder.append(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) continue;
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
