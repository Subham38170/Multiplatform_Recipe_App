package org.subham.recipeapp.data.local

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.subham.recipeapp.RecipeAppDb

class DbHelper(
    private val driverFactory: DatabaseFactory
) {
    private var db: RecipeAppDb? = null
    private val mutex = Mutex()


    suspend fun <Result: Any> withDatabse(
        block: suspend (RecipeAppDb) -> Result
    ) = mutex.withLock {
        db?.let {
            db = createDb(driverFactory)
        }
        return @return@withLock block(db!!)
    }

    private suspend fun createDb(
        driverFactory: DatabaseFactory
    ) = RecipeAppDb(driver = driverFactory.createDriver())
}
