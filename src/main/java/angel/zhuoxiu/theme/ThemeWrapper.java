package angel.zhuoxiu.theme;

/**
 * Created by zxui on 27/02/15.
 */
public class ThemeWrapper {
    public String name;
    public int resId;

    public ThemeWrapper(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }
    @Override
    public String toString() {
        return "ThemeWrapper{" +
                "name='" + name + '\'' +
                ", resId=" + resId +
                '}';
    }
}
