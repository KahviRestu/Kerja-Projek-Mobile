package com.morzata.absensi.network

object  ApiURL {
    private val BASE_API = "http://192.168.137.207/Laravel/Kerja-Projek/public/api/"

    fun getLogin(email: String, password: String): String {
        return BASE_API+"login?email=${email}&password=${password}"
    }
}