package com.cleanup.todoc;

import static org.junit.Assert.assertTrue;

import android.graphics.Color;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    //database
    private TodocDatabase mDatabase;
    //DATA

   // private static final String TASK_NAME = "Task name test";
    //private static final Project TASK_DEMO = new Task(TASK_NAME,);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();

    }
    @After
    public void closeDb() throws Exception {
        mDatabase.close();

    }

    @Test
    public void insertGetAndDeleteProject() throws InterruptedException {
        // Adding a new project

//        mDatabase.projectDao().insertProject(PROJECT_DEMO);
//        List<Project> projectList = LiveDataTestUtil.getValue(mDatabase.projectDao().getAllProjects());
//        Assert.assertEquals(1, projectList.size());
//
//        // Get project
//        LiveData<Project> project = (mDatabase.projectDao().getProjectById(PROJECT_ID));
//        Assert.assertNotNull(project);
//        assertTrue(LiveDataTestUtil.getValue(project).getName().equals(PROJECT_DEMO.getName()) && LiveDataTestUtil.getValue(project).getId() == PROJECT_ID);
//
//        //Delete project
//        mDatabase.projectDao().deleteProject(PROJECT_DEMO);
//        projectList = LiveDataTestUtil.getValue(mDatabase.projectDao().getAllProjects());
//        Assert.assertEquals(0, projectList.size());
    }
}
