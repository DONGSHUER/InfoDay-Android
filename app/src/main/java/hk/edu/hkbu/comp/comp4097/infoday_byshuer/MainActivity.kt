package hk.edu.hkbu.comp.comp4097.infoday_byshuer

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.newsListFragment, R.id.eventFragment, R.id.bookmarkFragment, R.id.mapsFragment, R.id.infoFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController(R.id.nav_host_fragment).popBackStack()
        return true
    }

    //It is called when the app is created or resumed from another app
    override fun onResume() {
        super.onResume()
        //The SwitchPreferenceCompat will automatically store the preference value in a key-value dictionary.
        if (getSharedPreferences("${packageName}_preferences", 0).getBoolean("dark_mode", false)) {
            //switch to night mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            //switch to day mode
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

//    fun buttonClick(view : View) {
//        findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_home_to_infoFragment)
//    }
}