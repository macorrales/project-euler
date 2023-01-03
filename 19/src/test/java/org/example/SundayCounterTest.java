package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SundayCounterTest {
    SundayCounter sc = new SundayCounter();

    @ParameterizedTest
    @CsvSource(textBlock = """
            19010102,  0, No Sundays in two days
            19010108,  0, First whole week
            19010131,  0, 4 in Jan 1901
            19010203,  0, 5 first change of month
            19010303,  0, After first February
            19010901,  1, First Sunday on 1st of month
            19011129,  1, Still one
            19011231,  2, Two Sundays on 1st of month
            19080301, 14, Sunday first within leap year.
            20001231, 171, final target score
            """)

    void evaluateNumberOfSundays( String to, int count, String description){
        assertEquals(count,sc.firstOfMonthSundays(to),description);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            19010101, 1
            19010102, 2
            19010201, 32
            19010301, 60
            19010401, 91
            19010501, 121
            19010601, 152
            19010701, 182
            19010801, 213
            19010901, 244
            19011001, 274
            19011101, 305
            19011201, 335
              """)
    void countDaysSinceEpoch(String date, int days){
        assertEquals(days,sc.daysSinceEpoch(date));
    }

    @Test
    void generateOneFirsOfMonth(){
        assertEquals(1, sc.firstDayOfMonthUntil("19010101").count());
        assertTrue(sc.firstDayOfMonthUntil("19010101").toList().contains("19010101"));
    }

    @Test
    void generateSeveralFirstOfMonth(){
        assertEquals(3,sc.firstDayOfMonthUntil("19010319").count());
        var days = sc.firstDayOfMonthUntil("19010319").toList();
        assertTrue(days.contains("19010101"));
        assertTrue(days.contains("19010201"));
        assertTrue(days.contains("19010301"));
    }

    @Test
    void verifyDayOfYear(){
        System.out.println(LocalDate.of(1901,9,1).getDayOfYear());
        System.out.println(LocalDate.of(1901,9,1).getDayOfWeek());
        System.out.println(244%7);
        System.out.println(LocalDate.of(1901,1,1).getDayOfWeek());
    }
}