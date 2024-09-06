package org.example;

// Аннотация @Table используется для указания названия таблицы в базе данных
@Table(title = "Homa")
public class Humster {

    // Перечисление woolLenght для обозначения длины шерсти
    public enum woolLenght {
        LOW, MEDIUM, HIGH;

        // Метод для получения строки с названием длины шерсти
        public String getWoolLenght() {
            return this.toString();
        }
    }

    // Аннотация @Column указывает, что это поле будет колонкой в таблице
    @Column
    private String name; // Имя хомяка

    @Column
    private String age; // Возраст хомяка

    @Column
    private int maxRunDistance; // Максимальная дистанция, которую может пробежать хомяк

    @Column
    private String woolLenght; // Длина шерсти хомяка

    // Конструктор класса Humster для инициализации полей
    public Humster(String name, String age, int maxRunDistance, String woolLenght) {
        this.name = name; // Устанавливаем имя хомяка
        this.age = age; // Устанавливаем возраст хомяка
        this.maxRunDistance = maxRunDistance; // Устанавливаем максимальную дистанцию бега
        this.woolLenght = woolLenght; // Устанавливаем длину шерсти
    }
}