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
    private TodocDatabase mDatabase;

    @Before
    public void setUp() throws InterruptedException {

        initRepository();
        mDatabase = TodocDatabase.getInstance(ApplicationProvider.getApplicationContext());
        mDatabase.taskDao().deleteAllTasks();
        // mTaskDataRepository.deleteAllTask();
    }


    @Test
    public void getAllTasks() throws InterruptedException {
        mTaskArrayList = new ArrayList<>();
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        //   Thread.sleep(2000);
        Assert.assertEquals(0, mTaskArrayList.size());

    }

    @Test
    public void insertGetAndDeleteTask() throws InterruptedException {

        //Assert 5 tasks prepopulate
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        Assert.assertEquals(0, mTaskArrayList.size());

      /*  for (int i = 0; i < mTaskArrayList.size(); i++){
            Log.d("test123", "1" + String.valueOf(mTaskArrayList.get(i).getName()));
        }*/


        //insert task, assert  6 tasks
        Task taskTest = new Task(1, "taskDemo", 1029);
        mTaskDataRepository.insertTask(taskDemo);
        Thread.sleep(200);
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        // Thread.sleep(500);
        Assert.assertEquals(1, mTaskArrayList.size());
/*        for (int i = 0; i < mTaskArrayList.size(); i++){
            Log.d("test123", "2" + String.valueOf(mTaskArrayList.get(i).getName()));
        }*/

        //delete task, assert 5 tasks
        // mTaskDataRepository.deleteTask(taskDemo);
        mTaskDataRepository.deleteAllTask();
        Thread.sleep(200);
        mTaskArrayList = LiveDataTestUtil.getValue(mTaskDataRepository.getAllTasks());
        Assert.assertEquals(0, mTaskArrayList.size());


    }

    private void initRepository() {

        mTaskDataRepository = new TaskDataRepository(ApplicationProvider.getApplicationContext());
    }

}
