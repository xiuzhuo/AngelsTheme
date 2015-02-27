package angel.zhuoxiu.theme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class ThemeActivity extends Activity implements ThemeInterface {
    String tag = this.getClass().getSimpleName();
    ThemeManager themeManager;
    ThemeWrapper[] themes = new ThemeWrapper[0];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
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
                setTheme(itemId);
                startActivityForResult(new Intent(this, this.getClass()), 100);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTheme(int resId) {
        super.setTheme(resId);
        if (getApplication() instanceof ThemeApplication) {
            ((ThemeApplication) getApplication()).setTheme(resId);
            ((ThemeApplication) getApplication()).setDefaultTheme(resId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        restart();
    }

    public void restart() {
        finish();
        startActivity(getIntent());
    }

    @Override
    public void saveStatus() {

    }

    @Override
    public void restoreStatus() {

    }

    @Override
    public String getPrefix() {
        return "Theme_Angel";
    }
}
