package net.cloudkit.service.provider.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Holder;

/**
 * ServiceProviderFacade
 * 通过annotation方式实现webservice，targetNamespace属性定义了自己的命名空间，
 * serviceName则定义了< definitions >标签和<service>标签的name属性
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2018-05-16 10:17 AM
 */
// Service Endpoint Interface
@WebService()
// @WebService(name = "ServiceProviderFacade", targetNamespace = "http://interfaces.open.cloudkit.net/services", serviceName = "ServiceProviderFacade")
// SOAP绑定方式，默认是document wrapped方式
// @SOAPBinding(style = SOAPBinding.Style.RPC)
// 1.Document Wrapped：
// @SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use=SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
// 2.Document Bare：
// @SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use=SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.BARE)
// 3.RPC：
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.ENCODED, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
// @SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface ServiceProviderFacade {

    /**
     * service
     *
     * @param serviceName the serviceName
     * @param requestContext the requestContext
     * @param requestData the requestData
     * @param responseData the responseData
     * @return results
     */
    @WebMethod(action = "service", operationName = "service", exclude = false)
    @WebResult(name = "result")
    byte[] service(
        @WebParam(name = "serviceName") String serviceName,
        @WebParam(name = "requestContext") byte[] requestContext,
        @WebParam(name = "requestData") byte[] requestData,
        @WebParam(name = "responseData", mode = WebParam.Mode.OUT) Holder<byte[]> responseData
    );

}
