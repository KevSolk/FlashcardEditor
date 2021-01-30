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
    @Query("SELECT * FROM stacks")
    fun getAll(): Flow<List<CardModel>>

    @Query("DELETE FROM stacks WHERE id IN (:ids)")
    fun delete(vararg ids: Int)

    @Insert
    fun insertAll(vararg stacks: StackModel)
}