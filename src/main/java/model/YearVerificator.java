package model;

import java.util.Calendar;

public class YearVerificator {

    public boolean isAValidStartYear(Integer aStartYear) {
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return aStartYear > 0 && aStartYear <= currentYear;
    }

    public boolean isAValidEndYearRegardToAStartYear(Integer aStartYear, Integer anEndYear) {

            return aStartYear - anEndYear <= 0;
    }
}
