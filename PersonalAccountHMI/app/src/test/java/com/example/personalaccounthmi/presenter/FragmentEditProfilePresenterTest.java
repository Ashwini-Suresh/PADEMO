package com.example.personalaccounthmi.presenter;

import com.example.personalaccounthmi.MainActivityContract;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.model.FragmentEditProfileModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class FragmentEditProfilePresenterTest {
    @Mock
    MainActivityContract.EditProfileVIew view;
    @Mock
    FragmentEditProfileModel fragmentEditProfileModel;
    @InjectMocks
    FragmentEditProfilePresenter fragmentEditProfilePresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHighlightProfile() throws Exception {
        when(fragmentEditProfileModel.highlightProfile()).thenReturn(new ProfileData(0, "name", "avatar", true));

        ProfileData result = fragmentEditProfilePresenter.getHighlightProfile();
        Assert.assertEquals(new ProfileData(0, "name", "avatar", true), result);
    }

    @Test
    public void testRefreshEditProfile() throws Exception {
        fragmentEditProfilePresenter.refreshEditProfile();
    }

    @Test
    public void testDeleteProfileSelected() throws Exception {
        fragmentEditProfilePresenter.deleteProfileSelected();
    }

    @Test
    public void testGetProfileCount() throws Exception {
        when(fragmentEditProfileModel.getProfileCountModel()).thenReturn(0L);

        long result = fragmentEditProfilePresenter.getProfileCount();
        Assert.assertEquals(0L, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme