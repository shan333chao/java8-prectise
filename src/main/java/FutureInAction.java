
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by HenDiao on 2017/8/21.
 */
public class FutureInAction {
    public static void main(String[] args) throws InterruptedException {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "Iam finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }

        });
        System.out.println(future.get());
        while(!future.isDone()){
            Thread.sleep(10);
            System.out.println("1");
        }
        Optional.ofNullable(future.get()).ifPresent(System.out::println);
    }
    public static  <T> Future<T> invoke(Callable<T> callable){
        AtomicReference<T> result =new AtomicReference<>();
        AtomicBoolean finished=new AtomicBoolean(false);
      Thread t =new Thread(()->{
         T value=callable.action();
         result.set(value);
         finished.set(true);

      });
      t.start();
      Future<T> future=new Future<T>() {

          @Override
          public T get() {
              return result.get();
          }

          @Override
          public boolean isDone() {
              return finished.get();
          }
      };
      return future;
    }

    private  interface  Future<T> {
        T get();
       boolean isDone();

    }
    private interface  Callable<T> {
        T action();
    }
}
