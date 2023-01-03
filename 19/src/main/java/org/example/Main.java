package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        long numberOfSundaysOnFirst = Stream.iterate(LocalDate.of(1901, 1, 1),
                                                    d -> d.isBefore(LocalDate.of(2001, 1, 1)),
                                                    d -> d.plus(1, ChronoUnit.MONTHS))
                                    .filter(d -> d.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                                    .count();
        System.out.println(numberOfSundaysOnFirst);

    }
}
