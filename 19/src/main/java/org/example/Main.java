package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        System.out.println(IntStream.range(0, 100 * 366)
                .mapToObj(i -> LocalDate.of(1901, 1, 1).plus(i, ChronoUnit.MONTHS))
                .filter(d -> d.isBefore(LocalDate.of(2001, 1, 1)))
                .filter(d -> d.getDayOfWeek() == DayOfWeek.SUNDAY)
                .count());

        long numberOfSundaysOnFirst = Stream.iterate(LocalDate.of(1901, 1, 1),
                                                    d -> d.isBefore(LocalDate.of(2001, 1, 1)),
                                                    d -> d.plus(1, ChronoUnit.MONTHS))
                                    .filter(d -> d.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                                    .count();
        System.out.println(numberOfSundaysOnFirst);

    }
}