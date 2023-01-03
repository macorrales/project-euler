package org.example;

import java.util.stream.Stream;

public class SundayCounter {
    public static final int LONG_MONTH = 31;
    public static final int FEBRUARY = 28;
    public static final int SHORT_MONTH = 30;
    public static final int TUESDAY_OFFSET = 1;
    public static final int EPOCH_YEAR = 1901;


    /**
     * @param to date in format yyyymmdd
     * @return
     */
    public long firstOfMonthSundays(String to) {
        // range go from first to first and return number of days
        return firstDayOfMonthUntil(to)
                .filter(date -> (daysSinceEpoch(date) + TUESDAY_OFFSET) % 7 == 0)
                .count();
    }

    public Stream<String> firstDayOfMonthUntil(String to) {

        return Stream.iterate("19010101",
                s -> to.compareTo(s) >= 0,
                s -> {
                    var month = getMonth(s);
                    var year = getYear(s);
                    if (month == 12) {
                        year++;
                        month = 1;
                    } else {
                        month++;
                    }
                    return String.format("%d%02d01", year, month);
                });
    }

    public int daysSinceEpoch(String date) {
        var day = getDay(date);
        var month = getMonth(date);
        var year = getYear(date);
        return day + daysFromFullMonths(month, year) + (year - EPOCH_YEAR) * 365 + additionalLeapYearDays(year);
    }

    private static int additionalLeapYearDays(int year) {
        var doNotCountCurrentYear =1;
        return (year - 1900 - doNotCountCurrentYear)  / 4 ;
    }

    private static int getDay(String date) {
        return Integer.parseInt(date.substring(6));
    }

    private static int getYear(String date) {
        return Integer.parseInt(date.substring(0, 4));
    }

    private static int getMonth(String date) {
        return Integer.parseInt(date.substring(4, 6));
    }

    private static int daysFromFullMonths(int monthTo, int year) {
        switch (monthTo) {
            case 2:
                return LONG_MONTH;
            case 3:
                return LONG_MONTH + februaryDays(year);
            case 4:
                return LONG_MONTH * 2 + februaryDays(year);
            case 5:
                return LONG_MONTH * 2 + februaryDays(year) + SHORT_MONTH;
            case 6:
                return LONG_MONTH * 3 + februaryDays(year) + SHORT_MONTH;
            case 7:
                return LONG_MONTH * 3 + februaryDays(year) + SHORT_MONTH * 2;
            case 8:
                return LONG_MONTH * 4 + februaryDays(year) + SHORT_MONTH * 2;
            case 9:
                return LONG_MONTH * 5 + februaryDays(year) + SHORT_MONTH * 2;
            case 10:
                return LONG_MONTH * 5 + februaryDays(year) + SHORT_MONTH * 3;
            case 11:
                return LONG_MONTH * 6 + februaryDays(year) + SHORT_MONTH * 3;
            case 12:
                return LONG_MONTH * 6 + februaryDays(year) + SHORT_MONTH * 4;
            default:
                return 0;
        }
    }

    private static int februaryDays(int year) {
        return FEBRUARY + ((year % 4) == 0 ? 1 : 0);
    }
}

