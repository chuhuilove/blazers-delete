package com.chuhui.blazers.algorithm.bigfile;

import com.alibaba.druid.util.StringUtils;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

import static com.chuhui.blazers.commcustome.constant.Constaints.commonlyUserDateTimeFormat;
import static com.chuhui.blazers.commcustome.constant.Constaints.returnCurrentTimeFormated;

/**
 * SearchUrl
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/11/11
 */
public class SearchUrl {


    private final static int MOD = 31;

    public static void main(String[] args) throws IOException {

        /**
         * 将这个两个文件拆分成个文件
         */
        System.err.println("start:" + returnCurrentTimeFormated(commonlyUserDateTimeFormat));


        final File urlFile1 = new File("url1.txt");

        final File urlFile2 = new File("url2.txt");

        final BufferedReader bufferedReader1 = new BufferedReader(new FileReader(urlFile1));
        final BufferedReader bufferedReader2 = new BufferedReader(new FileReader(urlFile2));


        Map<Integer, BufferedWriter> sizeFile = new HashMap<>(21);


        String line1;
        String line2;


//        Files.lines(FileSystems.getDefault().getPath("url1.txt"))
//                .parallel()
//                .map(e->{
//
//                })
//

//        BufferedReader bufferedReader = Files.newBufferedReader(FileSystems.getDefault().getPath("url.txt"));






        while ((line1 = bufferedReader1.readLine()) != null && (line2 = bufferedReader2.readLine()) != null) {
            writeToFile(sizeFile, line1);
            writeToFile(sizeFile, line2);
        }
        System.err.println("hash分流完成:" + returnCurrentTimeFormated(commonlyUserDateTimeFormat));

        final Map<Integer, BufferedReader> readFile = new HashMap<>(sizeFile.size());


        sizeFile.forEach((k, v) -> {
            try {
                v.close();
                readFile.put(k, Files.newBufferedReader(FileSystems.getDefault().getPath(k + "")));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        readFile.forEach((key, value) -> {
            Set<String> str = new HashSet<>();
            try {
                String line = value.readLine();
                while (!StringUtils.isEmpty(line)) {
                    if (str.contains(line)) {
                        System.err.println(line);
                    } else {
                        str.add(line);
                    }
                    line = value.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        System.err.println("end:" + returnCurrentTimeFormated(commonlyUserDateTimeFormat));
    }


    static void writeToFile(Map<Integer, BufferedWriter> map, String line) throws IOException {

        if (!StringUtils.isEmpty(line)) {

            int mod = line.hashCode() % MOD;
            BufferedWriter writer = map.get(mod);

            if (writer == null) {
                File file = new File(mod + "");
                file.createNewFile();
                writer = new BufferedWriter(new FileWriter(file));
                map.put(mod, writer);
            }
            writer.write(line + System.lineSeparator());
        }
    }


}
