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
    @Query("SELECT * FROM stackTags WHERE (:stackId) LIMIT (:limit)")
    fun getAll(limit: Int? = 100, stackId: Int): Flow<List<StackTagModel>>

    @Query("SELECT * FROM stackTags WHERE (:stackId) and (:tagId)")
    fun get(stackId: Int, tagId: Int)

    @Query("DELETE FROM stackTags WHERE stackId = :stackId AND tagId = :tagId")
    fun delete(stackId: Int, tagId: Int)

    @Insert
    fun insertAll(vararg cards: StackTagModel)
}