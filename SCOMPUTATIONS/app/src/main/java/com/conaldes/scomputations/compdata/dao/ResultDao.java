package com.conaldes.scomputations.compdata.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.conaldes.scomputations.compdata.model.Result;

import java.util.List;

@Dao
public interface ResultDao {
    /*
    @Insert
    Long insertTask(Note note);

    @Query("SELECT * FROM Note ORDER BY created_at desc")
    LiveData<List<Note>> fetchAllTasks();


    @Query("SELECT * FROM Note WHERE id =:taskId")
    LiveData<Note> getTask(int taskId);
    */
    //@Query("SELECT * FROM silocomp ORDER BY type")
    @Query("SELECT * FROM silocomp")
    List<Result> getResults();

    @Query("SELECT * FROM silocomp WHERE type =:comp_type ORDER BY result_id")
    List<Result> getResults(String comp_type);

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insertResult(Result result);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void updateResult(Result results);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    //@Delete
    //void deleteResult(int id);

    // Result... is varargs, here note is an array
    /*
     * delete list of objects from database
     * @param note, array of oject to be deleted
     */
    @Delete
    void deleteResults(Result... result);
}

