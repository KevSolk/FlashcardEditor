package com.example.flashcardeditor.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Entity(tableName = "stacks")
data class StackModel(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name="name") val Name: String,
        @ColumnInfo(name="isStrict") val IsStrict: Boolean,
        @ColumnInfo(name="isArchived") val IsArchived: Boolean,
        @ColumnInfo(name="dateCreated") val DateCreated: Date,
        @ColumnInfo(name="dateModified") val DateModified: Date,
        @ColumnInfo(name="dateDeleted") val DateDeleted: Date?
){}

@Dao
interface StackDAO{
    @Query("SELECT * FROM stacks LIMIT (:limit)")
    fun getAll(limit: Int? = 100): Flow<List<CardModel>>

    @Query("SELECT * FROM stacks WHERE (:ids)")
    fun get(ids: Int)

    @Query("DELETE FROM stacks WHERE id IN (:ids)")
    fun delete(vararg ids: Int)

    @Insert
    fun insertAll(vararg stacks: StackModel)
}