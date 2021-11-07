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

    public void insertProject(Project project) {
        mProjectDataRepository.insertProject(project);
    }

    public void deleteProject(Project project) {
        mProjectDataRepository.deleteProject(project);
    }


    public void updateProject(Project project) {
        mProjectDataRepository.updateProject(project);

    }

    public LiveData<Project> getProjectById(long projectId) {
        return mProjectDataRepository.getProjectById(projectId);
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

    public void updateTask(Task task) {
        mTaskDataRepository.updateTask(task);
    }


}



