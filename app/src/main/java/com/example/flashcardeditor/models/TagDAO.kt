package com.example.flashcardeditor.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Entity(tableName = "tags")
data class TagModel(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name="label") val Label: String,
        @ColumnInfo(name="isArchived") val IsArchived: Boolean,
        @ColumnInfo(name="dateCreated") val DateCreated: Date,
        @ColumnInfo(name="dateModified") val DateModified: Date,
        @ColumnInfo(name="dateDeleted") val DateDeleted: Date?
){}

@Dao
interface TagDAO{
    @Query("SELECT * FROM tags")
    fun getAll(): Flow<List<TagModel>>

    @Query("DELETE FROM tags WHERE id IN (:ids)")
    fun delete(vararg ids: Int)

    @Insert
    fun insertAll(vararg tags: TagModel)
}