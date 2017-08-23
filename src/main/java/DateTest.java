import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateTest {
    private  final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");

    public static void main(String[] args) {
//        for (int i=0 ;i<30;i++){
//            new Thread(()->{
//                Date parse=null;
//                for (int x=0;x<100;x++){
//                    try {
//                        parse  = sdf.parse("2017-03-22");
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(parse);
//                }
//            }).start();
//        }
  //      testLocalDate();
   //     testlocalTime();
        testlocalDateAndTime();
    }

    private static void testlocalDateAndTime() {
        LocalDate ld=LocalDate.now();
        LocalTime lt=LocalTime.now();
        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter yyyymmddhhmmss = DateTimeFormatter.ofPattern("yyyymmddhhmmss");

        System.out.println(ld);
        System.out.println(lt);
        System.out.println(ldt);
        System.out.println(   ldt.format(yyyymmddhhmmss));
    }

    private static void testlocalTime() {
        LocalTime localtime=LocalTime.now();
        System.out.println(localtime);
    }

    private static void testLocalDate() {
        LocalDate localeDate=LocalDate.of(2017,8,11);
        System.out.println(localeDate.getYear());
        System.out.println(localeDate.getMonth());
        System.out.println(localeDate.getMonthValue());
        System.out.println(localeDate.getDayOfYear());
        System.out.println(localeDate.getDayOfWeek());
        System.out.println(    LocalDate.now());;
        localeDate.get(ChronoField.DAY_OF_MONTH);
    }

}
