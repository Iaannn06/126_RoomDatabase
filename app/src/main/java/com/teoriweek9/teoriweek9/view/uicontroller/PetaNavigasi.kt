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
import com.example.myroomsiswa.view.DetailSiswaScreen
import com.example.myroomsiswa.view.route.DestinasiDetailSiswa
import com.example.myroomsiswa.view.route.DestinasiDetailSiswa.itemIdArg
import com.teoriweek9.teoriweek9.view.EntrySiswaScreen
import com.teoriweek9.teoriweek9.view.HomeScreen
import com.teoriweek9.teoriweek9.view.route.DestinasiEntry
import com.teoriweek9.teoriweek9.view.route.DestinasiHome

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    // Fungsi utama yang dipanggil dari MainActivity atau komponen tingkat tinggi lainnya
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // NavHost mendefinisikan grafik navigasi aplikasi
    NavHost(
        navController = navController,
        // Layar awal saat aplikasi dibuka
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        // Mendefinisikan rute untuk Layar Home
        composable(DestinasiHome.route) {
            HomeScreen(
                // Aksi untuk menavigasi ke layar Entry
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemUpdate = {
                    navController.navigate(route="${DestinasiDetailSiswa.route}/${it}")
                }
            )
        }
        composable(route=DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = {navController.popBackStack()})
        }

        composable(route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(name = itemIdArg){
                type = NavType.IntType
            })
        ){
            DetailSiswaScreen(
                navigateBack = {navController.navigateUp()}
            )
        }

        composable(route=DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(name=itemIdArg) {
                type = NavType.IntType
            })
        ){

        }

        // Mendefinisikan rute untuk Layar Entry Siswa
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                // Aksi untuk kembali ke layar sebelumnya (popBackStack)
                navigateBack = { navController.popBackStack() },
            )
        }
    }
}