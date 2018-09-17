/*
 * Copyright 2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.api.kafka.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.strimzi.crdgenerator.annotations.Description;
import io.sundr.builder.annotations.Buildable;
import java.util.Map;

@Buildable(
        editableEnabled = false,
        generateBuilderPackage = false,
        builderPackage = "io.fabric8.kubernetes.api.builder"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaMirrorMakerConsumerSpec extends KafkaMirrorMakerClientSpec {
    private static final long serialVersionUID = 1L;

    public static final String FORBIDDEN_PREFIXES = "ssl., bootstrap.servers, group.id";

    private int numStreams;

    private String groupId;

    // Is there any less dumb way how to override final field?
    @Override
    @Description("The mirror maker client config. Properties with the following prefixes cannot be set: " + FORBIDDEN_PREFIXES)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Map<String, Object> getConfig() {
        return super.getConfig();
    }

    @Description("Specifies the number of consumer stream threads to create.")
    @JsonProperty(required = true)
    public int getNumStreams() {
        return numStreams;
    }

    public void setNumStreams(int numStreams) {
        this.numStreams = numStreams;
    }

    @Description("A unique string that identifies the consumer group this consumer belongs to.")
    @JsonProperty(required = true)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
