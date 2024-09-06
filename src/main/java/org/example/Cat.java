package org.example;

@Table(title = "Koteyka")
public class Cat {
    @Column
    private String name; // Имя кота

    @Column
    private String age; // Возраст кота
    @Column
    private String color; // Цвет кота (не размечен аннотацией @Column)

    // Конструктор класса Cat для инициализации полей
    public Cat(String name, String age, String color) {
        this.name = name; // Устанавливаем имя кота
        this.age = age; // Устанавливаем возраст кота
        this.color = color; // Устанавливаем цвет кота
    }

    // Геттер для получения значения поля color
    public String getColor() {
        return color;
    }

    // Сеттер для установки значения поля color
    public void setColor(String color) {
        this.color = color;
    }
}
//создать новый атрибутый не размеченный в анотации колумн