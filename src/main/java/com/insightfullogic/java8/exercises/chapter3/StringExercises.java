package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StringExercises {

    // Question 7
    public static int countLowercaseLetters(String string) {
        return (int)string.chars().filter(value -> value>='a'&&value<= 'z').count();
    }

    // Question 8
    public static Optional<String> mostLowercaseString(List<String> strings) {
        return strings.stream().max((o1, o2) -> countLowercaseLetters(o1)-countLowercaseLetters(o2));
    }

}
