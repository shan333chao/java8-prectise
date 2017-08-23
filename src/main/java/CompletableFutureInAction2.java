import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureInAction2 {
    private  final static  Random RANDOM=new Random(System.currentTimeMillis());
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        AtomicInteger finished=new AtomicInteger(0);
            Executor executor = Executors.newFixedThreadPool(2,r->{
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            return thread;
        });
        CompletableFuture<Double> doubleCompletableFuture = CompletableFuture.supplyAsync(CompletableFutureInAction::get, executor)
                .whenComplete((v, t) -> {
                    finished.addAndGet(1);
                    System.out.println("finished:"+finished.get());
                    Optional.of(v).ifPresent(System.out::println);
                    Optional.of(t).ifPresent(tw -> tw.printStackTrace());

                });
        System.out.println("11111");

        while(finished.get()!=1){
            Thread.sleep(500);
            System.out.println(finished.get());
        }


    }


}
