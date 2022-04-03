package com.purang.manifest.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class CommonConfig {

    @Value("${login.context}")
    private String loginContext;

    @Value("${access.secret}")
    private String accessSecret;

    @Value("${track-server.url}")
    private String trackServer;

    @Value("${proxy.kafka.topicGroupId}")
    private String topicGroupId;

    @Value("${proxy.kafka.writeResultTopic}")
    private String writeResultTopic;

    @Value("${proxy.kafka.zkPath}")
    private String zkPath;

    @Value("${proxy.kafka.billNotifyTopic}")
    private String billNotifyTopic;

    @Value("${spring.application.name:unknown}")
    private String domain;
/*

    @Value("${cat.servers}")
    private String servers;
*/

    @Value("${uniqueId.index}")
    private String uniqueIdIndex;
}
