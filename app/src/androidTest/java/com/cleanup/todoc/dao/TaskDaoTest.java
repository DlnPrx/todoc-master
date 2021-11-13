package com.cleanup.todoc.dao;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.LiveDataTestUtil;
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

    //DATA
    private static final long PROJECT_ID = 0;
    private static final Project PROJECT_DEMO = new Project(PROJECT_ID, "Project One", 0xFFEADAD1);
    private static final String TASK_NAME = "Task name test";
    private static final long TIMESTAMP = 1001;
    private static final Task TASK_DEMO = new Task(PROJECT_ID, TASK_NAME, TIMESTAMP);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    //database
    private TodocDatabase mDatabase;

    @Before
    public void initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();

    }

    @After
    public void closeDb() {
        mDatabase.close();

    }

    @Test
    public void testInitDatabase() {

        Assert.assertNotNull(this.mDatabase);

    }

    @Test
    public void insertGetAndDeleteTask() throws InterruptedException {
        // adding project
        mDatabase.projectDao().insertProject(PROJECT_DEMO);
        TASK_DEMO.setId(1);
        // Adding  task
        mDatabase.taskDao().insertTask(TASK_DEMO);

        List<Task> taskList = LiveDataTestUtil.getValue(mDatabase.taskDao().getAllTasks());
        Assert.assertEquals(1, taskList.size());

        //get task
        taskList = LiveDataTestUtil.getValue(mDatabase.taskDao().getAllTasks());

        Assert.assertEquals(taskList.get(0).getName(), TASK_NAME);
        Assert.assertEquals(taskList.get(0).getProjectId(), PROJECT_ID);
        Assert.assertEquals(taskList.get(0).getCreationTimestamp(), TIMESTAMP);

        //delete task
        mDatabase.taskDao().deleteTask(TASK_DEMO);
        taskList = LiveDataTestUtil.getValue(mDatabase.taskDao().getAllTasks());
        Assert.assertEquals(0, taskList.size());


    }
}
