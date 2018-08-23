/*
 * Copyright (c) 2018, Zora and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.cloudkit.service.provider.configurations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * WebMvcConfiguration
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2017-12-28 14:31:02
 */
@Configuration
// @PropertySource("classpath:applications.properties")
@EnableWebMvc
@ComponentScan(
    value = "net.cloudkit.open.*"
///
//    ,includeFilters = {
//        @ComponentScan.Filter(
//            type = FilterType.ANNOTATION,
//            classes = {
//                org.springframework.stereotype.Controller.class,
//                org.springframework.controllers.bind.annotation.ControllerAdvice.class
//            }
//        )
//    }
)
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

    private static final String CHARSET = "UTF-8";

    private static final String PATH_PATTERN = "/**";

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
        "classpath:/META-INF/resources/",
        "classpath:/resources/",
        "classpath:/static/",
        "classpath:/public/"
    };

    /**
     * applications.properties
     * spring.messages.basename=messages
     * spring.messages.encoding=UTF-8
     * <p>
     * messages.properties
     */
    private MessageSource messageSource;

    public WebMvcConfiguration(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    ///
    /*
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    */

    @Override
    public Validator getValidator() {
        // ...
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }
///
//
//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.addUrlMappings("*.do");
//        registration.addUrlMappings("*.json");
//        return registration;
//    }
//
//    /**
//     * ServletRegistrationBean，FilterRegistrationBean，ServletListenerRegistrationBean
//     */
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        /// return new ServletRegistrationBean(new CXFServlet(), "/services/*");
//        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean(
//            new WSServlet(),
//            "/services/*"
//        );
//        ///
//        // registrationBean.setServlet();
//        // registrationBean.addUrlMappings();
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(new IndexFilter());
//        registration.addUrlPatterns("/");
//        return registration;
//    }
//
//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
//        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
//        registrationBean.setListener(new WSServletContextListener());
//        ///
//        // registrationBean.setOrder(1);
//        return registrationBean;
//    }

    @Bean
    public XmlMapper xmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();

        /*
         * KebabCaseStrategy: 肉串策略 - 单词小写，使用连字符'-'连接
         * SnakeCaseStrategy: 蛇形策略 - 单词小写，使用下划线'_'连接；即老版本中的 LowerCaseWithUnderscoresStrategy
         * LowerCaseStrategy: 小写策略 - 简单的把所有字母全部转为小写，不添加连接符
         * UpperCamelCaseStrategy: 驼峰策略 - 单词首字母大写其它小写，不添加连接符；即老版本中的 PascalCaseStrategy
         * PropertyNamingStrategy: LOWER_CAMEL_CASE - 第一个单词首字母小写,其它单词首字母大写
         */
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        xmlMapper.registerModule(javaTimeModule);
        return xmlMapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        /*
         * KebabCaseStrategy: 肉串策略 - 单词小写，使用连字符'-'连接
         * SnakeCaseStrategy: 蛇形策略 - 单词小写，使用下划线'_'连接；即老版本中的 LowerCaseWithUnderscoresStrategy
         * LowerCaseStrategy: 小写策略 - 简单的把所有字母全部转为小写，不添加连接符
         * UpperCamelCaseStrategy: 驼峰策略 - 单词首字母大写其它小写，不添加连接符；即老版本中的 PascalCaseStrategy
         * PropertyNamingStrategy: LOWER_CAMEL_CASE - 第一个单词首字母小写,其它单词首字母大写
         */
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(Date.class, new DateDeserializers.DateDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
            .indentOutput(true)
            .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
            .modulesToInstall(new ParameterNamesModule());
        converters.add(new StringHttpMessageConverter(Charset.forName(CHARSET)));
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(builder.build());
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper());
        converters.add(mappingJackson2HttpMessageConverter);
        converters.add(new MappingJackson2XmlHttpMessageConverter(Jackson2ObjectMapperBuilder.xml().build()));
        // converters.add(new MappingJackson2JsonHttpMessageConverter(Jackson2ObjectMapperBuilder.json().build()));
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
        // configurer.enable("defaultServletName");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 不检查Accept请求头
        configurer.ignoreAcceptHeader(true)
            // 是否通过请求Url的扩展名来决定media type
            .favorPathExtension(false)
            .favorParameter(true)
            .parameterName("format")
            // 请求以 .json 结尾的会被当成 MediaType.APPLICATION_JSON
            .mediaType("json", MediaType.APPLICATION_JSON_UTF8)
            // 请求以 .xml 结尾的会被当成 MediaType.APPLICATION_XML
            .mediaType("xml", MediaType.APPLICATION_XML)
            /// 请求以 .html 结尾的会被当成 MediaType.TEXT_HTML
            // .mediaType("html", MediaType.TEXT_HTML)
            // 设置默认的media type
            .defaultContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
        /// add view controllers
        // registry.addViewController("/error").setViewName("error.html");
        // registry.addViewController("/login").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        /// order
        // registry.order(Ordered.LOWEST_PRECEDENCE);

        /// for appliation/xml
        // MarshallingView marshallingView = new MarshallingView();
        // marshallingView.setMarshaller(new XStreamMarshaller());

        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        mappingJackson2JsonView.setObjectMapper(objectMapper());
        registry.enableContentNegotiation(
            // for applications/json
            mappingJackson2JsonView,
            // for applications/xml marshallingView
            new MappingJackson2XmlView()
            // new BeanNameViewResolver()
        );

        // beanName
        // registry.viewResolver(new BeanNameViewResolver());

        /// for jsp
        /*
        registry.jsp("/resources/jsp", ".jsp");
        registry.jsp()
                .viewClass(JstlView.class)
                .prefix("/resources/jsp")
                .suffix(".jsp");
        */
    }

