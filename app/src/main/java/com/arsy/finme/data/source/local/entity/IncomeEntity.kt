package com.arsy.finme.data.source.local.entity

data class IncomeEntity(
        var incomeId: Int,
        var accountId: Int,
        var nameIncome: String,
        var category: String,
        var dateIncome: String,
        var amountIncome: Int,
)