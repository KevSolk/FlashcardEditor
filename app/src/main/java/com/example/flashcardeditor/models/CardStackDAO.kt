 package com.example.flashcardeditor.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Entity(tableName = "cardStacks")
data class CardStackModel(
        @ColumnInfo(name="stackId") val StackId: String,
        @ColumnInfo(name="cardId") val CardId: String,
){}

@Dao
interface CardStackDAO{
    @Query("SELECT * FROM cardStacks")
    fun getAll(): Flow<List<CardStackModel>>

    @Query("DELETE FROM cardStacks WHERE stackId = :stackId AND cardId = :cardId")
    fun delete(stackId: Int, cardId: Int)

    @Insert
    fun insertAll(vararg cards: CardStackModel)
}