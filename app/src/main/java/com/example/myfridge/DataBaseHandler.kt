package com.example.myfridge

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MYDB2"
val TABLE_NAME = "gLists2"//
val COL_ID = "id"
val COL_NAME = "gname"
val COL_WEBSITE = "website"
val COL_NOOFITEMS = "noofitems"
val COl_DATE = "expirydate"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_WEBSITE + " VARCHAR(256)," +
                COL_NOOFITEMS + " INTEGER," +
                COl_DATE + " VARCHAR(256));"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(glist : GList){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME,glist.gName)
        cv.put(COL_WEBSITE,glist.website)
        cv.put(COL_NOOFITEMS,glist.noOfItems)
        cv.put(COl_DATE,glist.expiryDate)
        var result = db.insert(TABLE_NAME,null,cv)

        //This is just for debugging

        if(result == -1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()

    }

    fun readData() : MutableList<GList>{
        var list : MutableList<GList> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                var glist = GList()
                glist.id = result.getString(0).toInt()
                glist.gName = result.getString(1)
                glist.website = result.getString(2)
                glist.noOfItems = result.getString(3).toInt()
                glist.expiryDate =result.getString(4)
                list.add(glist)


            }while(result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }

}


