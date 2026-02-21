package com.devhjs.memo.di

import android.app.Application
import androidx.room.Room
import com.devhjs.memo.data.local.MemoDatabase
import com.devhjs.memo.data.local.dao.InformationDao
import com.devhjs.memo.data.local.dao.RecipeDao
import com.devhjs.memo.data.local.dao.ShoppingDao
import com.devhjs.memo.data.repository.InformationRepositoryImpl
import com.devhjs.memo.data.repository.RecipeRepositoryImpl
import com.devhjs.memo.data.repository.ShoppingRepositoryImpl
import com.devhjs.memo.data.util.ImageStorage
import com.devhjs.memo.domain.repository.InformationRepository
import com.devhjs.memo.domain.repository.RecipeRepository
import com.devhjs.memo.domain.repository.ShoppingRepository
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
    fun provideMemoDatabase(app: Application): MemoDatabase {
        return Room.databaseBuilder(
            app,
            MemoDatabase::class.java,
            MemoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingDao(db: MemoDatabase): ShoppingDao {
        return db.shoppingDao
    }

    @Provides
    @Singleton
    fun provideShoppingRepository(dao: ShoppingDao): ShoppingRepository {
        return ShoppingRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideInformationDao(db: MemoDatabase): InformationDao {
        return db.informationDao
    }

    @Provides
    @Singleton
    fun provideInformationRepository(dao: InformationDao): InformationRepository {
        return InformationRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideRecipeDao(db: MemoDatabase): RecipeDao {
        return db.recipeDao
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(dao: RecipeDao): RecipeRepository {
        return RecipeRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideImageStorage(app: Application):ImageStorage {
        return ImageStorage(app)
    }

}
