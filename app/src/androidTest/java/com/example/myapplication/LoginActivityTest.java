package com.example.myapplication;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    private LoginActivity mActivity = null;


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    void testLaunch(){
        View signIn = mActivity.findViewById(R.id.signIn);
        assertNotNull(signIn);

        View btn_signUp = mActivity.findViewById(R.id.btn_signUp);
        assertNotNull(btn_signUp);

        View editTextEmail = mActivity.findViewById(R.id.editTextEmail);
        assertNotNull(editTextEmail);

        View editTextPassword = mActivity.findViewById(R.id.editTextPassword);
        assertNotNull(editTextPassword);

        View btn_continue = mActivity.findViewById(R.id.btn_continue);
        assertNotNull(btn_continue);

        View progressBar = mActivity.findViewById(R.id.progressBar);
        assertNotNull(progressBar);



    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}