package com.cristianalmendro.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.cristianalmendro.littlelemon.ui.theme.Gray
import com.cristianalmendro.littlelemon.ui.theme.Green
import com.cristianalmendro.littlelemon.ui.theme.Yellow

@Composable
fun Home(navController: NavHostController, context: Context, dao: MenuItemDao) {

    var searchText by remember {
        mutableStateOf("")
    }
    var selectedCategory by remember { mutableStateOf("") }

    fun navigateToProfile() {
        navController.navigate(Profile.navigationRoute)
    }

    val menuItems by produceState<List<MenuItemRoom>>(initialValue = emptyList()) {
        value = dao.getAll()
    }

    val categories: Set<String> = menuItems.map { it.category }.toSet()

    val filteredMenuItems = menuItems
        .filter { item ->
            item.title.contains(searchText, ignoreCase = true)
        }
        .filter { item ->
            selectedCategory.isBlank() || item.category == selectedCategory
        }


    Column(modifier = Modifier.fillMaxSize()) {
        // HEADER AREA
        Box(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "App logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(40.dp)
                    .width(140.dp)
            )

            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .height(40.dp)
                    .width(40.dp)
                    .clickable {
                        navigateToProfile()
                    }
            )
        }
        // HERO AREA
        Column(
            modifier = Modifier
                .background(Green)
                .padding(all = 20.dp)
        ) {
            Text("Little Lemon", color = Yellow, fontSize = 32.sp)

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text("Chicago", color = Color.White, fontSize = 25.sp)
                    Text(
                        "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        color = Color.White, fontSize = 12.sp
                    )
                }
                Image(
                    painter = painterResource(R.drawable.heroimage),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .size(150.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            TextField(
                searchText,
                onValueChange = { searchText = it },
                placeholder = {
                    Text(
                        text = "Enter Search Phrase",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
        }

        // MENU BREAKDOWN

        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Button(
                    onClick = { selectedCategory = "" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Gray,
                        contentColor = Green
                    )
                ) {
                    Text("All")
                }
            }

            items(categories.toList()) { category ->
                val isSelected = selectedCategory == category
                Button(
                    onClick = { selectedCategory = category },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Gray,
                        contentColor = Green
                    )
                ) {
                    Text(
                        text = category.replaceFirstChar { it.uppercase() }
                    )
                }
            }
        }
        // FOOD MENU LIST

        MenuItemList(filteredMenuItems)
    }
}


@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    println(menuItem.image)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(menuItem.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Column {
                Text(menuItem.description, maxLines = 2, fontSize = 14.sp)
                Text("%.2f".format(menuItem.price), fontSize = 16.sp)
            }
        }
        AsyncImage(
            model = menuItem.image,
            contentDescription = null,
            modifier = Modifier
                .weight(0.3f)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop,
        )
    }
    HorizontalDivider()
}

@Composable
fun MenuItemList(menuItems: List<MenuItemRoom>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(menuItems) { menuItem ->
            MenuItem(menuItem)
        }
    }
}