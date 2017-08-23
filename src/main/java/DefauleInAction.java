import java.util.Objects;

/**
 * Created by HenDiao on 2017/8/21.
 */
public class DefauleInAction {

    private  void confuse(Object o){
        System.out.print("Object");
    }

    private  void confuse(int [] i){
        System.out.print("int [] i");
    }

    public static void main(String[] args) {
        DefauleInAction action=new DefauleInAction();
        action.confuse(null);

        C c=new C();
        c.hello();
    }

    private  interface  A{

        default void  hello(){
            System.out.println("a.hello");
        }
    }

    private  interface B extends A{
        @Override
        default void  hello(){
            System.out.println("b.hello");
        }
    }
    private static   class C implements B, A{
        @Override
    public  void hello(){
        System.out.println("c.hello");
    }
    }
}
