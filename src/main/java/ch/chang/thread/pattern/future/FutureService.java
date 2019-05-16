package ch.chang.thread.pattern.future;

import java.util.function.Consumer;


public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task) {
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
        }).start();
        return asynFuture;
    }

    public <T> void submit(final FutureTask<T> task, final Consumer<T> consumer) {
        new Thread(() -> {
            T result = task.call();
            consumer.accept(result);
        }).start();
    }
}