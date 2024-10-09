package com.jagdon.jagdon_bottom_nav;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set the initial fragment to the To-Do List
        loadFragment(new FragmentToDoList()); // Load FragmentToDoList as the default

        // Set listener for bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Check which item was selected and set the corresponding fragment
            if (item.getItemId() == R.id.nav_calculator) { // Replace with your calculator menu ID
                selectedFragment = new FragmentCalculator();
            }
            else if (item.getItemId() == R.id.nav_todolist) { // ID for your To-Do List menu item
                selectedFragment = new FragmentToDoList();
            }
            else if (item.getItemId() == R.id.nav_profile) { // ID for your profile menu item
                selectedFragment = new FragmentProfile(); // Adjust if you have a FragmentProfile
            }

            return loadFragment(selectedFragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.framelayout, fragment);
            transaction.commit();
            return true;
        }
        return false;
    }
}
