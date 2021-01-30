package com.example.flashcardeditor.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "cardTags")
data class CardTagModel(
        @ColumnInfo(name="stackId") val StackId: String,
        @ColumnInfo(name="cardId") val CardId: String,
){}

@Dao
interface CardTagDAO{

    @Query("SELECT * FROM cardTags WHERE (:cardId) LIMIT (:limit)")
    fun getAll(limit: Int? = 100, cardId: Int?): Flow<List<CardTagModel>>

    @Query("SELECT * FROM cardTags WHERE (:cardId) AND (:tagId)")
    fun get(cardId: Int, tagId: Int)

    @Query("DELETE FROM cardTags WHERE stackId = :stackId AND cardId = :cardId")
    fun delete(stackId: Int, cardId: Int)

    @Insert
    fun insertAll(vararg cards: CardTagModel)
}