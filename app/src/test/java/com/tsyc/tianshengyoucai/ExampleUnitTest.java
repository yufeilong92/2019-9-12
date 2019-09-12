package com.tsyc.tianshengyoucai;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void tex() {
        String com = "2";
        String[] split = com.split(",");
        for (int i = 0; i < split.length; i++) {
            Log.i("==", split[i]);
        }
    }
}