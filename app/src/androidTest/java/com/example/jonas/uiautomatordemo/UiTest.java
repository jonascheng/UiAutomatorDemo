package com.example.jonas.uiautomatordemo;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
//import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

// test annotation
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by jonas on 2017/2/4.
 */

@RunWith(AndroidJUnit4.class)
public class UiTest {
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String CAL_PACKAGE_NAME = "com.sec.android.app.popupcalculator";
    private UiDevice mDevice;
    private Instrumentation instrumentation;

    @Before
    public void setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        mDevice = UiDevice.getInstance(instrumentation);

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertNotNull(launcherPackage);
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void testDemo() throws UiObjectNotFoundException {
        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(CAL_PACKAGE_NAME);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(CAL_PACKAGE_NAME).depth(0)),
                LAUNCH_TIMEOUT);

        UiObject seven = mDevice.findObject(new UiSelector().resourceId("com.sec.android.app.popupcalculator:id/bt_07"));
        seven.click();
        UiObject plus = mDevice.findObject(new UiSelector().resourceId("com.sec.android.app.popupcalculator:id/bt_add"));
        plus.click();
        UiObject one = mDevice.findObject(new UiSelector().resourceId("com.sec.android.app.popupcalculator:id/bt_01"));
        one.click();
        UiObject equal = mDevice.findObject(new UiSelector().resourceId("com.sec.android.app.popupcalculator:id/bt_equal"));
        equal.click();

        // verify result
        UiObject result = mDevice.findObject(new UiSelector().resourceId("com.sec.android.app.popupcalculator:id/txtCalc"));
        assertEquals("8", result.getText());
    }

    @After
    public void tearDown() {
        mDevice.pressHome();
    }
}
