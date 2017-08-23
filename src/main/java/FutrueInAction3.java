import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by HenDiao on 2017/8/21.
 */
public class FutrueInAction3 {

    public static void main(String[] args) {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(3000);
                Objects.requireNonNull(null);
                return "i am ok";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "i am error";
            }
        });
        System.out.println("running");
        future.setComplateable(new ComplateAble<String>() {
            @Override
            public void complate(String s) {
                System.out.println(new Date());
                System.out.println(s);
            }
            @Override
            public void execution(Throwable course) {
                System.out.println("error");
                course.printStackTrace();
            }
        });
        System.out.println(future.get());
    }
    public static  <T> Future<T> invoke(Callable<T> callable){
        AtomicReference<T> result =new AtomicReference<>();
        AtomicBoolean finished=new AtomicBoolean(false);
        Future<T> future=new Future<T>() {
            private ComplateAble<T> complateAble;
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }

            @Override
            public void setComplateable(ComplateAble<T> complateable) {
                this.complateAble=complateable;
            }

            @Override
            public ComplateAble<T> getComplate() {
                return complateAble;
            }
        };
        Thread t =new Thread(()->{
            try {
                T value=callable.action();
                result.set(value);

                finished.set(true);
                if(future.getComplate()!=null){
                    future.getComplate().complate(value);
                }
            }catch (Throwable course){
                if(future.getComplate()!=null){
                    future.getComplate().execution(course);
                }
            }


        });
        t.start();

        return future;
    }

    private  interface  Future<T> {
        T get();
        boolean isDone();
        void setComplateable(ComplateAble<T> complateable);
        ComplateAble<T> getComplate();
    }
    public interface ComplateAble<T> {
        void complate(T t);
        void execution(Throwable course);
    }

    private interface  Callable<T> {
        T action();
    }
}
