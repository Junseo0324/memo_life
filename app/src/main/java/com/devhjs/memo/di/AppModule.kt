package com.devhjs.memo.di

import android.app.Application
import androidx.room.Room
import com.devhjs.memo.data.local.ShoppingDao
import com.devhjs.memo.data.local.ShoppingDatabase
import com.devhjs.memo.data.repository.ShoppingRepositoryImpl
import com.devhjs.memo.domain.repository.ShoppingRepository
import com.devhjs.memo.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShoppingDatabase(app: Application): ShoppingDatabase {
        return Room.databaseBuilder(
            app,
            ShoppingDatabase::class.java,
            ShoppingDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingDao(db: ShoppingDatabase): ShoppingDao {
        return db.shoppingDao
    }

    @Provides
    @Singleton
    fun provideShoppingRepository(dao: ShoppingDao): ShoppingRepository {
        return ShoppingRepositoryImpl(dao)
    }


}
