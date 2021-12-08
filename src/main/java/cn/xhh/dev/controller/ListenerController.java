package cn.xhh.dev.controller;

import cn.xhh.dev.servlet.listener.MyListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/listener")
public class ListenerController {

    @RequestMapping("/online")
    public String online(HttpSession session) {
        session.setAttribute("session","session");
        return "在线人数：" + MyListener.online;
    }
}
