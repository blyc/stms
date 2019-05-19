package com.hp.common.bo;

import java.util.Calendar;

public class DateInfo {
    private Integer year;
    private Integer month;
    private Integer maxDay;

    public DateInfo() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONDAY) + 1;
        c.set(year, month, 0);
        maxDay = c.get(Calendar.DAY_OF_MONTH);
        c.clear();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(Integer maxDay) {
        this.maxDay = maxDay;
    }

    @Override
    public String toString() {
        return "DateInfo{" +
                "year=" + year +
                ", month=" + month +
                ", maxDay=" + maxDay +
                '}';
    }

}
