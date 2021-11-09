package com.cleanup.todoc.viewModels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.viewmodels.ProjectViewModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectViewModelTest {

    ProjectViewModel mProjectViewModel;
    TodocDatabase mDatabase;
    Task mTaskTest;
    Task mTaskTest2;
    List<Task> mTaskArrayList;
    List<Project> mProjectArrayList;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setUp() throws InterruptedException {

        ApplicationProvider.getApplicationContext().deleteDatabase("todoc_database");
        initDatabase();
        initViewModel();
        initModels();
        initObservers();
        Thread.sleep(2000);
    }


    @After
    public void tearDown() {

        ApplicationProvider.getApplicationContext().deleteDatabase("todoc_database");
    }


    @Test
    public void getAllProjects() throws InterruptedException {

        Thread.sleep(1000);
        //Assert 3 projects prepopulate
        Assert.assertEquals(3, mProjectArrayList.size());

    }


    @Test
    public void getAllTasks() {


        Assert.assertEquals(5, mTaskArrayList.size());
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {

        //Assert prepopulate TaskList = 5
        Assert.assertEquals(5, mTaskArrayList.size());


        //insert one Task and assert taskList = 6
        mTaskTest.setId(10);
        mProjectViewModel.insertTask(mTaskTest);


        Thread.sleep(1000);

        mTaskArrayList = LiveDataTestUtil.getValue(mProjectViewModel.getAllTasks());
        Assert.assertEquals(6, mTaskArrayList.size());

        //delete the task and assert taskList = 5
        mProjectViewModel.deleteTask(mTaskTest);

        Thread.sleep(1000);
        mTaskArrayList = LiveDataTestUtil.getValue(mProjectViewModel.getAllTasks());
        Thread.sleep(1000);
        Assert.assertEquals(5, mTaskArrayList.size());

    }


    private void initViewModel() {
        mProjectViewModel = new ProjectViewModel(ApplicationProvider.getApplicationContext());

    }

    private void initDatabase() {

        mDatabase = TodocDatabase.getInstance(ApplicationProvider.getApplicationContext());
    }

    private void initModels() {
        //  mProjectTest = new Project(3, "Project Test", 0xFFB4CDBA);
        mTaskTest = new Task(1, "Task test", 1050);
        mTaskTest2 = new Task(1, "Task test2", 1051);
    }

    private void initObservers() throws InterruptedException {
        mProjectArrayList = LiveDataTestUtil.getValue(mDatabase.projectDao().getAllProjects());
        mTaskArrayList = LiveDataTestUtil.getValue(mDatabase.taskDao().getAllTasks());

    }
}