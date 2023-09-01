package org.thechance.common.data.remote.mapper

import org.thechance.common.data.remote.model.RestaurantDto
import org.thechance.common.domain.entity.Time
import org.thechance.common.domain.entity.Restaurant

fun RestaurantDto.toEntity() = Restaurant(
    id = id,
    name = name,
    ownerUsername = ownerUsername,
    phoneNumber = phoneNumber,
    rating = rating,
    priceLevel = priceLevel,
    workingHours = workingHours.replace(" ", "").split("-").run{
        Pair(
            Time(
                get(0).split(":")[0].toInt(),
                get(0).split(":")[1].toInt()
            ),
            Time(
                get(1).split(":")[0].toInt(),
                get(1).split(":")[1].toInt()
            )
        )
    }


)


fun List<RestaurantDto>.toEntity() = map(RestaurantDto::toEntity)
