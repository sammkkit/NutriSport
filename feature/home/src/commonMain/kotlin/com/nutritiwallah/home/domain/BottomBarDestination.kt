package com.nutritiwallah.home.domain

import com.nutritionwallah.shared.Resource
import com.nutritionwallah.shared.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

enum class BottomBarDestination(
    val icon : DrawableResource,
    val title: String,
    val screen : Screen
) {
    ProductsOverview(
        icon = Resource.Icon.Home,
        title = "Home",
        screen = Screen.ProductsOverview
    ),
    Cart(
        icon = Resource.Icon.ShoppingCart,
        title = "Cart",
        screen = Screen.Cart
    ),
    Categories(
        icon = Resource.Icon.Categories,
        title = "Categories",
        screen = Screen.Categories
    )
}