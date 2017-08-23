import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

public class CompletableFutureInAction5 {
    public static void main(String[] args) throws InterruptedException {
//        CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"is runing1");
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 1;
//        }) .runAfterBoth(CompletableFuture.supplyAsync(()->{
//                    System.out.println(Thread.currentThread().getName()+"is runing2");
//                    return 2;
//                }),()->System.out.println("done"));

//        CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"is runing1");
//            return CompletableFutureInAction.get();
//        }).applyToEither(  CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"is runing2");
//            return CompletableFutureInAction.get();
//        }),v->v*10).thenAccept(System.out::println);

//        CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"is runing1");
//            return CompletableFutureInAction.get();
//        }).acceptEither(CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"is runing2");
//            return CompletableFutureInAction.get();
//        }),System.out::println);
//        CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"is runing1");
//            return CompletableFutureInAction.get();
//        }).runAfterEither(CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"is runing2");
//            return CompletableFutureInAction.get();
//        }),()->System.out.println("done"));
        List<Integer> productIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<CompletableFuture<Double>> collect = productIds.stream().map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction::get))
                .map(s->s.thenApply(CompletableFutureInAction3::mutiply)).collect(toList());
        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()])).thenRun(()->System.out.println("done"));
        Thread.currentThread().join();
    }
}
