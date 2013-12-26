package es.anloci.raspberry.camera.rest.server;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        context.registerShutdownHook();
//        Thread.sleep(60 * 1000);
    }
}
