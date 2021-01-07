package com.yjjk.monitor.utility;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-05-20 09:54:05
 **/
public class MonitorUtils {

    public static int getSleepingState(Integer state) {
        if (state > 0) {
            return 1;
        }
        return 0;
    }
}
