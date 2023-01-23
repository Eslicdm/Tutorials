package com.eslirodrigues.tutorials.sqldelight_database.ui.state

import tutorialsqldelightdb.SqldelightUserEntity

data class SqlDelightUserState (
    val data: List<SqldelightUserEntity>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)