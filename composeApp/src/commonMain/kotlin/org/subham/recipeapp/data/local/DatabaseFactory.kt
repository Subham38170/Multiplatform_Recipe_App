package org.subham.recipeapp.data.local

import app.cash.sqldelight.db.SqlDriver

const val DB_FILE_NAME = "recipeapp.db"

expect class DatabaseFactory {
    suspend fun createDriver(): SqlDriver
}