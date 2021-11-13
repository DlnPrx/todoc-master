package com.cleanup.todoc.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.model.Project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectDataRepositoryTest {


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    List<Project> mProjectArrayList;
    ProjectDataRepository mProjectDataRepository;

    @Before
    public void setUp() {

        initRepository();
    }


    @Test
    public void getAllProjects() throws InterruptedException {

        mProjectArrayList = new ArrayList<>();
        mProjectArrayList = LiveDataTestUtil.getValue(mProjectDataRepository.getAllProjects());
        //Assert 3 projects prepopulate
        Assert.assertEquals(3, mProjectArrayList.size());

    }


    private void initRepository() {
        mProjectDataRepository = new ProjectDataRepository(ApplicationProvider.getApplicationContext());
    }
}
