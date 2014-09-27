/**
 * 
 */
package me.koeb.invoiceR.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * my Configuratio of the thymeleaf template engine
 * 
 * @author Alexander Köb
 *
 */

public class ThymeleafConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ThymeleafConfig.class);

    @Bean
    public ServletContextTemplateResolver templateResolver() {

        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        // NB, selecting HTML5 as the template mode.
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(true);
        resolver.setCharacterEncoding("UTF-8");

        LOG.info("loaded template Resolver" + resolver.toString());
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[] { "*" });
        viewResolver.setCache(false);
        return viewResolver;
    }

}
