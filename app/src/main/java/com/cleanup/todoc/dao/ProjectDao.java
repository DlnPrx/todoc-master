package com.cleanup.todoc.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project_table")
    LiveData<List<Project>> getAllProjects();

    @Query("SELECT * FROM project_table WHERE project_id = :projectId")
    LiveData<Project> getProjectById(long projectId);


    @Insert
    void insertProject(Project project);

    @Delete
    void deleteProject(Project project);

    @Update
    void updateProject(Project project);
}
