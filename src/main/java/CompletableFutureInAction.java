import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureInAction {
    private  final static  Random RANDOM=new Random(System.currentTimeMillis());
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture =new CompletableFuture<>();
        new Thread(()->{
            double v = get();
            completableFuture.complete(v);


        }).start();
        System.out.println("11111");
        Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
        completableFuture.whenComplete((v,e)->{
           System.out.println(v);
           e.printStackTrace();
        });

    }

       static  double get(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
           double v = RANDOM.nextDouble();
        System.out.println(v);
           return v;
    }
}
