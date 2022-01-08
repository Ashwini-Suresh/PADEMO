package com.training.personalaccountservice;

import android.content.ContentValues;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;

@RunWith(RobolectricTestRunner.class)
@Config(packageName = "com.training.personalaccountservice",sdk = 28)
public class PASDataBaseManagerTest {


    PASDataBaseManager pASDataBaseManager;

    @Before
    public void setUp() {
        pASDataBaseManager = PASDataBaseManager.getInstance(RuntimeEnvironment.getApplication());

    }

    @After
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field instance = PASDataBaseManager.class.getDeclaredField("INSTANCE");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void testGetInstance() {
        PASDataBaseManager result = PASDataBaseManager.getInstance(Robolectric.setupService(PAService.class).getApplicationContext());
        Assert.assertNotNull(result);
    }

    @Test
    public void testAdd() {
        pASDataBaseManager.addProfile(2, "profile2", "avatar2");
    }

    @Test
    public void readAllTest() {
        Assert.assertNotNull(pASDataBaseManager.readAllData());
    }

    @Test
    public void readActiveProfileTest() {
        pASDataBaseManager.getActiveProfile(1);
    }

    @Test
    public void getRowCountTest() {
        Assert.assertEquals(1, pASDataBaseManager.getRowCount());
        pASDataBaseManager.addProfile(2, "profile2", "avatar2");
        Assert.assertEquals(2, pASDataBaseManager.getRowCount());
    }

    @Test
    public void deleteActiveProfileTest() {
        Assert.assertFalse(pASDataBaseManager.deleteProfile(2));
        Assert.assertTrue(pASDataBaseManager.deleteProfile(1));
    }

    @Test
    public void updateActiveProfileName_Success_Test() {
        pASDataBaseManager.updateActiveProfileName(1,"Akshay");
    }
    @Test
    public void updateActiveProfileName_Fail_Test() {
        pASDataBaseManager.updateActiveProfileName(3,"Akshay");
    }
    @Test
    public void updateActiveProfileAvatar_Success_Test() {
        pASDataBaseManager.updateActiveProfileAvatar(1,"avatar");
    }
    @Test
    public void updateActiveProfileAvatar_Fail_Test() {
        pASDataBaseManager.updateActiveProfileAvatar(3,"avatar");
    }
    @Test
    public void readActiveProfileSettingsTest() {
        Assert.assertNotNull(pASDataBaseManager.readActiveProfileSettings(1));
        Assert.assertEquals(0, pASDataBaseManager.readActiveProfileSettings(7).getCount());
    }

    @Test
    public void updateActiveProfileSettingsTest() {
        ContentValues cv = new ContentValues();
        cv.put("profile_settings", "settings");
        Assert.assertEquals(1,pASDataBaseManager.updateActiveProfileSettings(1,cv));
    }
}

