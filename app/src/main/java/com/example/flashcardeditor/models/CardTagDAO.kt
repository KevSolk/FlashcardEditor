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
    @Query("SELECT * FROM cardTags")
    fun getAll(): Flow<List<CardTagModel>>

    @Query("DELETE FROM cardTags WHERE stackId = :stackId AND cardId = :cardId")
    fun delete(stackId: Int, cardId: Int)

    @Insert
    fun insertAll(vararg cards: CardTagModel)
}