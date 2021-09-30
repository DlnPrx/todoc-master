package com.cleanup.todoc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {


    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTasks();

    /**
     * Comparator to sort task from A to Z
     */
    @Query("SELECT * FROM task_table  ORDER BY name ASC ")
    LiveData<List<Task>> getTasksByAlphabeticalAscending( );

    /**
     * Comparator to sort task from Z to A
     */
    @Query("SELECT * FROM task_table  ORDER BY name DESC")
    LiveData<List<Task>> getTasksByAlphabeticalDescending( );
    /**
     * Comparator to sort task from last created to first created
     */
    @Query("SELECT * FROM task_table  ORDER BY id ASC")
    LiveData<List<Task>> getTasksRecentFirst();

    /**
     * Comparator to sort task from first created to last created
     */
    @Query("SELECT * FROM task_table ORDER BY id DESC")
    LiveData<List<Task>> getTasksOlderFirst();

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

}
