package com.conaldes.scomputations.compdata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.conaldes.scomputations.compdata.dao.ResultDao;
import com.conaldes.scomputations.compdata.model.Result;
import com.conaldes.scomputations.util.Constants;

@Database(entities = { Result.class }, version = 1)
public abstract class ResultDatabase extends RoomDatabase {

    public abstract ResultDao getResultDao();


    private static ResultDatabase resultDB;

    // synchronized is use to avoid concurrent access in multithred environment
    public static /*synchronized*/ ResultDatabase getInstance(Context context) {
        if (null == resultDB) {
            resultDB = buildDatabaseInstance(context);
        }
        return resultDB;
    }

    private static ResultDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                ResultDatabase.class,
                Constants.DB_NAME).allowMainThreadQueries().build();
    }

    public  void cleanUp(){
        resultDB = null;
    }
}