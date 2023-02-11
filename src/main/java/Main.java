import annotations.KoliberDescription;
import annotations.KoliberFieldDescription;
import models.Invoice;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static java.lang.Boolean.TRUE;

public class Main {
    public static void main(String[] args) {
        printClassStructure(Invoice.class);
    }

    private static void printClassStructure(Class<?> classType) {
        Field[] declaredFields = classType.getDeclaredFields();

        Arrays.stream(declaredFields).forEach(field -> {
            if (field.isAnnotationPresent(KoliberFieldDescription.class)) {
                System.out.println(field.getName() + " - " + field.getAnnotation(KoliberFieldDescription.class).comment());
            } else if (field.isAnnotationPresent(KoliberDescription.class)) {
                Type type = field.getGenericType();

                if (type instanceof Class<?>) {
                    System.out.println(field.getType().getSimpleName().toLowerCase() + " - " + field.getType().getAnnotation(KoliberDescription.class).comment());
                    printClassStructure(field.getType());
                } else if (type instanceof ParameterizedType) {
                    Type subType = ((ParameterizedType) type).getActualTypeArguments()[0];
                }
            }
        });
    }
}