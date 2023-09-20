
package com.example.db;

import com.example.TenantContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TenantListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantListener.class);

    @PreUpdate
    @PreRemove
    @PrePersist
    public void setTenant(TenantAware entity) {
        String tenant = TenantContext.getTenant();
        LOGGER.info("enrich entity with tenant {}", tenant);
        entity.setTenant(tenant);
    }
}