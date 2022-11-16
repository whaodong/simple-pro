package pers.hd.simplepro.server.security.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.hd.simplepro.server.security.model.LoginProperties;
import pers.hd.simplepro.server.security.model.SecurityProperties;

/**
 * 配置文件转换Pojo类的 统一配置类
 *
 * @author WangHaoDong
 */
@Configuration
public class ConfigBeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}
