package com.example.personalaccounthmi.model;

import com.example.personalaccounthmi.BindServiceInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.presenter.FragmentEditProfilePresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class FragmentEditProfileModelTest {
    @Mock
    BindServiceInterface bindServiceInterface;
    @Mock
    FragmentEditProfilePresenter mFragmentEditProfilePresenter;
    @InjectMocks
    FragmentEditProfileModel fragmentEditProfileModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHighlightProfile() throws Exception {
        when(bindServiceInterface.activeProfile()).thenReturn(new ProfileData(0, "name", "avatar", true));

        ProfileData result = fragmentEditProfileModel.highlightProfile();
        Assert.assertEquals(new ProfileData(0, "name", "avatar", true), result);
    }

    @Test
    public void testDeleteSelectedProfile() throws Exception {
        fragmentEditProfileModel.deleteSelectedProfile();
    }

    @Test
    public void testGetProfileCountModel() throws Exception {
        when(bindServiceInterface.getProfileCount()).thenReturn(0L);

        long result = fragmentEditProfileModel.getProfileCountModel();
        Assert.assertEquals(0L, result);
    }

    @Test
    public void testNotifyPersonalAccountChange() throws Exception {
        fragmentEditProfileModel.notifyPersonalAccountChange(0, 0);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme