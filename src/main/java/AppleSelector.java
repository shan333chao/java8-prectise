import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by HenDiao on 2017/7/24.
 */
public class AppleSelector {
@FunctionalInterface
    public interface  AppleFilter{
        boolean filter(Apple apple);
    }

    public  static class  AppleFilterImpl implements  AppleFilter{

        @Override
        public boolean filter(Apple apple) {

            return  "green".equals(apple.getColor());
        }
    }
    public static List<Apple> findGreenApples(List<Apple> apples,AppleFilter filter){
        List<Apple> list=new ArrayList<>();
        for (Apple item:apples){

                list.add(item);


        }
        return list;
    }

    public static void main(String[] args) {

        List<Apple> list= Arrays.asList(new Apple("green",4),
                new Apple("green",4),
                new Apple("green",43),
                new Apple("green",44),
                new Apple("green",45));
        List<Apple> greenApples=findGreenApples(list,new AppleFilterImpl());
        List<Apple> yellowApples=findGreenApples(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                  return  "yellow".equals(apple.getColor());
            }
        });
        String strs="33333";

        List<Apple> yellowApples2=findGreenApples(list, item->{
           return    "yellow".equals(item.getColor());
        });
        List<String> lists= Stream.of("12345678").skip(3).collect(Collectors.toList());
        lists.forEach((a)->{System.out.print(a);});

        List<String> colors= list.parallelStream().filter(item->item.getWeight()>4).map(Apple::getColor).collect(Collectors.toList());

        colors.stream().forEach((a)->System.out.print(String.valueOf(a)) );

        System.out.println(greenApples.stream().skip(2).collect(Collectors.toList()).toString());

   //     Thread.currentThread().join();
    }
}
