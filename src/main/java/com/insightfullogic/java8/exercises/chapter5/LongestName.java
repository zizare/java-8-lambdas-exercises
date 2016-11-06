package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class LongestName {

    private static Comparator<Artist> comparing = Comparator.comparing(artist -> artist.getName().length());

    public static Artist byReduce(List<Artist> artists) {
        return artists.stream().reduce((acc, artist) -> {
            return (comparing.compare(acc, artist) >= 0) ? acc : artist;
        }).orElseThrow(RuntimeException::new);
    }

    public static Artist byCollecting(List<Artist> artists) {

        return artists.stream().collect(Collectors.maxBy(comparing)).orElseThrow(() -> new RuntimeException());
    }

}
