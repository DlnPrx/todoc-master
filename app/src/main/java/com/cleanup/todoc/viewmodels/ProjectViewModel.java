package com.cleanup.todoc.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {
    //----------
    //REPOSITORIES
    //----------
    private final ProjectDataRepository mProjectDataRepository;
    private final TaskDataRepository mTaskDataRepository;


    //----------
    //DATA
    //----------


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
        mTaskDataRepository.insertTask(task);
    }

    public void deleteTask(Task task) {
        mTaskDataRepository.deleteTask(task);
    }


}



