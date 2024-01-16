package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.api.dto.staticdata.CryptoCoinDetailsResponseDto
import com.example.cryptoapp.domain.model.CryptoCoinDetailsModel

/**
 * extension function that maps [CryptoCoinDetailsResponseDto] to List of [CryptoCoinDetailsModel]
 * @return List of [CryptoCoinDetailsModel]
 * */
fun CryptoCoinDetailsResponseDto.toCryptoCoinDetailsModel() : List<CryptoCoinDetailsModel> {
    val cryptoDetailsList = crypto.values.toList().map { cryptoCoinDetail ->
        CryptoCoinDetailsModel(
            nameFull = cryptoCoinDetail.nameFull,
            iconUrl = cryptoCoinDetail.iconUrl,
            symbol = cryptoCoinDetail.symbol,
        )
    }

    return cryptoDetailsList
}