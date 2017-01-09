package thinkslate.thinkslateapplication;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {

    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mBottomBar = BottomBar.attach(this, savedInstanceState);

        mBottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch(menuItemId) {
                    case R.id.menuHome:
                        HomeActivity homeActivity = new HomeActivity();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, homeActivity).commit();
                        break;
                    case R.id.menuMap:
                        MapActivity mapActivity = new MapActivity();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, mapActivity).commit();
                        break;
                    case R.id.menuUser:
                        UserActivity userActivity = new UserActivity();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame, userActivity).commit();
                        break;
                    case R.id.menuSettings:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {}
        });
    }
}
