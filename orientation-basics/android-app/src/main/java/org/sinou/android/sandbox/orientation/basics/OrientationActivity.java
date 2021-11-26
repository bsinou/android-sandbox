package org.sinou.android.sandbox.orientation.basics;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class OrientationActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);


        navController = Navigation.findNavController(this, R.id.main_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.more_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_global_button_fragment:
                System.out.println("Yep");
                return true;
            case R.id.action_favorite:
                navController.navigate(R.id.button_fragment);
                System.out.println("action_favorite");
                return true;
            default:
                // Enable direct reference to a fragment ID from the XML menu definition.
                return NavigationUI.onNavDestinationSelected(item, navController)
                        || super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method must be implemented in activity and / or fragments
     * that need to persist data in order to optimise the re-creation of the activity.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
