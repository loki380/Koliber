import annotations.KoliberDescription;
import annotations.KoliberFieldDescription;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class KoliberPrinter {

    private static final int SPACE = 3;

    public static void printClassStructure(Class<?> classType) {
        printClassStructure(classType, 0);
    }

    private static void printClassStructure(Class<?> classType, int level) {
        Field[] declaredFields = classType.getDeclaredFields();
        int spaces = level * SPACE;

        Arrays.stream(declaredFields)
                .filter(field -> field.isAnnotationPresent(KoliberFieldDescription.class) || field.isAnnotationPresent(KoliberDescription.class))
                .sorted((f1, f2) -> {
                    int f1Priority;
                    int f2Priority;
                    if (f1.isAnnotationPresent(KoliberFieldDescription.class)) {
                        f1Priority = f1.getAnnotation(KoliberFieldDescription.class).priority();
                    } else {
                        f1Priority = f1.getAnnotation(KoliberDescription.class).priority();
                    }

                    if (f2.isAnnotationPresent(KoliberFieldDescription.class)) {
                        f2Priority = f2.getAnnotation(KoliberFieldDescription.class).priority();
                    } else {
                        f2Priority = f2.getAnnotation(KoliberDescription.class).priority();
                    }

                    return Integer.compare(f2Priority, f1Priority);
                })
                .forEach(field -> {
                    if (field.isAnnotationPresent(KoliberFieldDescription.class)) {
                        printWithFormat(field.getName() + " - " + field.getAnnotation(KoliberFieldDescription.class).comment(), spaces);
                    } else if (field.isAnnotationPresent(KoliberDescription.class)) {
                        Type type = field.getGenericType();
                        if (type instanceof Class<?>) {
                            printWithFormat(getShortClassName(field.getType()) + " - " + field.getType().getAnnotation(KoliberDescription.class).comment(), spaces);
                            printClassStructure(field.getType(), level + 1);
                        } else if (type instanceof ParameterizedType) {
                            Class<?> subClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (subClass.isAnnotationPresent(KoliberDescription.class)) {
                                printWithFormat(getShortClassName(subClass) + " - " + subClass.getAnnotation(KoliberDescription.class).comment(), spaces);
                                printClassStructure(subClass, level + 1);
                            }
                        }
                    }
                });
    }

    private static String getShortClassName(Class<?> classType) {
        return StringUtils.lowerCase(ClassUtils.getShortCanonicalName(classType));
    }

    private static void printWithFormat(String str, int spaceCount) {
        if (spaceCount == 0) {
            System.out.printf("%s \n", str);
        } else {
            System.out.printf("%" + spaceCount + "s %s \n", " ", str);
        }
    }
}
