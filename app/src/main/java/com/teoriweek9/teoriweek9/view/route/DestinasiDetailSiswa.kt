package com.example.teoriweek9.view.route

import com.teoriweek9.teoriweek9.R
import com.teoriweek9.teoriweek9.view.route.DestinasiNavigasi

object DestinasiDetailSiswa : DestinasiNavigasi {
    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}


