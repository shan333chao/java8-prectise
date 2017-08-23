import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by HenDiao on 2017/8/21.
 */
public class ComplateableInAction {
    private final static  Random random=new Random(System.currentTimeMillis());
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture=new CompletableFuture<>();
        new Thread(()->{
           double value=get();
           completableFuture.complete(value);
        }).start();
        System.out.println("+++++++++++no block");
        Optional.ofNullable( completableFuture.get()).ifPresent(System.out::println);
        completableFuture.whenComplete((v,t)->{
           Optional.ofNullable(v).ifPresent(System.out::println);
           Optional.ofNullable(t).ifPresent(x->x.printStackTrace());
        });
    }

    private static double get(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextDouble();
    }
}
