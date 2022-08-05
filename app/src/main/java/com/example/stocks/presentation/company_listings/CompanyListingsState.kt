package com.example.stocks.presentation.company_listings

import com.example.stocks.domain.modal.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
