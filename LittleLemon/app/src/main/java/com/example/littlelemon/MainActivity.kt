package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.lifecycle.lifecycleScope
import com.example.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase
    private lateinit var menuDao: MenuDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Room database and DAO
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "little_lemon_db"
        ).build()
        menuDao = database.menuDao()

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val menuNetwork = fetchMenu()
                menuDao.insertMenuItems(menuNetwork.menu.map { it.toEntity() })
            } catch (e: Exception) {
                android.util.Log.e("MainActivity", "Failed to fetch/insert menu", e)
            }
        }

        setContent {
            val navController = rememberNavController()
            LittleLemonTheme {
                Navigation(navController = navController, context = this, menuDao = menuDao)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
    }
}
