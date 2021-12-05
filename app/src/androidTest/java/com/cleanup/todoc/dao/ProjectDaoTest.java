package com.cleanup.todoc.dao;

import static org.junit.Assert.assertTrue;

import android.graphics.Color;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {


    //DATA
    private static final long PROJECT_ID = 1;
    private static final String PROJECT_NAME = "Project name test";
    private static final int PROJECT_COLOR = Color.rgb(100, 0, 0);
    private static final Project PROJECT_DEMO = new Project(PROJECT_ID, PROJECT_NAME, PROJECT_COLOR);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    //database
    private TodocDatabase mDatabase;

    @Before
    public void initDb() {

        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getTargetContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }


    //TESTS


    @Test
    public void insertGetAndDeleteProject() throws InterruptedException {

        //insert a new project
        mDatabase.projectDao().insertProject(PROJECT_DEMO);
        List<Project> projectList = LiveDataTestUtil.getValue(mDatabase.projectDao().getAllProjects());
        Assert.assertEquals(1, projectList.size());

        // Get project
        LiveData<Project> project = (mDatabase.projectDao().getProjectById(PROJECT_ID));
        Assert.assertNotNull(project);
        assertTrue(LiveDataTestUtil.getValue(project).getName().equals(PROJECT_DEMO.getName()) && LiveDataTestUtil.getValue(project).getId() == PROJECT_ID);

        //Delete project
        mDatabase.projectDao().deleteProject(PROJECT_DEMO);
        projectList = LiveDataTestUtil.getValue(mDatabase.projectDao().getAllProjects());
        Assert.assertEquals(0, projectList.size());
    }

}
