package com.yjjk.monitor.utility;

import java.math.BigDecimal;

/**
 * @program: monitor
 * @description:
 * @author: CentreS
 * @create: 2020-04-08 09:57:21
 **/
public class MathUtils {

    /**
     * 百分比
     *
     * @param denominator
     * @param numerator
     * @return
     */
    public static Double percents(Integer denominator, Integer numerator) {
        if (denominator == 0) {
            return 0.0;
        }
        BigDecimal d = new BigDecimal(denominator);
        BigDecimal n = new BigDecimal(numerator);
        BigDecimal divide = n.divide(d, 2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), 2, BigDecimal.ROUND_FLOOR);
        return divide.doubleValue();
    }

    /**
     * <p>华氏度  = 32 + 摄氏度 × 1.8。</p>
     *
     * @param degree 需要转换的温度
     * @param scale  保留的小数位
     * @return
     */
    public static Double centigrade2Fahrenheit(Double degree, int scale) {
        if (degree == null) {
            return null;
        }
        double d = 32 + degree * 1.8;
        return new BigDecimal(d).setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static Double centigrade2Fahrenheit(Double degree) {
        return centigrade2Fahrenheit(degree, 1);
    }

    /**
     * <p>摄氏度  = (华氏度 - 32) ÷ 1.8。</p>
     *
     * @param degree 需要转换的温度
     * @param scale  保留的小数位
     * @return
     */
    public static Double fahrenheit2Centigrade(Double degree, int scale) {
        double d = (degree - 32) / 1.8;
        return new BigDecimal(d).setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static Double fahrenheit2Centigrade(Double degree) {
        return fahrenheit2Centigrade(degree, 1);

    }
}
