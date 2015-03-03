package angel.zhuoxiu.theme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public abstract class ThemeActivity extends Activity implements ThemeInterface {

    static final String DEFAULT_THEME_RES_ID = "default_theme_res_id";
    String tag = this.getClass().getSimpleName();
    ThemeManager themeManager;
    ThemeWrapper[] themes = new ThemeWrapper[0];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getDefaultTheme());
        themes = ThemeManager.getInstance(this, getPrefix()).getThemes();
        for (ThemeWrapper theme : themes) {
            Log.i(tag, theme.toString());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        for (ThemeWrapper theme : themes) {
            menu.add(0, theme.resId, 0, theme.name);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int itemId = item.getItemId();

        switch (itemId) {
            default:
                if (itemId != getDefaultTheme()) {
                    setDefaultTheme(itemId);
                    restartForThemeChange();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        restartForThemeChange();
    }

    public abstract void restartForThemeChange();

    @Override
    public void saveStatus() {

    }

    @Override
    public void restoreStatus() {

    }

    @Override
    public abstract String getPrefix();

    boolean setDefaultTheme(int resId) {
        return getGlobalSharedPreferences().edit().putInt(DEFAULT_THEME_RES_ID, resId).commit();
    }

    int getDefaultTheme() {
        return getGlobalSharedPreferences().getInt(DEFAULT_THEME_RES_ID, getApplicationInfo().theme);
    }

    SharedPreferences getGlobalSharedPreferences() {
        return getSharedPreferences(getPackageName(), MODE_PRIVATE);
    }
}
