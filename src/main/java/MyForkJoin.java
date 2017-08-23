import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

/**
 * Created by HenDiao on 2017/8/20.
 */
public class MyForkJoin extends RecursiveTask<Apple> {
    private final int start;
    private final int end;
    private List<Apple> data;
    private  final int LIMIT=3;

    public MyForkJoin(int start, int end, List<Apple> data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Apple compute() {
        if ((end-start)<=LIMIT){
            Apple app=new Apple();
            for (int i=start;i<end;i++){
                Apple a = data.get(i);
                app.setWeight(Optional.ofNullable(app).map(Apple::getWeight).orElse(0) +a.getWeight());
                app.setColor(Optional.ofNullable(app).map(Apple::getColor).orElse("").concat("-"+a.getColor()));
            }
            return app;
        }
        int mid=(start+end) /2;
        MyForkJoin left=new MyForkJoin(start,mid,data);
        MyForkJoin right=new MyForkJoin(mid,end,data);
        left.fork();
        Apple res=right.compute();
        Apple leftres=   left.join();
        res.setColor(res.getColor().concat(leftres.getColor()));
        res.setWeight(res.getWeight()+leftres.getWeight());
        return res;
    }
}
