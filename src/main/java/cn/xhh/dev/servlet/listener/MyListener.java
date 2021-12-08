package cn.xhh.dev.servlet.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Administrator
 */
public class MyListener implements HttpSessionListener {

    public static int online;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        System.out.println(++online);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
    }
}
