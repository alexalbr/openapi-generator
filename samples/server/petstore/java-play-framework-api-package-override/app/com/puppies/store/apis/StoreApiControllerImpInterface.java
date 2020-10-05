package com.puppies.store.apis;

import java.util.Map;
import apimodels.Order;

import play.mvc.Http;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.constraints.*;

@SuppressWarnings("RedundantThrows")
public interface StoreApiControllerImpInterface {
    default Result deleteOrderHttp(Http.Request request, String orderId) throws Exception {
        deleteOrder(request, orderId);
        return ok();
    }

    void deleteOrder(Http.Request request, String orderId) throws Exception;

    default Result getInventoryHttp(Http.Request request) throws Exception {
        Map<String, Integer> obj = getInventory(request);
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    Map<String, Integer> getInventory(Http.Request request) throws Exception;

    default Result getOrderByIdHttp(Http.Request request,  @Min(1) @Max(5)Long orderId) throws Exception {
        Order obj = getOrderById(request, orderId);
        if (configuration.getBoolean("useOutputBeanValidation")) {
                OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    Order getOrderById(Http.Request request,  @Min(1) @Max(5)Long orderId) throws Exception;

    default Result placeOrderHttp(Http.Request request, Order body) throws Exception {
        Order obj = placeOrder(request, body);
        if (configuration.getBoolean("useOutputBeanValidation")) {
                OpenAPIUtils.validate(obj);
        }
        JsonNode result = mapper.valueToTree(obj);
        return ok(result);
    }

    Order placeOrder(Http.Request request, Order body) throws Exception;

}
