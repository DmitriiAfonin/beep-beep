package data.remote.model

import domain.entity.Location

data class LocationDto(
    val latitude: Double? = null,
    val longitude: Double? = null,
)

fun LocationDto.toEntity(): Location {
    return Location(
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0
    )
}