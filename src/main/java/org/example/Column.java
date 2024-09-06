package org.example;

// Импортируем необходимые классы для создания аннотаций
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Аннотация @Target указывает, что эта аннотация может применяться только к полям класса
@Target(value = ElementType.FIELD)
// Аннотация @Retention указывает, что эта аннотация будет доступна во время выполнения программы
@Retention(value = RetentionPolicy.RUNTIME)
// Объявляем аннотацию @interface Column
public @interface Column {

}