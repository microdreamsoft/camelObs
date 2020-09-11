package org.lsh;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The camelObs producer.
 */
public class camelObsProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(camelObsProducer.class);
    private camelObsEndpoint endpoint;

    public camelObsProducer(camelObsEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        boolean exists = endpoint.getObsClient().headBucket(endpoint.getBucketName());
        if(!exists){
            if(endpoint.getAutoCreateBucket()){

            }else{
                throw new Exception("桶不存在!");
            }
        }


    }

}
