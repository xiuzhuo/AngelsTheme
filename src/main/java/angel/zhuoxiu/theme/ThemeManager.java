package angel.zhuoxiu.theme;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxui on 26/02/15.
 */
public class ThemeManager {
    String tag = getClass().getSimpleName();

    Context context;
    String prefix;
    static final Map<String, ThemeManager> managerMap = new HashMap<String, ThemeManager>();

    public static ThemeManager getInstance(Context context, String prefix) {
        String packageName = context.getPackageName();
        if (managerMap.get(context.getPackageName()) == null) {
            managerMap.put(context.getPackageName(), new ThemeManager(context, prefix));
        } else {
            managerMap.get(context.getPackageName()).context = context;
        }
        return managerMap.get(context.getPackageName());
    }

    private ThemeManager(Context context, String prefix) {
        this.context = context;
        this.prefix = prefix;

    }

    public ThemeWrapper[] getThemes() {
        ThemeWrapper[] themes = new ThemeWrapper[0];
        Class r = null;
        try {
            r = Class.forName(context.getPackageName() + ".R");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class styleClass = null;
        for (Class c : r.getDeclaredClasses()) {
            if (c.getSimpleName().equals("style")) {
                Log.d(tag, "classes = " + c.getName());
                styleClass = c;
                break;
            }
        }
        Field[] styleFields = styleClass.getDeclaredFields();
        themes = new ThemeWrapper[styleFields.length];
        int index = -1;
        for (Field field : styleFields) {
            try {
                if (field.getName().startsWith(prefix)) {
                    index++;
                    themes[index] = new ThemeWrapper(field.getName(), field.getInt(null));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        ThemeWrapper[] tempThemes = themes;
        themes = new ThemeWrapper[index + 1];
        System.arraycopy(tempThemes, 0, themes, 0, index + 1);
        return themes;
    }


}
