package com.isaacag.rickandmorty_network.models

data class InfoRequestApiResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
