package ch.chang.concurrent.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用countDown 实现拆分
 * 一个时间多个table
 * 一个table 多个任务，
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        Event[] events = new Event[]{new Event("e-1", 10), new Event("e-2", 8)};
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for (int i = 0; i < events.length; i++) {
            List<Table> table = getTable(events[i]);
            for (int j = 0; j < table.size(); j++) {
                executorService.submit(new DoSomthingOne(table.get(j)));
                executorService.submit(new DoSomthingTwo(table.get(j)));
            }
        }
        executorService.shutdown();
    }

    private static List<Table> getTable(Event event) {
        List<Table> tableList = new ArrayList<>();
        for (int i = 0; i < event.size; i++) {
            Table table = new Table(event.eventName + "-e-" + i+"-t", 2, event);
            tableList.add(table);
        }
        return tableList;
    }

    static class Event {
        private String eventName;
        private int size;
        private final CountDownLatch countDownLatch;

        public Event(String eventName, int size) {
            this.eventName = eventName;
            this.size = size;
            this.countDownLatch = new CountDownLatch(size);
        }

        public void down() {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println(eventName + "-e  is finished!");
            }
        }
    }

    static class Table {
        private String tableName;
        private int size;
        private final CountDownLatch countDownLatch;
        private final Event event;

        public Table(String tableName, int size, Event event) {
            this.tableName = tableName;
            this.size = size;
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }

        public void down() {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println(tableName + " is finished!");
                event.down();
            }
        }
    }

    static class DoSomthingOne implements Runnable {
        private Table tablel;

        public DoSomthingOne(Table tablel) {
            this.tablel = tablel;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tablel.down();
        }
    }

    static class DoSomthingTwo implements Runnable {
        private Table tablel;

        public DoSomthingTwo(Table tablel) {
            this.tablel = tablel;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tablel.down();
        }
    }
}
