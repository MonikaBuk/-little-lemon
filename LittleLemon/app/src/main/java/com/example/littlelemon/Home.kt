package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.Typography
import com.example.littlelemon.ui.theme.green
import com.example.littlelemon.ui.theme.yellow
import com.example.littlelemon.ui.theme.h1
import com.example.littlelemon.ui.theme.h2
import com.example.littlelemon.ui.theme.body1 as b1

@Composable
fun HomeScreen(navController: NavHostController, menuDao: MenuDao) {
    var searchPhrase by remember {
        mutableStateOf("")
    }

    // getMenuItems() returns a Flow<List<MenuItemEntity>>, collected as Compose state.
    val menuItems by menuDao.getMenuItems().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // --- Top content area (Logo, Banner, and Fields grouped) ---
        Column(modifier = Modifier.fillMaxWidth()) {

            // Logo row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon logo"
                )
            }

            // Green banner
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(green)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "Little Lemon",
                    style = Typography.h1,
                    color = yellow
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Chicago",
                            style = Typography.h2,
                            color = Color.White
                        )
                        Text(
                            text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                            style = Typography.b1,
                            color = Color.White,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.upperpanelimage),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .size(130.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = searchPhrase,
                    onValueChange = { searchPhrase = it },
                    placeholder = { Text("Enter search phrase") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            MenuItems(
                menuItems = menuItems,
                modifier = Modifier.weight(1f)
            )

            Button(onClick = { navController.navigate(Profile.route) }) {
                Text("Go to Profile")
            }
        }
    }
}
