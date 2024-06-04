package ru.job4j.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .map(Pupil::subjects)
                .flatMap(List::stream)
                .mapToInt(Subject::score)
                .average()
                .orElse(0D);
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
                .map(student ->
                    new Tuple(student.name(),
                            student.subjects().stream()
                                    .mapToInt(Subject::score)
                                    .average()
                                    .orElse(0D)
                    )
                )
                .toList();
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        Map<String, Double> subjects = stream
                .map(Pupil::subjects)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        Subject::name,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::score)
                ));
        return subjects.entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .toList();
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(student -> new Tuple(
                        student.name(),
                        student.subjects().stream()
                                .mapToInt(Subject::score)
                                .sum()
                ))
                .max(Comparator.comparingDouble(Tuple::score))
                .orElse(null);
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        Map<String, Double> subjects = stream
                .map(Pupil::subjects)
                .flatMap(List::stream)
                .collect(
                Collectors.groupingBy(
                        Subject::name,
                        LinkedHashMap::new,
                        Collectors.summingDouble(Subject::score)
                )
        );
        return subjects.entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }
}
