import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HenDiao on 2017/8/21.
 */
public class AccumulatorRecursiceAction extends RecursiveAction {
    private final int start;
    private final int end;
    private List<Apple> data;
    private  final int LIMIT=3;

    public AccumulatorRecursiceAction(int start, int end, List<Apple> data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        if ((end-start)<=LIMIT){
            for (int i=start;i<end;i++){
                Apple a = data.get(i);
                AccumulatorHelper.accumulate(Optional.ofNullable(a).map(Apple::getWeight).orElse(0));
//                app.setWeight(Optional.ofNullable(app).map(Apple::getWeight).orElse(0) +a.getWeight());
//                app.setColor(Optional.ofNullable(app).map(Apple::getColor).orElse("").concat("-"+a.getColor()));
            }

        }else {
            int mid=(start+end) /2;
            AccumulatorRecursiceAction left=new AccumulatorRecursiceAction(start,mid,data);
            AccumulatorRecursiceAction right=new AccumulatorRecursiceAction(mid,end,data);
            left.fork();
            right.fork();
            left.join();
            right.join();

        }

    }

    static  class  AccumulatorHelper{
        private  static  final AtomicInteger result=new AtomicInteger(0);

        static  void accumulate(int value){
            result.getAndAdd(value);
        }
        public  static  int getResult(){
            return result.get();
        }
        static void reset(){
            result.set(0);
        }
    }
}
