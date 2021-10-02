package com.cleanup.todoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.graphics.Color;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

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
public class TaskDaoTest {
    private static String TAG ="debug123";


    //database
    private TodocDatabase mDatabase;
    //DATA
    private static final long PROJECT_ID = 1;
    private static final String PROJECT_NAME = "Project name test";
    private static final int PROJECT_COLOR = Color.rgb(100,0,0);
    private static final Project PROJECT_DEMO = new Project(PROJECT_ID, PROJECT_NAME,PROJECT_COLOR);



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
    public void insertAndGetProject() throws InterruptedException {
        // BEFORE : Adding a new project
        List<Project> projectList = LiveDataTestUtil.getValue(mDatabase.projectDao().getAllProjects());
        mDatabase.projectDao().insertProject(PROJECT_DEMO);
       
        // TEST
//        LiveData<Project> project = (mDatabase.projectDao().getProjectById(PROJECT_ID));
//        Assert.assertNotNull(project);
//        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);

    }

}
