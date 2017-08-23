import java.util.Optional;

/**
 * Created by HenDiao on 2017/8/19.
 */
public class Children {
    private Optional<Apple > apple;

    public Optional<Apple> getApple() {
        return apple;
    }

    public void setApple(Optional<Apple> apple) {
        this.apple = apple;
    }

    public Children(Optional<Apple> apple) {
        this.apple = apple;
    }

}
