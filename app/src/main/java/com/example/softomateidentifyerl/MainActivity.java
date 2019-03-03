package com.example.softomateidentifyerl;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public final String TAG = getClass().getSimpleName();
    int menuItemSelected=0;
    public static final String SAVE_STATE_MENU="state_menu";
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
        if(savedInstanceState != null){
            menuItemSelected = savedInstanceState.getInt(SAVE_STATE_MENU,0);
        }
        else
            selectDrawerItem(navigationView.getMenu().getItem(menuItemSelected));
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(SAVE_STATE_MENU, menuItemSelected);
        Log.d(TAG, "onSaveInstanceState");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public void selectDrawerItem(MenuItem item) {
		Fragment fragment = null;
        int id = item.getItemId();
		switch(id)
		{
			case R.id.nav_identifyer:
				fragment = new IdentifyerFragment();
                menuItemSelected=0;
			break;
			case R.id.nav_history:
				fragment = new HistoryFragment();
                menuItemSelected=1;
			break;
		}
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
		item.setChecked(true);
		setTitle(item.getTitle());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Log.d(TAG, "onNavigationItemSelected");
    }
}
