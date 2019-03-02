package com.example.softomateidentifyerl;

import java.util.HashMap;
import java.util.Map;

public class Language {

    private Double confidence;
    private String language;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}