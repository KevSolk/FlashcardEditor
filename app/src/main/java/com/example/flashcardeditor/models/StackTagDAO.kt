package com.example.flashcardeditor.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "stackTags")
data class StackTagModel(
        @ColumnInfo(name="stackId") val StackId: String,
        @ColumnInfo(name="tagId") val TagId: String,
){}

@Dao
interface StackTagDAO{
    @Query("SELECT * FROM stackTags")
    fun getAll(): Flow<List<StackTagModel>>

    @Query("DELETE FROM stackTags WHERE stackId = :stackId AND tagId = :tagId")
    fun delete(stackId: Int, tagId: Int)

    @Insert
    fun insertAll(vararg cards: StackTagModel)
}