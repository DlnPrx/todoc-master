package com.cleanup.todoc.viewModels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.viewmodels.ProjectViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    ProjectViewModel mProjectViewModel;
    TodocDatabase mDatabase;
    Task mTaskTest;
    Task mTaskTest2;
    List<Task> mTaskArrayList;
    List<Project> mProjectArrayList;

    @Before
    public void setUp() throws InterruptedException {

        TodocDatabase database = TodocDatabase.getInstance(ApplicationProvider.getApplicationContext());
        database.taskDao().deleteAllTasks();
        initDatabase();
        initViewModel();
        initModels();
        initObservers();

    }


    @Test
    public void getAllProjects() {

        //  Thread.sleep(1000);
        //Assert 3 projects prepopulate
        Assert.assertEquals(3, mProjectArrayList.size());

    }


    @Test
    public void getAllTasks() {


        Assert.assertEquals(0, mTaskArrayList.size());
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {

        //Assert prepopulate TaskList = 5
        Assert.assertEquals(0, mTaskArrayList.size());


        //insert one Task and assert taskList = 6
        mTaskTest.setId(1);

        mProjectViewModel.insertTask(mTaskTest);


        Thread.sleep(200);

        mTaskArrayList = LiveDataTestUtil.getValue(mProjectViewModel.getAllTasks());
        Assert.assertEquals(1, mTaskArrayList.size());
        //delete the task and assert taskList = 5
        mProjectViewModel.deleteTask(mTaskTest);
        mProjectViewModel.deleteAllTask();

        Thread.sleep(200);
        mTaskArrayList = LiveDataTestUtil.getValue(mProjectViewModel.getAllTasks());
        Thread.sleep(200);
        Assert.assertEquals(0, mTaskArrayList.size());

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