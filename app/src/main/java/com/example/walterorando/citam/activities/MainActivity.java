package com.example.walterorando.citam.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.walterorando.citam.Fragments.Home_Fragment;
import com.example.walterorando.citam.Fragments.Settings_Fragment;

import com.example.walterorando.citam.R;


public class MainActivity extends AppCompatActivity {

    private NavigationView myNavigationview;
    private DrawerLayout mydrawer;
    private View myNavView;
    private ImageView mynavHeaderbg, myprof;
    private TextView txtName;
    private Toolbar mytoolbar;

    private FloatingActionButton fab;
    public String loaderBg;

    private static int navIndex = 0;

    private static final String TAG_HOME = "home";
    private static final String TAG_BIBLE = "bible";
    private static final String TAG_SERMON = "sermon";
    private static final String TAG_GALLERY = "gallery";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);

        mHandler = new Handler();

        mydrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      /*  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mydrawer, mytoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed);
        mydrawer.setDrawerListener(toggle);
        toggle.syncState();*/
        // Navigation view header
        myNavigationview = (NavigationView) findViewById(R.id.nav_view);

        myNavView = myNavigationview.getHeaderView(0);
        txtName = (TextView) myNavView.findViewById(R.id.tv_prof_name);
        mynavHeaderbg = (ImageView) myNavView.findViewById(R.id.img_nav_bg);
        myprof = (ImageView) myNavView.findViewById(R.id.img_nav_prof);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();


        if (savedInstanceState == null) {
            navIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        txtName.setText("CITAM CHURCH KISUMU");

        // loading header background image
        Glide.with(this).load(R.drawable.background)
                .crossFade()
                .error(R.drawable.cover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mynavHeaderbg);

        // Loading profile image
        Glide.with(getApplicationContext())
                .load(loaderBg)
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.logo1)
                .error(R.drawable.logo1)
                .into(new BitmapImageViewTarget(myprof) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        myprof.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            mydrawer.closeDrawers();


            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


        //Closing drawer on item click
        mydrawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navIndex) {
            case 0:
                // home
                Home_Fragment homeFragment = new Home_Fragment();
                return homeFragment;
            case 1:

            case 2:

                mydrawer.closeDrawer(GravityCompat.START, true);
            startActivity(new Intent(MainActivity.this, Sermon.class));
            break;
            case 3:


            case 4:

                Settings_Fragment settingsFragment = new Settings_Fragment();
                return settingsFragment;
            default:
                return new Home_Fragment();
        }
        return new Home_Fragment();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navIndex]);
    }

    private void selectNavMenu() {
        myNavigationview.getMenu().getItem(navIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        myNavigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_bible:
                        navIndex = 1;
                        CURRENT_TAG = TAG_BIBLE;
                        break;
                    case R.id.nav_sermon:
                        navIndex = 2;
                        CURRENT_TAG = TAG_SERMON;
                        break;
                    case R.id.nav_gallery:
                        navIndex = 3;
                        CURRENT_TAG = TAG_GALLERY;
                        break;
                    case R.id.nav_settings:
                        navIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_citam:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, About_Citam_Church.class));

                    default:
                        navIndex = 0;


                        return true;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
               // menuItem.setChecked(true);

                loadHomeFragment();

                mydrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mydrawer, mytoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        mydrawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (mydrawer.isDrawerOpen(GravityCompat.START)) {
            mydrawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navIndex != 0) {
                navIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    // show or hide the fab
    private void toggleFab() {
        if (navIndex == 0)
            fab.show();
        else
            fab.hide();
    }


}
