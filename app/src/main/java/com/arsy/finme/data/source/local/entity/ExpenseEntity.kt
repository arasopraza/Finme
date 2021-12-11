package com.arsy.finme.data.source.local.entity

data class ExpenseEntity(
        var expenseId: Int,
        var accountId: Int,
        var nameExpense: String,
        var category: String,
        var dateExpense: String,
        var amountExpense: Int,
)
