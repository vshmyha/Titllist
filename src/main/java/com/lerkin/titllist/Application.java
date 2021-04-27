package com.lerkin.titllist;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class Application {
    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(8082);
            String webDir = "src/main/web";
            File webDirFile = new File(webDir);
            String webDirAbsolutePath = webDirFile.getAbsolutePath();
            tomcat.addWebapp("", webDirAbsolutePath);
            tomcat.start();
            Server server = tomcat.getServer();
            server.await();
        } catch (ServletException | LifecycleException e) {
            e.printStackTrace();
        }
    }
}
