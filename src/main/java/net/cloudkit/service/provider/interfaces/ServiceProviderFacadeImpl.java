package net.cloudkit.service.provider.interfaces;

import net.cloudkit.service.provider.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.xml.ws.Holder;


/**
 * Service Implementation Bean
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018-05-16 10:17 AM
 */
@WebService(endpointInterface = "net.cloudkit.service.provider.interfaces.ServiceProviderFacade")
public class ServiceProviderFacadeImpl implements ServiceProviderFacade {

    private Logger logger = LoggerFactory.getLogger(ServiceProviderFacadeImpl.class);

    @Autowired
    private TestService testService;

    @Override
    public byte[] service(
        final String serviceName,
        final byte[] requestContext,
        final byte[] requestData,
        Holder<byte[]> responseData
    ) {
        responseData.value = "test".getBytes();
        testService.sayHello(serviceName);
        return "test".getBytes();
    }

}
