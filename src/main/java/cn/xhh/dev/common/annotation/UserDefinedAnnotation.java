package cn.xhh.dev.common.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.lang.annotation.*;

/**
 * 自定义注解实现
 * 四个元注解：
 * 1.@Target(ElementType.TYPE)，代表可以加在哪里，比如说加在类上，加在方法上，加在属性上
 * 2.@Retention(RetentionPolicy.RUNTIME)，代表注解生效的时间
 * 3.@Documented，代表是否有文档注释
 * 4.@Inherited，代表子类是否继承该注解
 * @author Administrator
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserDefinedAnnotation {
    String value() default "";
}


@EnableAutoConfiguration
class TestUseAnnotation {

    private static String name;

    private static String valueName;

    @UserDefinedAnnotation(value = "lisi")
    public void setName(String tempName){
        name = tempName;
    }

    @Value(value = "wangwu")
    public void setValueName(String tempName){
        valueName = tempName;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestUseAnnotation.class, args);
        System.out.println(name);
        System.out.println(valueName);
    }
}