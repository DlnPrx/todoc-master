package com.cleanup.todoc.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectDataRepository;
import com.cleanup.todoc.repository.TaskDataRepository;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {
    //----------
    //REPOSITORIES
    //----------
    private ProjectDataRepository mProjectDataRepository;
    private TaskDataRepository mTaskDataRepository;



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

    public void createProject(Project project) {
        mProjectDataRepository.createProject(project);

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

    public void createTask(Task task) {
        mTaskDataRepository.createTask(task);
    }

    public void deleteTask(Task task) {
        mTaskDataRepository.deleteTask(task);
    }

    public void updateTask(Task task) {
        mTaskDataRepository.updateTask(task);
    }


}



