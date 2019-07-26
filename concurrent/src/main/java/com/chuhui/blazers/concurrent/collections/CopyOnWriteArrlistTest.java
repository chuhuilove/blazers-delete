package com.chuhui.blazers.concurrent.collections;

import com.chuhui.blazers.commcustome.CustomerThreadFactory;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * CopyOnWriteArrlistTest
 * 多线程填充{@link java.util.concurrent.CopyOnWriteArrayList},测试使用java监视工具jstat
 * 文档：java虚拟机性能监视工具 jdk 8.md
 * 链接：http://note.youdao.com/noteshare?id=475dcd284d3b7aee8c445fa70ea36af9&sub=B00AD4433BED438AAAFD020F40241405
 *
 * @author: 纯阳子
 * @Date: 2019/6/19
 * @Description:
 */
public class CopyOnWriteArrlistTest {

    private final List<TestModel> safeList = new CopyOnWriteArrayList<>();

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int MAX_COUNT = THREAD_COUNT << 26;

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT, 2000L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(THREAD_COUNT), new CustomerThreadFactory("CopyOnWritelist-"));

    public static void main(String[] args) {

        CopyOnWriteArrlistTest listTest = new CopyOnWriteArrlistTest();

        IntStream.rangeClosed(1, THREAD_COUNT).forEach(e -> listTest.executor.execute(() -> listTest.run()));

        listTest.executor.shutdown();

        while (listTest.executor.isTerminated()) {
        }


    }


    void run() {

        int count = 1;
        while (count++ < MAX_COUNT) {

            int dataInt1 = ThreadLocalRandom.current().nextInt();
            int dataInt2 = ThreadLocalRandom.current().nextInt();
            long dataLong1 = ThreadLocalRandom.current().nextLong();
            long dataLong2 = ThreadLocalRandom.current().nextLong();
            String uuid = Thread.currentThread().getName() + "--->" + UUID.randomUUID().toString();

            safeList.add(new TestModel(dataInt1, dataInt2, dataLong1, dataLong2, uuid));
        }

    }


    static private class TestModel {
        private int dataInt1;
        private int dataInt2;
        private long dataLong1;
        private long dataLong2;
        private String uuid;

        public TestModel(int dataInt1, int dataInt2, long dataLong1, long dataLong2, String uuid) {
            this.dataInt1 = dataInt1;
            this.dataInt2 = dataInt2;
            this.dataLong1 = dataLong1;
            this.dataLong2 = dataLong2;
            this.uuid = uuid;
        }
    }


}
