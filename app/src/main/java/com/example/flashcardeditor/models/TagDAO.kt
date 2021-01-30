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
    @Query("SELECT * FROM tags LIMIT (:limit)")
    fun getAll(limit: Int? = 100): Flow<List<TagModel>>

    @Query("SELECT * FROM tags WHERE (:ids)")
    fun get(ids: Int)

    @Query("DELETE FROM tags WHERE id IN (:ids)")
    fun delete(vararg ids: Int)

    @Insert
    fun insertAll(vararg tags: TagModel)
}