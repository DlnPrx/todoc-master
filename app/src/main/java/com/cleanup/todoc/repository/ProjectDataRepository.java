package com.cleanup.todoc.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;
   private static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public ProjectDataRepository(Application application) {
        TodocDatabase todocDatabase = TodocDatabase.getInstance(application);
        mProjectDao = todocDatabase.projectDao();

    }

    //GET ALL PROJECTS
    public LiveData<List<Project>> getAllProjects() {
        return mProjectDao.getAllProjects();
    }

    //GET PROJECT BY ID
    public LiveData<Project> getProjectById(long projectId) {
        return mProjectDao.getProjectById(projectId);
    }


    //CREATE
    public void createProject(Project project) {
        databaseWriteExecutor.execute(() -> mProjectDao.insertProject(project));


    }

    //DELETE
    public void deleteProject(Project project) {
        databaseWriteExecutor.execute(() -> mProjectDao.deleteProject(project));


    }

    //UPDATE
    public void updateProject(Project project) {
        databaseWriteExecutor.execute(() -> mProjectDao.updateProject(project));


    }

}
