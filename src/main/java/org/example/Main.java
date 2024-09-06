package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        // Создаем объекты класса Humster с различными параметрами
        Humster humster = new Humster("Byria", "7", 200, "HIGH");
        Humster humster1 = new Humster("Murk", "8", 234, "LOW");
        Humster humster2 = new Humster("Valli", "3", 443, "MEDIUM");

        // Создаем объекты класса Cat с различными параметрами
        Cat cat = new Cat("Pushok", "5", "White");
        Cat cat1 = new Cat("Pushok", "7", "Black");

        // Создаем таблицы в базе данных на основе аннотаций классов Humster и Cat
        AnnotationProcessor.createTable(humster); // Создаем таблицу для Humster
        AnnotationProcessor.createTable(cat); // Создаем таблицу для Cat

        // Вставляем объекты хомяков в таблицу базы данных
        AnnotationProcessor.insertIntoTable(humster);
        AnnotationProcessor.insertIntoTable(humster1);
        AnnotationProcessor.insertIntoTable(humster2);

        // Вставляем объекты котов в таблицу базы данных
        AnnotationProcessor.insertIntoTable(cat);
        AnnotationProcessor.insertIntoTable(cat1);
    }
}
