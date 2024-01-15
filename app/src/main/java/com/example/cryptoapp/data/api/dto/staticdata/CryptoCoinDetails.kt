package com.example.cryptoapp.data.api.dto.staticdata

import com.google.gson.annotations.SerializedName


data class CryptoCoinDetailsResponseDto(
    val success: Boolean,
    val crypto: Map<String, CryptoCoinDetails>
)

data class CryptoCoinDetails(
    @SerializedName("icon_url")
    val iconUrl: String,
    val symbol: String,
    @SerializedName("name_full")
    val nameFull: String,
    val name: String,
    @SerializedName("max_supply")
    val maSupply: String
)