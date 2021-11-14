package com.cleanup.todoc.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProjectViewModel extends AndroidViewModel {

    private final ProjectDataRepository mProjectDataRepository;
    private final TaskDataRepository mTaskDataRepository;

    private final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public ProjectViewModel(Application application) {
        super(application);

        mProjectDataRepository = new ProjectDataRepository(application);
        mTaskDataRepository = new TaskDataRepository(application);
    }

    //----------
    // PROJECTS
    //----------

    public LiveData<List<Project>> getAllProjects() {
        return mProjectDataRepository.getAllProjects();
    }


    //----------
    // TASKS
    //----------

    public LiveData<List<Task>> getAllTasks() {
        return mTaskDataRepository.getAllTasks();
    }

    public void insertTask(Task task) {
        databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mTaskDataRepository.insertTask(task);
            }
        });
    }

    public void deleteTask(Task task) {databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            mTaskDataRepository.deleteTask(task);
        }
    });

    }

    public void deleteAllTask(){databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            mTaskDataRepository.deleteAllTask();
        }
    });
        }
}



