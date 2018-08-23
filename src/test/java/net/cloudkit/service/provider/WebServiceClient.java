package net.cloudkit.service.provider;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.ws.Holder;

/**
 * WebServiceClient
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018/8/22 9:34
 */
public class WebServiceClient {

    public static void main(String args[]) throws Exception {

        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient(
            "http://localhost:8080/services/ServiceProviderFacade?wsdl"
        );

        // Service name
        String serviceName = "TestService";
        // Request context
        byte[] requestContext = "requestContext".getBytes();
        // Request data
        byte[] requestData = "requestData".getBytes();
        // Response data
        Holder<byte[]> responseData = new Holder<>("".getBytes());

        Object[] objects = client.invoke(
            "service", serviceName, requestContext, requestData, responseData
        );
        // 输出调用结果
        System.out.println("results: " + new String((byte[]) objects[0]));
    }
}
