package com.example.personalaccounthmi.model;

import com.example.personalaccounthmi.BindServiceInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.presenter.FragmentAllProfilePresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class FragmentAllProfileModelTest {
    @Mock
    ArrayList<ProfileData> list;
    @Mock
    BindServiceInterface bindServiceInterface;
    @Mock
    FragmentAllProfilePresenter mFragmentAllProfilePresenter;
    @InjectMocks
    FragmentAllProfileModel fragmentAllProfileModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToGetProfile() throws Exception {
        when(bindServiceInterface.getAllProfile()).thenReturn(Arrays.<ProfileData>asList(null));

        ArrayList<ProfileData> result = fragmentAllProfileModel.toGetProfile();
        Assert.assertEquals(new ArrayList<ProfileData>(Arrays.asList(null)), result);
    }

    @Test
    public void testNotifyPersonalAccountChange() throws Exception {
        fragmentAllProfileModel.notifyPersonalAccountChange(0, 0);
    }

    @Test
    public void testGetProfileCount() throws Exception {
        when(bindServiceInterface.getProfileCount()).thenReturn(0L);

        long result = fragmentAllProfileModel.getProfileCount();
        Assert.assertEquals(0L, result);
    }

    @Test
    public void testSwitchActiveProfile() throws Exception {
        fragmentAllProfileModel.switchActiveProfile(0);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme