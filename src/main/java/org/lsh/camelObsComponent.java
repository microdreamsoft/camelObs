package org.lsh;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;

import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages {@link camelObsEndpoint}.
 */
public class camelObsComponent extends DefaultComponent {
    
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        camelObsEndpoint endpoint = new camelObsEndpoint(uri, this);
        setProperties(endpoint, parameters);
        endpoint.initObsClient();
        return endpoint;
    }
}
