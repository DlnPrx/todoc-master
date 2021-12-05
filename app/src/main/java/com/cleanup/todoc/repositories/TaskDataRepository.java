package com.cleanup.todoc.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;

    public TaskDataRepository(Application application) {
        TodocDatabase todocDatabase = TodocDatabase.getInstance(application);
        mTaskDao = todocDatabase.taskDao();

    }

    //GET ALL TASKS
    public LiveData<List<Task>> getAllTasks() {
        return mTaskDao.getAllTasks();
    }

    //CREATE A NEW TASK
    public void insertTask(Task task) {
        mTaskDao.insertTask(task);
    }

    //DELETE A TASK
    public void deleteTask(Task task) {
        mTaskDao.deleteTask(task);
    }

    //DELETE ALL TASKS
    public void deleteAllTask() {
        mTaskDao.deleteAllTasks();
    }
}
