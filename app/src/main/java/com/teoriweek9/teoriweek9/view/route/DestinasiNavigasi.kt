package com.teoriweek9.teoriweek9.view.route



import androidx.annotation.StringRes



interface DestinasiNavigasi {

    val route: String
    @get:StringRes
    val titleRes: Int
}