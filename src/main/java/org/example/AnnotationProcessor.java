package org.example;

import java.lang.reflect.Field; // Импортируем класс Field для работы с полями класса
import java.sql.Connection; // Импортируем класс Connection для работы с соединением к базе данных
import java.sql.DriverManager; // Импортируем класс DriverManager для управления драйверами базы данных
import java.sql.Statement; // Импортируем класс Statement для выполнения SQL-запросов

public class AnnotationProcessor {

    // Метод createTable создает таблицу в базе данных на основе аннотаций класса
    public static void createTable(Object cl) throws Exception {
        Class<?> clClass = cl.getClass(); // Получаем класс объекта
        // Проверяем, содержит ли класс аннотацию @Table
        if (!clClass.isAnnotationPresent(Table.class)) {
            throw new Exception("Класс не содержит аннотации @Table");
        }
        Table table = clClass.getAnnotation(Table.class); // Получаем аннотацию @Table
        StringBuilder sql = new StringBuilder("CREATE TABLE " + table.title() + " ("); // Строим SQL-запрос для создания таблицы
        Field[] fields = clClass.getDeclaredFields(); // Получаем все поля класса
        for (Field field : fields) {
            // Проверяем, содержит ли поле аннотацию @Column
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true); // Делаем поле доступным для чтения
                Column column = field.getAnnotation(Column.class); // Получаем аннотацию @Column
                sql.append(field.getName()).append(" "); // Добавляем имя поля в SQL-запрос
                // Определяем тип данных для поля
                if (field.getType() == int.class) {
                    sql.append("INT"); // Если поле типа int, добавляем INT в SQL-запрос
                } else if (field.getType() == String.class || field.getType().isEnum()) {
                    sql.append("TEXT"); // Если поле типа String или Enum, добавляем TEXT в SQL-запрос
                } else {
                    sql.append("TEXT"); // Для всех остальных типов данных добавляем TEXT
                }
                sql.append(","); // Добавляем запятую для разделения полей
            }
        }
        // Удаляем последнюю запятую и закрываем скобку
        if (sql.charAt(sql.length() - 1) == ',') {
            sql.deleteCharAt(sql.length() - 1); // Удаляем последнюю запятую
        }
        sql.append(");"); // Закрываем скобку
        Connection connection = null; // Инициализируем соединение с базой данных
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:lr9.db"); // Устанавливаем соединение с базой данных SQLite
            Statement statement = connection.createStatement(); // Создаем объект Statement для выполнения SQL-запросов
            statement.execute("DROP TABLE IF EXISTS " + table.title() + ";"); // Удаляем таблицу, если она уже существует
            statement.execute(sql.toString()); // Выполняем SQL-запрос для создания таблицы
        } catch (Exception e) {
            e.printStackTrace(); // Обрабатываем исключения
        } finally {
            try {
                if (connection != null) connection.close(); // Закрываем соединение с базой данных
            } catch (Exception e) {
                e.printStackTrace(); // Обрабатываем исключения
            }
        }
    }

    // Метод insertIntoTable добавляет объект в таблицу базы данных на основе аннотаций класса
    public static void insertIntoTable(Object cl) {
        Class<?> clClass = cl.getClass(); // Получаем класс объекта
        // Проверяем, содержит ли класс аннотацию @Table
        if (!clClass.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("Класс не содержит аннотации @Table");
        }
        String tableName = clClass.getAnnotation(Table.class).title(); // Получаем название таблицы из аннотации @Table
        Field[] fields = clClass.getDeclaredFields(); // Получаем все поля класса
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " ("); // Строим SQL-запрос для вставки данных
        for (Field field : fields) {
            // Проверяем, содержит ли поле аннотацию @Column
            if (field.isAnnotationPresent(Column.class)) {
                query.append(field.getName()).append(","); // Добавляем имя поля в SQL-запрос
            }
        }
        // Удаляем последнюю запятую и закрываем скобку
        if (query.charAt(query.length() - 1) == ',') {
            query.deleteCharAt(query.length() - 1);
        }
        query.append(") VALUES ("); // Добавляем часть SQL-запроса для значений
        for (Field field : fields) {
            // Проверяем, содержит ли поле аннотацию @Column
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true); // Делаем поле доступным для чтения
                try {
                    query.append("'"); // Добавляем кавычку для значения
                    // Определяем тип данных для поля и добавляем значение в SQL-запрос
                    if (field.getType() == int.class) {
                        query.append(field.getInt(cl));
                    } else if (field.getType() == String.class || field.getType().isEnum()) {
                        query.append(field.get(cl));
                    }
                    query.append("',"); // Закрываем кавычку и добавляем запятую для разделения значений
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Обрабатываем исключения
                }
            }
        }
        // Удаляем последнюю запятую и закрываем скобку
        if (query.charAt(query.length() - 1) == ',') {
            query.deleteCharAt(query.length() - 1);
        }
        query.append(")"); // Закрываем скобку
        Connection connection = null; // Инициализируем соединение с базой данных
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:lr9.db"); // Устанавливаем соединение с базой данных SQLite
            Statement statement = connection.createStatement(); // Создаем объект Statement для выполнения SQL-запросов
            statement.execute(query.toString()); // Выполняем SQL-запрос для вставки данных
        } catch (Exception e) {
            e.printStackTrace(); // Обрабатываем исключения
        } finally {
            try {
                if (connection != null) connection.close(); // Закрываем соединение с базой данных
            } catch (Exception e) {
                e.printStackTrace(); // Обрабатываем исключения
            }
        }
    }
}