package com.example.flashcardeditor.utility

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.flashcardeditor.models.*
import java.util.concurrent.Executors

@Database(entities = arrayOf(
        CardStackModel::class,
        CardModel::class,
        CardTagModel::class,
        StackModel::class,
        StackTagModel::class,
        TagModel::class
), version = 1)
abstract class FlashcardDatabase : RoomDatabase() {
    //DAO
    abstract fun cardDAO(): CardDAO
    abstract fun cardStackDAO(): CardStackDAO
    abstract fun cardTagDAO(): CardTagDAO
    abstract fun stackDAO(): StackDAO
    abstract fun stackTagDAO(): StackTagDAO
    abstract fun tagDAO(): TagDAO

    companion object {
        @Volatile private var instance: FlashcardDatabase? = null

        fun getDBInstance(context: Context): FlashcardDatabase {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also{instance = it}
            }
        }

        private fun buildDatabase(context: Context): FlashcardDatabase {
            return Room.databaseBuilder(context, FlashcardDatabase::class.java, "flashcard-editor-database.db")
                    .build()
        }
    }
}