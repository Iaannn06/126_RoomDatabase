package com.example.myroomsatu.view.uicontroller

import android.R.attr.type
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.teoriweek9.view.route.DestinasiDetailSiswa
import com.example.teoriweek9.view.route.DestinasiDetailSiswa.itemIdArg
import com.teoriweek9.teoriweek9.view.DetailSiswaScreen
import com.teoriweek9.teoriweek9.view.EditSiswaScreen
import com.teoriweek9.teoriweek9.view.EntrySiswaScreen
import com.teoriweek9.teoriweek9.view.HomeScreen
import com.teoriweek9.teoriweek9.view.route.DestinasiEditSiswa
import com.teoriweek9.teoriweek9.view.route.DestinasiEntry
import com.teoriweek9.teoriweek9.view.route.DestinasiHome

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController=navController, startDestination = DestinasiHome.route, modifier = Modifier)
    {
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
                //edit 1 : tambahkan parameter navigateToItemUpdate
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetailSiswa.route}/$it")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntrySiswaScreen(navigateBack = { navController.popBackStack()})
        }
        //edit 2 : tambahkan 2 composable route
        composable(route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailSiswa.itemIdArg) {
                type = NavType.IntType
            })
        ){
            DetailSiswaScreen(
                navigateToEditItem = {navController.navigate("${DestinasiEditSiswa.route}/$it")},
                navigateBack = { navController.navigateUp() })
        }
        composable(route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditSiswa.itemIdArg){
                type = NavType.IntType
            })){
            EditSiswaScreen(
                navigateBack = {navController.popBackStack()},
                onNavigateUp = { navController.navigateUp() })
        }
    }
}