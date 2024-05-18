package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sum = 0;
        int amount = pupils.size() * pupils.get(0).subjects().size();
        for (Pupil student : pupils) {
            for (Subject subject : student.subjects()) {
                sum += subject.score();
            }
        }
        return sum / (amount);
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        int amountSubjects = pupils.get(0).subjects().size();
        for (Pupil student : pupils) {
            double sum = 0;
            for (Subject subject : student.subjects()) {
                sum += subject.score();
            }
            labels.add(new Label(student.name(), sum / amountSubjects));
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> allSubjects = new LinkedHashMap<>();
        for (Pupil student : pupils) {
            for (Subject currentSubject : student.subjects()) {
                allSubjects.put(currentSubject.name(),
                        allSubjects.getOrDefault(currentSubject.name(), 0) + currentSubject.score());
            }
        }
        List<Label> labels = new ArrayList<>();
        int amountSubjects = pupils.size();
        for (Map.Entry<String, Integer> subject : allSubjects.entrySet()) {
            labels.add(new Label(subject.getKey(), ((double) subject.getValue()) / amountSubjects));
        }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        Map<String, Integer> allStudents = new LinkedHashMap<>();
        for (Pupil student : pupils) {
            for (Subject currentSubject : student.subjects()) {
                allStudents.put(student.name(),
                        allStudents.getOrDefault(student.name(), 0) + currentSubject.score());
            }
        }
        List<Label> labels = new ArrayList<>();
        for (Map.Entry<String, Integer> student : allStudents.entrySet()) {
            labels.add(new Label(student.getKey(), student.getValue()));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> allSubjects = new LinkedHashMap<>();
        for (Pupil student : pupils) {
            for (Subject currentSubject : student.subjects()) {
                allSubjects.put(currentSubject.name(),
                        allSubjects.getOrDefault(currentSubject.name(), 0) + currentSubject.score());
            }
        }
        List<Label> labels = new ArrayList<>();
        for (Map.Entry<String, Integer> subject : allSubjects.entrySet()) {
            labels.add(new Label(subject.getKey(), subject.getValue()));
        }
        Collections.sort(labels);
        return labels.get(labels.size() - 1);
    }
}
