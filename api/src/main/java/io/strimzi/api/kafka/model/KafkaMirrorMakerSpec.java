/*
 * Copyright 2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.api.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.strimzi.crdgenerator.annotations.Description;
import io.strimzi.crdgenerator.annotations.Minimum;
import io.sundr.builder.annotations.Buildable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Buildable(
        editableEnabled = false,
        generateBuilderPackage = false,
        builderPackage = "io.fabric8.kubernetes.api.builder"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"replicas", "whitelist", "blacklist", "consumer", "producer", "metrics"})
public class KafkaMirrorMakerSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    private int replicas;

    private String whitelist;

    private String blacklist;

    private KafkaMirrorMakerConsumer consumer;

    private KafkaMirrorMakerProducer producer;

    private Map<String, Object> metrics = new HashMap<>(0);

    @Description("The number of pods in the cluster.")
    @Minimum(1)
    @JsonProperty(required = true)
    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    @Description("List of topics which are included for mirroring. This option allows any regular expression using Java-style regular expressions. If you use this option do not use blacklist one.")
    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }

    @Description("List of topics which are excluded from mirroring. This option allows any regular expression using Java-style regular expressions. If you use this option do not use whitelist one.")
    public String getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }

    @Description("Configuration of target cluster.")
    public KafkaMirrorMakerConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(KafkaMirrorMakerConsumer consumer) {
        this.consumer = consumer;
    }

    @Description("Configuration of source cluster.")
    public KafkaMirrorMakerProducer getProducer() {
        return producer;
    }

    public void setProducer(KafkaMirrorMakerProducer producer) {
        this.producer = producer;
    }


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Description("The Prometheus JMX Exporter configuration. " +
            "See https://github.com/prometheus/jmx_exporter for details of the structure of this configuration.")
    public Map<String, Object> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Object> metrics) {
        this.metrics = metrics;
    }
}
