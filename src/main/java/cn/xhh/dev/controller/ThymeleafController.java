package cn.xhh.dev.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * thymeleaf模板测试
 * @author Administrator
 */
@Controller
public class ThymeleafController {
    @RequestMapping("/returnThymeleaf")
    public String returnThymeleaf(Model model) {
        model.addAttribute("msg", "springboot thymeleaf");
        return "thymeleaf";
    }
}
