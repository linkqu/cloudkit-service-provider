package net.cloudkit.service.provider.configurations;

import net.cloudkit.service.provider.interfaces.ServiceProviderFacade;
import net.cloudkit.service.provider.interfaces.ServiceProviderFacadeImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.binding.soap.SoapBindingConfiguration;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

import javax.xml.ws.Endpoint;
import javax.servlet.Servlet;

/**
 * WebServiceConfiguration
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018/8/21 11:08
 */
@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {
///
//    @Bean
//    public SimpleXsdSchema echoXsd() {
//        return new SimpleXsdSchema(new ClassPathResource("echo.xsd"));
//    }
//
//    @Override
//    public void addInterceptors(List<EndpointInterceptor> interceptors) {
//        PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
//        validatingInterceptor.setXsdSchema(echoXsd());
//        validatingInterceptor.setValidateRequest(true);
//        validatingInterceptor.setValidateResponse(true);
//        interceptors.add(validatingInterceptor);
//        interceptors.add(new PayloadLoggingInterceptor());
//    }
//
//    @Bean
//    public DefaultWsdl11Definition echo() {
//        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
//        definition.setPortTypeName("Echo");
//        definition.setLocationUri("http://localhost:8080/echo/services");
//        definition.setSchema(echoXsd());
//        return definition;
//    }

    @Bean
    public ServiceProviderFacade serviceProviderFacade() {
        return new ServiceProviderFacadeImpl();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean<Servlet>(new CXFServlet(), "/services/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), serviceProviderFacade());
        endpoint.publish("/ServiceProviderFacade");
        SoapBindingConfiguration configuration = new SoapBindingConfiguration();
        ///
        // configuration.setStyle("DOCUMENT");
        // configuration.setUse("LITERAL");
        endpoint.setBindingConfig(configuration);
        return endpoint;
    }
}
