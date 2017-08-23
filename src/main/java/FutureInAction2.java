import java.util.concurrent.*;

/**
 * Created by HenDiao on 2017/8/21.
 */
public class FutureInAction2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10000);
                return "1 an all right";
            } catch (InterruptedException e) {

                return "Iam sad";
            }
        });
        System.out.println("Waiting");
     //   String value =future.get(10, TimeUnit.MICROSECONDS);
        int i=0;
        while(!future.isDone()){
            Thread.sleep(200);
            System.out.println("Waiting:"+i++);
        }
        System.out.println(future.get());

        executorService.shutdown();

        System.out.println(" executorService.shutdown");

    }
}
