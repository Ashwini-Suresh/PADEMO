package com.example.personalaccounthmi.presenter;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.model.FragmentAllProfileModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class FragmentAllProfilePresenterTest {
    @Mock
    MainActivityContract.View view;
    @Mock
    FragmentAllProfileModel fragmentAllProfileModel;
    @InjectMocks
    FragmentAllProfilePresenter fragmentAllProfilePresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProfileList() throws Exception {
        when(fragmentAllProfileModel.toGetProfile()).thenReturn(new ArrayList<ProfileData>(Arrays.asList(new ProfileData(0, "name", "avatar", true))));

        ArrayList<ProfileData> result = fragmentAllProfilePresenter.getProfileList();
        Assert.assertEquals(new ArrayList<ProfileData>(Arrays.asList(new ProfileData(0, "name", "avatar", true))), result);
    }

    @Test
    public void testGetProfileCount() throws Exception {
        when(fragmentAllProfileModel.getProfileCount()).thenReturn(0L);

        long result = fragmentAllProfilePresenter.getProfileCount();
        Assert.assertEquals(0L, result);
    }

    @Test
    public void testRefreshAllProfiles() throws Exception {
        fragmentAllProfilePresenter.refreshAllProfiles();
    }

    @Test
    public void testSwitchActiveProfile() throws Exception {
        fragmentAllProfilePresenter.switchActiveProfile(0);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme