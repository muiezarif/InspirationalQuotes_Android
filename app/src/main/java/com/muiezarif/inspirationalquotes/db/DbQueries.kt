package com.muiezarif.inspirationalquotes.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.muiezarif.inspirationalquotes.db.models.AuthorModel

import com.muiezarif.inspirationalquotes.db.models.QuotesModel
import java.io.IOException


class DbQueries(context: Context?) {

    private val TAG = "DataAdapter"
    private var mContext: Context? = context
    private var mDb: SQLiteDatabase? = null
    private var mDbHelper: DatabaseHelper? = mContext?.let { DatabaseHelper(it) }
    init {
        createDatabase()
    }

    @Throws(SQLException::class)
    fun createDatabase(): DbQueries? {
        try {
            mDbHelper?.createDataBase()
        } catch (mIOException: IOException) {
            Log.e(TAG, "$mIOException  UnableToCreateDatabase")
            throw Error("UnableToCreateDatabase")
        }
        return this
    }

    @Throws(SQLException::class)
    fun open(): DbQueries? {
        mDb = try {
            mDbHelper?.openDataBase()
            mDbHelper?.close()
            mDbHelper?.readableDatabase
        } catch (mSQLException: SQLException) {
            Log.e(TAG, "open >>$mSQLException")
            throw mSQLException
        }
        return this
    }

    fun close() {
        mDbHelper?.close()
    }

    fun getAllQuotes():ArrayList<QuotesModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: ArrayList<QuotesModel> = ArrayList<QuotesModel>()
        try {
            cursor = mDb?.rawQuery("select * from inspirational_quotes WHERE random() > 8998023222112865499", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("id")
                val authorNameCol = cursor.getColumnIndex("author")
                val favCol = cursor.getColumnIndex("fav")
                val quoteCol = cursor.getColumnIndex("quote")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val author = cursor.getString(authorNameCol)
                    val fav = cursor.getInt(favCol)
                    val quote = cursor.getString(quoteCol)
                    operatorArray.add(QuotesModel(id,quote,author,fav))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        close()
        return operatorArray
    }

    fun getQuotesByAuthor(authorName:String):ArrayList<QuotesModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: ArrayList<QuotesModel> = ArrayList<QuotesModel>()
        try {
            cursor = mDb?.rawQuery("select * from inspirational_quotes where author='"+authorName+"'", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("id")
                val authorNameCol = cursor.getColumnIndex("author")
                val favCol = cursor.getColumnIndex("fav")
                val quoteCol = cursor.getColumnIndex("quote")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val author = cursor.getString(authorNameCol)
                    val fav = cursor.getInt(favCol)
                    val quote = cursor.getString(quoteCol)
                    operatorArray.add(QuotesModel(id,quote,author,fav))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        close()
        return operatorArray
    }


    fun getAllAuthors():ArrayList<AuthorModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: ArrayList<AuthorModel> = ArrayList<AuthorModel>()
        try {
            cursor = mDb?.rawQuery("select * from authors WHERE random() > 8334023222112865485", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("id")
                val authorNameCol = cursor.getColumnIndex("name")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val author = cursor.getString(authorNameCol)
                    operatorArray.add(AuthorModel(id,author))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        close()
        return operatorArray
    }

    fun updateQuoteFav(id:Int,check:Int){
        createDatabase()
        open()
        val values = ContentValues()
        values.put("fav", check)
        val args = arrayOf<String>(id.toString())
        mDb?.update("inspirational_quotes",values,"id=?",args)
        close()
    }

    fun getFavQuotes():ArrayList<QuotesModel>{
        createDatabase()
        open()
        val cursor: Cursor?
        val operatorArray: java.util.ArrayList<QuotesModel> = java.util.ArrayList<QuotesModel>()
        try {
            cursor = mDb?.rawQuery("select * from inspirational_quotes where fav="+1+"", null)
            if (cursor != null) {
                val idCol = cursor.getColumnIndex("id")
                val authorNameCol = cursor.getColumnIndex("author")
                val favCol = cursor.getColumnIndex("fav")
                val quoteCol = cursor.getColumnIndex("quote")
                for (i in 0 until cursor.count) {
                    cursor.moveToPosition(i)
                    val id = cursor.getInt(idCol)
                    val author = cursor.getString(authorNameCol)
                    val fav = cursor.getInt(favCol)
                    val quote = cursor.getString(quoteCol)
                    operatorArray.add(QuotesModel(id,quote,author,fav))
                }
            } else {
                Log.e("query", "getAuthorData" + "Cursor is null")
            }
        } catch (e: Exception) {
            Log.e("query", " getEventFromDB$e")
        }
        Log.i("query",operatorArray.toString())
        close()
        return operatorArray
    }



}