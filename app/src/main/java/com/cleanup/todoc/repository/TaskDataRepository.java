package com.cleanup.todoc.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskDataRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTaskss;
    static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();


    public TaskDataRepository(Application application) {
        TodocDatabase todocDatabase = TodocDatabase.getInstance(application);
        mTaskDao = todocDatabase.taskDao();
        mAllTaskss = mTaskDao.getAllTasks();
    }

    //getAllTasks
    public LiveData<List<Task>> getAllTasks() {
        return mTaskDao.getAllTasks();
    }

    //get tasks by projectId and order by A-Z
    public LiveData<List<Task>> getTaskByAlphabeticalAscending() {
        return mTaskDao.getTasksByAlphabeticalAscending();
    }

    //get tasks by projectId and order by Z-A
    public LiveData<List<Task>> getTaskByAlphabeticalDescending() {
        return mTaskDao.getTasksByAlphabeticalDescending();
    }

    //get tasks by projectId and order by recent-older
    public LiveData<List<Task>> getTaskByTasksRecentFirst() {
        return mTaskDao.getTasksRecentFirst();
    }

    //get tasks by projectId and order by older-recent
    public LiveData<List<Task>> getTaskByOlderFirst() {
        return mTaskDao.getTasksOlderFirst();
    }

    //create
    public void createTask(Task task) {databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            mTaskDao.insertTask(task);
        }
    });


    }

    //delete
    public void deleteTask(Task task) {databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            mTaskDao.deleteTask(task);
        }
    });

    }

    //update
    public void updateTask(Task task) {databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            mTaskDao.updateTask(task);
        }
    });

    }


}
