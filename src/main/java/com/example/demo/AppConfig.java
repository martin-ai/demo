package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "ts.config")
public class AppConfig {

    public static final String TIME_ZONE = "GMT+8";
    public static final String DEFAULT_ZONE_ID = "Asia/Shanghai";

    public static final String ENV_LOCAL = "local";
    public static final String ENV_LIVE = "live";

    private String env;
    private Integer tmpFileExpiredDays;
    private Map<String, List<String>> typeStockSuggestionRel;
    private boolean extractCallouts;
    private String legendCodeSepChar;

    public Integer getTmpFileExpiredDays() {
        return tmpFileExpiredDays;
    }

    public void setTmpFileExpiredDays(Integer tmpFileExpiredDays) {
        this.tmpFileExpiredDays = tmpFileExpiredDays;
    }

    public Map<String, List<String>> getTypeStockSuggestionRel() {
        return typeStockSuggestionRel;
    }

    public void setTypeStockSuggestionRel(Map<String, List<String>> typeStockSuggestionRel) {
        this.typeStockSuggestionRel = typeStockSuggestionRel;
    }

    public boolean isExtractCallouts() {
        return extractCallouts;
    }

    public void setExtractCallouts(boolean extractCallouts) {
        this.extractCallouts = extractCallouts;
    }

    public String getLegendCodeSepChar() {
        return legendCodeSepChar;
    }

    public void setLegendCodeSepChar(String legendCodeSepChar) {
        this.legendCodeSepChar = legendCodeSepChar;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

}
