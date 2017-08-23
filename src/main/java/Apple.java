import java.util.Optional;

/**
 * Created by HenDiao on 2017/7/24.
 */
public class Apple {
    private String color;
    private Integer weight;
    private Optional<AppleAttr> appleAttr;
    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Optional<AppleAttr> getAppleAttr() {
        return appleAttr;
    }

    public void setAppleAttr(Optional<AppleAttr> appleAttr) {
        this.appleAttr = appleAttr;
    }

    @Override
    public String toString() {
        return  "{color："+this.color+",weight:"+this.weight+"}";
    }
}
