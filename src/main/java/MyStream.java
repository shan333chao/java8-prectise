import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by HenDiao on 2017/7/26.
 */
public class MyStream {


    private static Stream<String> createStreamCollection(){
        List<String> list= Arrays.asList("mon","tues","wed","thur");
        return list.stream();
    }

    public static void main(String[] args) {
        Stream<String> words=Stream.of("Hello World");
        Stream<String[]> streams = words.map(x -> x.split(""));
        Stream<String> stringStream = streams.flatMap(Arrays::stream);
        stringStream.forEach(System.out::println);
        boolean b = createStreamFromArrays().anyMatch(o -> o == "sun");
        
        //  createStreamFromFile();//.forEach(System.out::println);
        System.out.println(b);
    }

    private  static Stream<String> createStreamFromValues(){
        return Stream.of("mon","tues","wed","thur");
    }

    private  static Stream<String> createStreamFromArrays(){
        return Arrays.stream(new String[]{"mon","tues","wed","thur"});
    }



    private  static Stream<String> createStreamFromFile(){
       Path p= Paths.get("D:\\MyCode\\javacode\\java8\\build.gradle");
       try( Stream<String> lines= Files.lines(p)) {
            lines.filter(xp->xp.contains("spring")).collect(Collectors.toList()).forEach(System.out::println);
          return lines;
       }catch (IOException e){
           throw  new RuntimeException(e);
       }


    }
}
