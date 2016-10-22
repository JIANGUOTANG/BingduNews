package com.jian.util;

import android.os.Build;

/**
 * Created by jian on 2016/8/13.
 */
public class VersionUtil {
    public static boolean isMaterial(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP;

    }
}
