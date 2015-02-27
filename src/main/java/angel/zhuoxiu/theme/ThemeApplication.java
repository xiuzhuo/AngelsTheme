package angel.zhuoxiu.theme;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by zxui on 27/02/15.
 */
public class ThemeApplication extends Application {
    static final String DEFAULT_THEME_RES_ID = "default_theme_res_id";
    static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        setTheme(getDefaultTheme());
    }

    public static void setDefaultTheme(int resId) {
        getGlobalSharedPreferences().edit().putInt(DEFAULT_THEME_RES_ID, resId).commit();
    }

    public static int getDefaultTheme() {
        return getGlobalSharedPreferences().getInt(DEFAULT_THEME_RES_ID, application.getApplicationInfo().theme);
    }

    public static SharedPreferences getGlobalSharedPreferences() {
        return application.getSharedPreferences(application.getPackageName(), MODE_PRIVATE);
    }
}
