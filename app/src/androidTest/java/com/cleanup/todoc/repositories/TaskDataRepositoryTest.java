package com.cleanup.todoc.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Task;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDataRepositoryTest {

    public static final Task taskDemo = new Task(1, "task demo", 1050);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    List<Task> mTaskArrayList;
    TaskDataRepository mTaskDataRepository;

    @Before
    public void setUp() {

        initRepository();
        TodocDatabase database = TodocDatabase.getInstance(ApplicationProvider.getApplicationContext());
        database.taskDao().deleteAllTasks();
    }


    @Test
    public void getAllTasks() throws InterruptedException {
        mTaskArrayList = new ArrayList<>();
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        Assert.assertEquals(0, mTaskArrayList.size());

    }

    @Test
    public void insertGetAndDeleteTask() throws InterruptedException {

        //Assert 0 tasks
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        Assert.assertEquals(0, mTaskArrayList.size());

        //insert task, assert  1 tasks and task name = task demo
        mTaskDataRepository.insertTask(taskDemo);
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        Assert.assertEquals(1, mTaskArrayList.size());
        Assert.assertEquals(mTaskArrayList.get(0).getName(),taskDemo.getName());
        //delete task, assert 0 tasks
        mTaskDataRepository.deleteAllTask();
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        Assert.assertEquals(0, mTaskArrayList.size());


    }

    private void initRepository() {

        mTaskDataRepository = new TaskDataRepository(ApplicationProvider.getApplicationContext());
    }

}
