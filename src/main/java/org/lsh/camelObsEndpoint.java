package org.lsh;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

/**
 * Represents a camelObs endpoint.
 */
@UriEndpoint(firstVersion = "1.0-SNAPSHOT", scheme = "obs", title = "camelObs", syntax="obs:name", 
             consumerClass = camelObsConsumer.class, label = "custom")
public class camelObsEndpoint extends DefaultEndpoint {
    @UriPath
    @Metadata(required = "true")
    private String endPoint;

    @UriPath
    @Metadata(required = "true")
    private String bucketName;

    @UriParam(defaultValue = "")
    private String ak;

    @UriParam(defaultValue = "")
    private String sk;

    @UriParam(defaultValue = "false")
    private boolean autoCreateBucket;

    public camelObsEndpoint() {
    }

    public camelObsEndpoint(String uri, camelObsComponent component) {
        super(uri, component);
    }

    public Producer createProducer() throws Exception {
        return new camelObsProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new camelObsConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

    /**
     * 是否自动创建桶
     */
    public void setAutoCreateBucket (boolean autoCreateBucket) {
        this.autoCreateBucket = autoCreateBucket;
    }

    public boolean getAutoCreateBucket () {
        return autoCreateBucket;
    }

    /**
     * 地址
     */
    public void setEndPoint (String name) {
        this.endPoint = name;
    }

    public String getEndPoint () {
        return endPoint;
    }

    /**
     * ak
     */
    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getAk() {
        return ak;
    }

    /**
     * sk
     */
    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getSk() {
        return ak;
    }

    /**
     * bucketName
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }


    private ObsClient obsClient;

    public ObsClient getObsClient(){
        return obsClient;
    }

    public void initObsClient() {
        // 创建ObsClient实例
        obsClient = new ObsClient(ak, sk, endPoint);
    }
}
