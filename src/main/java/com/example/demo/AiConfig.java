package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ai.config")
public class AiConfig {

    private String clientId;
    private String clientSecret;
    private String grantType;
    private String aiOauthUrl;
    private String aiNlpUrl;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAiOauthUrl() {
        return aiOauthUrl;
    }

    public void setAiOauthUrl(String aiOauthUrl) {
        this.aiOauthUrl = aiOauthUrl;
    }

    public String getAiNlpUrl() {
        return aiNlpUrl;
    }

    public void setAiNlpUrl(String aiNlpUrl) {
        this.aiNlpUrl = aiNlpUrl;
    }

}
