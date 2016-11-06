package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

    private final static Set<Characteristics> characteristics = new HashSet<>();

    static {
        characteristics.add(Characteristics.IDENTITY_FINISH);
    }


    private final Function<? super T, ? extends K> classifier;

    public GroupingBy(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (kListMap, t) -> {
            K key = classifier.apply(t);
//           More efficient... List<T> elements = kListMap.computeIfAbsent(key, k -> new ArrayList<T>());
            ArrayList<T> value = new ArrayList<>();
            kListMap.putIfAbsent(key, value);
            kListMap.get(key).add(t);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (kListMap, kListMap2) -> {
            Map<K, List<T>> result = kListMap;
            kListMap2.forEach((k, ts) -> {
                //redo with merge
                result.putIfAbsent(k, ts);
                result.computeIfPresent(k, (k1, ts1) -> {
                    ts1.addAll(ts);
                    return ts1;
                });
            });
            return result;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return kListMap -> kListMap;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
