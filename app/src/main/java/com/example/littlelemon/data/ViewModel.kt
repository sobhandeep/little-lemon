package com.example.littlelemon.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {
    private val database: AppDataBase =
        Room.databaseBuilder(application, AppDataBase::class.java, "database").fallbackToDestructiveMigration()
            .build()

    fun getMenuItems(): LiveData<List<MenuItemRoom>>{
        return database.menuItemDao().getAll()
    }

    fun fetchMenuData(){
        viewModelScope.launch(Dispatchers.IO) {
            if(database.menuItemDao().isEmpty()){
                saveMenuToDataBase(database, fetchMenu("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"))
            }
        }
    }
}

suspend fun fetchMenu(url: String): List<MenuItemNetwork>{
    val client = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text", "plain"))
        }
    }
    val response: MenuNetwork = client.get(url).body()
    return response.items
}

fun saveMenuToDataBase(database: AppDataBase, menuItemNetwork: List<MenuItemNetwork>){
    val menuItemRoom = menuItemNetwork.map { it.toMenuItemRoom() }
    database.menuItemDao().insertAll(*menuItemRoom.toTypedArray())
}