package com.itg.itgkeycloak.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class KeyCloakConfiguration {
    private String resourceId;
    private String principleAttribute;
    public String getResourceId() {
        return resourceId;
    }
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
    public String getPrincipleAttribute() {
        return principleAttribute;
    }
    public void setPrincipleAttribute(String principleAttribute) {
        this.principleAttribute = principleAttribute;
    }


}