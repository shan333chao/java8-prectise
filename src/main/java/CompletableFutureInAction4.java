import java.util.concurrent.CompletableFuture;

public class CompletableFutureInAction4 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->1)
                .thenApply(i->Integer.sum(i,10))
                .whenComplete ((v,t)->System.out.println(v));
        CompletableFuture.supplyAsync(()->2)
                .handle((v,t)->Integer.sum(v,100))
                .whenComplete ((v,t)->System.out.println(v))
                .thenRun(System.out::println);
        CompletableFuture.supplyAsync(()->3)
                .handle((v,t)->Integer.sum(v,100))
                .thenAccept(System.out::println);
        CompletableFuture.supplyAsync(()->4)
               .thenCompose(i->CompletableFuture.supplyAsync(()->10*i))
                .thenAccept(System.out::println);
        CompletableFuture.supplyAsync(()->5)
                .thenCombine(CompletableFuture.supplyAsync(()->4),(r1,r2)->r1+r2)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(()->5)
                .thenAcceptBoth(CompletableFuture.supplyAsync(()->4),(r1,r2)->{
                    System.out.println(r1);
                    System.out.println(r2);
                } );
        Thread.sleep(1000L);

    }
}
