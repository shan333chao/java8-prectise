import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CompletableFutureInAction3 {
    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(2, r->{
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            return thread;
        });
//        CompletableFuture.supplyAsync(CompletableFutureInAction::get, executor)
//                .thenApply(CompletableFutureInAction3:: mutiply).whenComplete((v,t)->{
//            Optional.ofNullable(v).ifPresent(System.out::println);
//        });
        List<Integer> productIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Double> collect = productIds.stream().map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executor))
                .map(f -> f.thenApply(CompletableFutureInAction3::mutiply))
                .map(CompletableFuture::join).collect(toList());
        System.out.println(collect);
    }
      static double mutiply(double d){

        return d*10;
    }


    private  static double queryProduction(int i){
        return CompletableFutureInAction.get()+i;

    }

}