///
//    @Bean
//    public ViewResolver getViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".html");
//        return resolver;
//    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/unauthorized");
        /// properties.setProperty("org.springframework.controllers.multipart.MaxUploadSizeExceededException", "/error");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        exceptionResolvers.add(simpleMappingExceptionResolver);
///
//        exceptionResolvers.add(new HandlerExceptionResolver() {
//            @Nullable
//            @Override
//            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
//                logger.error(ex.getMessage(), ex);
//
//                response.setCharacterEncoding("UTF-8");
//                response.setHeader("Content-type", "applications/json;charset=UTF-8");
//                response.setStatus(200);
//                PrintWriter writer = null;
//                try {
//                    writer = response.getWriter();
//                    response.getWriter().write("error! " + ex.getMessage());
//                } catch (IOException e) {
//                    logger.error(e.getMessage());
//                } finally {
//                    if (writer != null) {
//                        writer.close();
//                    }
//                }
//                return new ModelAndView();
//            }
//        });

    }

///
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins(CorsConfiguration.ALL)
//                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS");
//    }
//
//    // response.setCharacterEncoding("UTF-8");
//    // response.setHeader("Content-type", "applications/json;charset=UTF-8");
//

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /// TODO
        /*
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        */

        /// add resource handler
        // registry.addResourceHandler("/resource/**").addResourceLocations("/WEB-INF/static/");
        // registry.addResourceHandler("/resources/").addResourceLocations("classpath:/resources/**");
        // registry.addResourceHandler("/**").addResourceLocations("classpath:/static/**");

        if (!registry.hasMappingForPattern(PATH_PATTERN)) {
            registry.addResourceHandler(PATH_PATTERN).addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        }
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping("/*").allowedOrigins("*");
    }
}

///
/*
// 全局 BindException 错误处理器
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public ResponseEntity beanValidation(BindException exception){
        final List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(msg -> messageSource.getMessage(msg,null,null))
                .collect(toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ImmutableMap.of("errors",errors));
    }
}

// 实现 Spring 的 Validator
@Component
public class ValidSampleValidator implements Validator {

    public boolean supports(Class<?> c) {
        return ValidSampleForm.class.isAssignableFrom(c);
    }

    @Override
    public void validate(Object paramObject, Errors paramErrors) {
        if (paramErrors.hasFieldErrors("from") || paramErrors.hasFieldErrors("to")) {
            return;
        }

        ValidSampleForm form = (ValidSampleForm)paramObject;
        Date from = form.getFrom();
        Date to = form.getTo();
        if (from != null && to != null && from.compareTo(to) > 0) {
            paramErrors.rejectValue("from", "validator.Period");
        }
    }
}

// Controller
// BindingResult result
if (result.hasErrors()) {
    for(FieldError err: result.getFieldErrors()) {
        log.debug("error code = [" + err.getCode() + "]");
    }
}
*/

///
//// IndexServlet
//@WebServlet(name = "IndexServlet",urlPatterns = "/hello")
//public class IndexServlet extends HttpServlet {
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().print("hello word");
//        resp.getWriter().flush();
//        resp.getWriter().close();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doGet(req, resp);
//    }
//}
//
//// IndexFilter
//@WebFilter(urlPatterns = "/*", filterName = "indexFilter")
//public class IndexFilter implements Filter {
//    Log log = LogFactory.getLog(IndexFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("init IndexFilter");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.info("doFilter IndexFilter");
//        filterChain.doFilter(servletRequest,servletResponse);
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
//
//// IndexListener
//@WebListener
//public class IndexListener implements ServletContextListener {
//    private Log log = LogFactory.getLog(IndexListener.class);
//
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        log.info("IndexListener contextInitialized");
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//
//    }
//}
//
//@SpringBootApplication
//@ServletComponentScan
//public class AppApplication {
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(AppApplication.class, args);
//    }
//
//}
