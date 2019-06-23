package ch.chang.concurrent.utils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 生产者消费者
 * condition 必须有 锁
 */
public class ConditionDemo {
    private static final Lock lock = new ReentrantLock(true);
    private static final Condition producerCondition = lock.newCondition();
    private static final Condition consumerCondition = lock.newCondition();
    private static final LinkedList<Long> chain = new LinkedList<>();
    private static final int CHAIN_MAX_SIZE = 20;

    public static void main(String[] args) {
        IntStream.range(0, 20).boxed().forEach(ConditionDemo::startProducer);
        IntStream.range(0, 12).boxed().forEach(ConditionDemo::startConsumer);
    }

    private static void startProducer(int id) {
        new Thread(() -> {
            while (true) {
                producer();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "P-" + id).start();
    }

    private static void startConsumer(int id) {
        new Thread(() -> {
            while (true) {
                consumer();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C-" + id).start();
    }

    private static void producer() {
        try {
            lock.lock();
            while (chain.size() >= CHAIN_MAX_SIZE) {
                producerCondition.await();
            }
            long pushData = System.currentTimeMillis();
            chain.addLast(pushData);
            System.out.println(Thread.currentThread().getName() + " -> values: " + pushData);

            consumerCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consumer() {
        try {
            lock.lock();
            while (chain.size() <= 0) {
                consumerCondition.await();
            }
            Long firstData = chain.removeFirst();
            System.out.println(Thread.currentThread().getName() + " -> values: " + firstData);
            producerCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
