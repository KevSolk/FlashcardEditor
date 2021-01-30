package com.example.flashcardeditor.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Entity(tableName = "cards")
data class CardModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name="frontText") val FrontText: String,
    @ColumnInfo(name="backText") val BackText: Boolean,
    @ColumnInfo(name="isArchived") val IsArchived: Boolean,
    @ColumnInfo(name="dateCreated") val DateCreated: Date,
    @ColumnInfo(name="dateModified") val DateModified: Date,
    @ColumnInfo(name="dateDeleted") val DateDeleted: Date?
){}

@Dao
interface CardDAO{
    @Query("SELECT * FROM cards")
    fun getAll(): Flow<List<CardModel>>

    @Query("DELETE FROM cards WHERE id IN (:ids)")
    fun delete(vararg ids: Int)

    @Insert
    fun insertAll(vararg cards: CardModel)
}

