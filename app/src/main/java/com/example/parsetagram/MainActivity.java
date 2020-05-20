package com.example.parsetagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.parsetagram.fragments.ComposeFragment;
import com.example.parsetagram.fragments.PostsFragment;
import com.example.parsetagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;
    private Toolbar topNavigationView;
    public final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Log.i(TAG, "Camera button");
               Toast.makeText(MainActivity.this, "Clicked on Compose", Toast.LENGTH_SHORT).show();
               Fragment fragment = new ComposeFragment();
               fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            }
        });

        Button btnLogo = findViewById(R.id.btnLogo);
        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Logo button");
                Toast.makeText(MainActivity.this, "Clicked on Home", Toast.LENGTH_SHORT).show();
                Fragment fragment = new PostsFragment();
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            }
        });

//        topNavigationView = findViewById(R.id.top_navigation);
//        setSupportActionBar(topNavigationView);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.action_compose:
                        Toast.makeText(MainActivity.this, "Clicked on Compose", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                        break;
                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "Clicked on Home", Toast.LENGTH_SHORT).show();
                        fragment = new PostsFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(MainActivity.this, "Clicked on Profile", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                        break;
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "Clicked on Search", Toast.LENGTH_SHORT).show();
                        fragment = new PostsFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                        break;
                    case R.id.action_likes:
                        Toast.makeText(MainActivity.this, "Clicked on Likes", Toast.LENGTH_SHORT).show();
//                        fragment = new PostsFragment();
                        ParseUser.logOut();
                        ParseUser.logOutInBackground();
                        goLoginActivity();
                        break;
                    default:
                        break;
                }
//                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_top_navigation, menu);
//        return true;
//    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        // this helps the scenario where the user logins and they press the back button. If they do this
        // they will just go back to home instead of the login activity
        finish();
    }
}
