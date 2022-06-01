package com.example.stocks.data.mapper

import com.example.stocks.data.local.CompanyListingsEntity
import com.example.stocks.domain.modal.CompanyListing

fun CompanyListingsEntity.toCompanyListing() = CompanyListing(
    name = name,
    symbol = symbol,
    exchange = exchange
)

fun CompanyListing.toCompanyListingEntity() = CompanyListingsEntity(
    name = name,
    symbol = symbol,
    exchange = exchange
)