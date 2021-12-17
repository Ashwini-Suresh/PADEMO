package com.training.personalaccountservice;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;


@RunWith(RobolectricTestRunner.class)
@Config(packageName = "com.training.personalaccountservice",sdk = 28)
public class PAServicePreferenceTest {

    PAServicePreference preference;

    public int getCount() throws IllegalAccessException, NoSuchFieldException {
        Field mLatest = PAServicePreference.class.getDeclaredField("mLatest");
        mLatest.setAccessible(true);
        return mLatest.getInt(preference);
    }
    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        preference = PAServicePreference.getInstance( Robolectric.setupService(PAService.class).getApplicationContext());
    }
    @After
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field instance = PAServicePreference.class.getDeclaredField("INSTANCE");
        instance.setAccessible(true);
        instance.set(null,null);
    }
    @Test
    public void addToPreference_addingOneValue_returnSame() {
        preference.addToPreference(5);
        assertEquals(5, preference.getActiveId());
    }

    @Test
    public void addToPreference_returnDefault() throws NoSuchFieldException, IllegalAccessException {
        assertEquals(1, getCount());
        assertEquals(1, preference.getActiveId());
    }
    @Test
    public void addToPreference_addingThreeValue_returnLast() throws NoSuchFieldException, IllegalAccessException {
        preference.addToPreference(5);
        preference.addToPreference(7);
        preference.addToPreference(17);
        assertEquals(4,getCount());
        assertEquals(17, preference.getActiveId());
    }
    @Test
    public void addToPreference_addingSameValue_returnLast() throws NoSuchFieldException, IllegalAccessException {
        preference.addToPreference(5);
        preference.addToPreference(5);
        preference.addToPreference(5);
        assertEquals(2,getCount());
        assertEquals(5, preference.getActiveId());
    }
    @Test
    public void addToPreference_addingFiveValueWithTwoSame_returnLastAndCountFour() throws NoSuchFieldException, IllegalAccessException {
        preference.addToPreference(5);
        preference.addToPreference(7);
        preference.addToPreference(17);
        assertEquals(4,getCount());
        preference.addToPreference(5);
        assertEquals(4,getCount());
        preference.addToPreference(7);
        assertEquals(4,getCount());
        assertEquals(7, preference.getActiveId());
    }
    @Test
    public void addToPreference_addingFiveValueWithTwoSameAtLast_returnLastAndCountFour() throws NoSuchFieldException, IllegalAccessException {
        preference.addToPreference(5);
        preference.addToPreference(7);
        preference.addToPreference(17);
        assertEquals(4,getCount());
        preference.addToPreference(6);
        assertEquals(4,getCount());
        preference.addToPreference(6);
        assertEquals(4,getCount());
        assertEquals(6, preference.getActiveId());
    }
    @Test
    public void delete_addingTwoAndDeletingOne_returnIdBeforeLastAndCount2() throws NoSuchFieldException, IllegalAccessException {
        preference.addToPreference(5);
        preference.addToPreference(7);
        preference.removeCurrentId();
        assertEquals(2,getCount());
        assertEquals(5, preference.getActiveId());
    }
    @Test
    public void delete_addingNoneAndDeletingOne_returnIdZeroAndCount0() throws NoSuchFieldException, IllegalAccessException {
        preference.removeCurrentId();
        assertEquals(0,getCount());
        assertEquals(0, preference.getActiveId());
    }

}