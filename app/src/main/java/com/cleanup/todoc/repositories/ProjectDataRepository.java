package com.cleanup.todoc.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;


    public ProjectDataRepository(Application application) {
        TodocDatabase todocDatabase = TodocDatabase.getInstance(application);
        mProjectDao = todocDatabase.projectDao();

    }

    //GET ALL PROJECTS
    public LiveData<List<Project>> getAllProjects() {
        return mProjectDao.getAllProjects();
    }

}
