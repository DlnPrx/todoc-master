package com.cleanup.todoc.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDataRepositoryTest {

    List<Task> mTaskArrayList;
    TaskDataRepository mTaskDataRepository;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @BeforeClass
    public static void beforeClass() {
        ApplicationProvider.getApplicationContext().deleteDatabase("todoc_database");

    }

    @Before
    public void setUp() throws InterruptedException {

        initRepository();

    }


    @After
    public void tearDown() {
        ApplicationProvider.getApplicationContext().deleteDatabase("todoc_database");
    }


    @Test
    public void getAllTasks() throws InterruptedException {
        mTaskArrayList = new ArrayList<>();
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        //Assert 5 tasks prepopulate
        Assert.assertEquals(5, mTaskArrayList.size());

    }

    @Test
    public void insertGetAndDeleteTask() throws InterruptedException {

        //Assert 5 tasks prepopulate
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        Assert.assertEquals(5, mTaskArrayList.size());

        //insert task, assert  6 tasks
        Task taskTest = new Task(1, "taskDemo", 1029);
        mTaskDataRepository.insertTask(taskTest);
        Thread.sleep(2000);
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        Thread.sleep(2000);
        Assert.assertEquals(6, mTaskArrayList.size());


        //delete task, assert 5 tasks
        mTaskDataRepository.deleteTask(taskTest);

        List<Task> mTaskArrayList2;
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        //TODO problem
        Assert.assertEquals(5, mTaskArrayList.size());


    }

    private void initRepository() {

        mTaskDataRepository = new TaskDataRepository(ApplicationProvider.getApplicationContext());
    }

}
