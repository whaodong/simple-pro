package pers.hd.simplepro.server.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author WangHaoDong
 */
@Configuration
@ComponentScan(basePackages = {
        "pers.hd.simplepro.server.service.impl"
})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "pers.hd.simplepro.server.dao",
})
@EntityScan(basePackages = {
        "pers.hd.simplepro.server.model.entity",
})
public class SimpleProConfiguration {
}
