package com.nutritiwallah.home.domain

import com.nutritionwallah.shared.Resource
import org.jetbrains.compose.resources.DrawableResource

enum class DrawerItem (
    val title: String,
    val icon: DrawableResource
){
    Profile(
        title = "Profile",
        icon = Resource.Icon.Person
    ),
    Blog(
        title = "Blog",
        icon = Resource.Icon.Book
    ),
    Locations(
        title = "Locations",
        icon = Resource.Icon.MapPin
    ),
    Contact(
        title = "Contact us",
        icon = Resource.Icon.Edit
    ),
    SignOut(
        title = "Sign Out",
        icon = Resource.Icon.SignOut
    ),
    Admin(
        title = "Admin Panel",
        icon = Resource.Icon.Unlock
    )
}