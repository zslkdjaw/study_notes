package cn.edu.bnuz.order_system.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * @author Zhang Hao
 * @create 2022-05-07-17:45
 */
public class DateCompareUtils {
    public static int compareYear(String date){
        LocalDate localDate = LocalDate.now();
        Period period = Period.between(LocalDate.parse(date),localDate);
        return (int)period.getYears();
    }



}
