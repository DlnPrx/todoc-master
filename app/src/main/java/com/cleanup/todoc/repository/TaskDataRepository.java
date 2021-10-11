package com.cleanup.todoc.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskDataRepository {

    private final TaskDao mTaskDao;

    static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();


    public TaskDataRepository(Application application) {
        TodocDatabase todocDatabase = TodocDatabase.getInstance(application);
        mTaskDao = todocDatabase.taskDao();

    }

    //getAllTasks
    public LiveData<List<Task>> getAllTasks() {
        return mTaskDao.getAllTasks();
    }


    //create
    public void createTask(Task task) {
        databaseWriteExecutor.execute(() -> mTaskDao.insertTask(task));


    }

    //delete
    public void deleteTask(Task task) {
        databaseWriteExecutor.execute(() -> mTaskDao.deleteTask(task));

    }

    //update
    public void updateTask(Task task) {
        databaseWriteExecutor.execute(() -> mTaskDao.updateTask(task));

    }


}
