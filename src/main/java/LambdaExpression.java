import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by HenDiao on 2017/7/24.
 */
public class LambdaExpression {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green", 4),
                new Apple("red", 888),
                new Apple("green", 41),
                new Apple("red", 8882),
                new Apple("green", 42),
                new Apple("yellow", 8883));
        list.get(1).setAppleAttr(Optional.of(new AppleAttr(null)));
        Optional.ofNullable(getAppleAttr(new Children(Optional.of(list.get(1))))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().collect(Collectors.groupingBy(Apple::getColor))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().collect(Collectors.averagingDouble(Apple::getWeight))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().collect(Collectors.groupingByConcurrent(Apple::getColor, Collectors.averagingDouble(Apple::getWeight)))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().collect(Collectors.groupingByConcurrent(Apple::getColor, Collectors.counting()))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().collect(Collectors.groupingByConcurrent(Apple::getColor, ConcurrentSkipListMap::new, Collectors.averagingDouble(Apple::getWeight)))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().map(Apple::getColor).collect(Collectors.joining(" ", "color[", "]"))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().collect(Collectors.mapping(Apple::getColor, Collectors.joining("-")))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Apple::getWeight)))).ifPresent(System.out::println);
        Optional.ofNullable(list.stream().collect(Collectors.groupingByConcurrent(Apple::getColor, Collectors.maxBy(Comparator.comparingInt(Apple::getWeight))))).ifPresent(System.out::println);

        Optional.ofNullable(list.stream().collect(Collectors.summarizingInt(Apple::getWeight))).ifPresent(System.out::println);
        Collector<Apple, List<Apple>, List<Apple>> collector = new MyCollector<>();

        Optional.of(list.parallelStream().filter(p -> p.getWeight() > 42).collect(collector)).ifPresent(System.out::println);
        Optional.of(System.getenv()).ifPresent(System.out::println);
        Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);
        MyForkJoin trsk = new MyForkJoin(0, list.size(), list);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Apple result = forkJoinPool.invoke(trsk);
        Optional.of(result).ifPresent(System.out::println);

        AccumulatorRecursiceAction action=new AccumulatorRecursiceAction(0, list.size(), list);
        forkJoinPool.invoke(action);
        Optional.of(AccumulatorRecursiceAction.AccumulatorHelper.getResult()).ifPresent(System.out::println);


//        Consumer<List<Apple>> consumer = (lists) -> {
//            lists.stream().forEach(System.out::println);
//
//        };
//        Function<String, Integer> f = s -> Integer.parseInt(s);
//        Integer res = f.apply("4432432");
//        System.out.println(res);
//        Consumer<Integer> tConsumer = System.out::println;
//
//        BiFunction<String, Integer, Integer> stringIntegerIntegerBiFunction = Integer::getInteger;
//        Integer ss = stringIntegerIntegerBiFunction.apply("111", 222);
//        tConsumer.accept(ss);
//
//
//        //  useConsumer(consumer,list);
//        useConsumer(System.out::println, "555");
//        List<Apple> list2 = list.stream().skip(1).filter((a) -> "red".equals(a.getColor()) && a.getWeight() > 4).collect(Collectors.toList());
//        useConsumer(consumer, list2);
    }


    private static Integer getAppleAttr(Children cld) {
        Integer integer = Optional.ofNullable(cld).flatMap(Children::getApple)
                .flatMap(Apple::getAppleAttr)
                .map(AppleAttr::getPrice)
                .orElse(666);
        return integer;
    }

    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }
}
