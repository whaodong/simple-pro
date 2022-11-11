package pers.hd.simplepro.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import pers.hd.simplepro.server.annotation.rest.AnonymousGetMapping;
import pers.hd.simplepro.server.util.SpringContextHolder;

@SpringBootApplication
public class SimpleProServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleProServerApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
        return fa;
    }

    @AnonymousGetMapping("/")
    public String index() {
        return "Backend service started successfully";
    }

}
