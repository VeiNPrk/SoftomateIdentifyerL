package com.example.softomateidentifyerl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Languages {

    private List<Language> languages = null;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
