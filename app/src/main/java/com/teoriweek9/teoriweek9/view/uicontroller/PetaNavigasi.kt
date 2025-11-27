package com.example.myroomsatu.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myroomsatu.view.EntrySiswaScreen
import com.example.myroomsatu.view.HomeScreen
import com.example.myroomsatu.view.route.DestinasiHome
import com.example.myroomsatu.view.route.DestinasiEntry
import com.teoriweek9.teoriweek9.view.EntrySiswaScreen
import com.teoriweek9.teoriweek9.view.HomeScreen
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
            )
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