/**
 * 
 */
package me.koeb.invoiceR.config;

/**
 * @author Alexander Köb
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableSpringDataWebSupport
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = { "me.koeb.invoiceR" })
public class InvoiceR { // extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceR.class, args);
    }

    // @Bean
    // public EmbeddedServletContainerFactory servletContainer() {
    // JettyEmbeddedServletContainerFactory factory = new
    // JettyEmbeddedServletContainerFactory();
    // factory.setPort(9000);
    // factory.setSessionTimeout(10, TimeUnit.MINUTES);
    // factory.addJettyErrorPages(new ErrorPage(HttpStatus.404,
    // "/notfound.html");
    // factory.addJettyErrorPages(null, null);
    // return factory;
    // }

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    // }

    // @Bean
    // public MessageSource messageSource() {
    //
    // ReloadableResourceBundleMessageSource messageSource = new
    // ReloadableResourceBundleMessageSource();
    // messageSource.setBasenames("classpath:messages/messages",
    // "classpath:messages/validation");
    // // if true, the key of the message will be displayed if the key is not
    // // found, instead of throwing a NoSuchMessageException
    // messageSource.setUseCodeAsDefaultMessage(true);
    // messageSource.setDefaultEncoding("UTF-8");
    // // # -1 : never reload, 0 always reload
    // messageSource.setCacheSeconds(0);
    // return messageSource;
    // }
}
