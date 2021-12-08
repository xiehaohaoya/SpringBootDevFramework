package cn.xhh.dev.controller;

import cn.xhh.dev.entity.Cat;
import cn.xhh.dev.entity.Dog;
import cn.xhh.dev.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 属性注入的三种方式
 * 1.@Autowired：不能将值注入静态变量，通过反射机制实现
 * 2.setter方法 + @Autowired：可以注入静态变量
 * 3.构造器方法注入：可以注入静态变量，推荐，指定了属性注入的顺序
 * @author Administrator
 */

/**
 * 读取配置文件的两种方式
 * 1.@Value
 * 2.在Bean类中@Component + @ConfigurationProperties注解 + setter，getter方法
 * @author Administrator
 */
@Controller
@RequestMapping("/fieldInjection")
public class FieldInjectionController {

    private static Person person;

    private final Dog dog;

    /**
     * 直接对属性进行注入
     */
    @Autowired
    private Cat cat;

    /**
     * 通过构造方法对属性进行注入-推荐
     * @param tempDog dog
     */
    public FieldInjectionController(Dog tempDog){
        dog = tempDog;
    }

    /**
     * 通过setter方法进行属性注入，可以对静态属性进行注入
     * @param tempTPerson person
     */
    @Autowired
    public void setPerson(Person tempTPerson){
        person = tempTPerson;
    }

    /**
     * @return String
     */
    @ResponseBody
    @RequestMapping("/returnPerson")
    public String returnPerson() {
        return person.toString();
    }

    /**
     * @return String
     */
    @ResponseBody
    @RequestMapping("/returnDog")
    public String returnDog() {
        return dog.toString();
    }

    /**
     * @return String
     */
    @ResponseBody
    @RequestMapping("/returnCat")
    public String returnCat() {
        return cat.toString();
    }
}
