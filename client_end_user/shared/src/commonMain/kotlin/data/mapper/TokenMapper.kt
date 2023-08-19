package data.mapper

import data.remote.dto.TokensResponse
import domain.entity.Tokens

fun TokensResponse.toEntity() = Tokens(accessToken, refreshToken)