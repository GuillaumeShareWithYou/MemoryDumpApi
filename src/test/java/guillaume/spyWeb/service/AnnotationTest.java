package guillaume.spyWeb.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Awesome {
    int value() default 0;
}

@Awesome(45)
public class AnnotationTest {
    @Awesome
    public void test() {

    }

    public static void main(String[] args) throws Exception {
        int value = AnnotationTest.class.getAnnotation(Awesome.class).value();
        int mainValue = AnnotationTest.class.getMethod("test").getAnnotation(Awesome.class).value();
        System.out.println(value);
        System.out.println(mainValue);
    }
}
