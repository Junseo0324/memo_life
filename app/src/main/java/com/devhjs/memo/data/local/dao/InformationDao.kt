package com.devhjs.memo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.devhjs.memo.data.local.entity.InformationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InformationDao {

    @Query("SELECT * FROM information_items ORDER BY id DESC")
    fun getInformationList(): Flow<List<InformationEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertInformationItem(item: InformationEntity)

    @Delete
    suspend fun deleteInformationItem(item: InformationEntity)

    @Update
    suspend fun updateInformationItem(item: InformationEntity)

    @Query("DELETE FROM information_items")
    suspend fun deleteAllInformationItems()
}