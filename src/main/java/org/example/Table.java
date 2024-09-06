package org.example;

// Импортируем необходимые классы для создания аннотаций
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Аннотация @Target указывает, что эта аннотация может применяться только к типам (классам, интерфейсам и т.д.)
@Target(value = ElementType.TYPE)
// Аннотация @Retention указывает, что эта аннотация будет доступна во время выполнения программы
@Retention(value = RetentionPolicy.RUNTIME)
// Объявляем аннотацию @interface Table
public @interface Table {
    // Метод title() используется для задания названия таблицы в базе данных
    String title();
}