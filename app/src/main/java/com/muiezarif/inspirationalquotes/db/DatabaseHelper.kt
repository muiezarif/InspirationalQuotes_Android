package com.muiezarif.inspirationalquotes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 400007) {
    private var myDataBase: SQLiteDatabase? = null
    private val mContext: Context
    @Throws(IOException::class)
    fun createDataBase() {
        val mDataBaseExist = checkDataBase()
        if (!mDataBaseExist) {
            this.readableDatabase
            close()
            try {
                copyDataBase()
                Log.d(TAG, "createDatabase database created")
            } catch (mIOException: IOException) {
                throw Error("ErrorCopyingDataBase")
            }
        }
    }


    private fun checkDataBase(): Boolean {
        val dbFile = File(DB_PATH + DB_NAME)
        return dbFile.exists()
    }

    @Throws(IOException::class)
    private fun copyDataBase() {
        val mInput =
            mContext.assets.open(DB_NAME)
        val outFileName =
            DB_PATH + DB_NAME
        val mOutput: OutputStream = FileOutputStream(outFileName)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) {
            mOutput.write(mBuffer, 0, mLength)
        }
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    @Throws(SQLiteException::class)
    fun openDataBase(): Boolean
    {
        val mPath = DB_PATH + DB_NAME
        myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY)
        return myDataBase != null
    }

    @Synchronized
    override fun close() {
        if (myDataBase != null) myDataBase?.close()
        super.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        private const val TAG = "DataBaseHelper "
        private var DB_PATH = ""
        private const val DB_NAME = "inspirationalquotes.db"
    }

    init {
        DB_PATH = "/data/data/" + context.packageName + "/databases/"
        mContext = context
    }
}