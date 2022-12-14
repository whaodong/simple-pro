package pers.hd.simplepro.server.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目扫描配置
 *
 * @author WangHaoDong
 */
@Configuration
@ComponentScan(basePackages = {
        "pers.hd.simplepro.server.domain.service.impl"
})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "pers.hd.simplepro.server.domain.repository",
})
@EntityScan(basePackages = {
        "pers.hd.simplepro.server.domain.model.entity",
})
public class SimpleProConfiguration {
}
