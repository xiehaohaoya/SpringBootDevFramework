package cn.xhh.dev.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
@ConfigurationProperties(prefix = "cat")
public class Cat {

    public Cat() {}

    public Cat(String name, Integer age, List likes) {
        this.name = name;
        this.age = age;
        this.likes = likes;
    }

    private String name;

    private Integer age;

    private List likes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List getLikes() {
        return likes;
    }

    public void setLikes(List likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", likes=" + likes +
                '}';
    }

}
