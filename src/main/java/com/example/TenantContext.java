package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TenantContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantContext.class);

    private TenantContext() {}

    private static final ThreadLocal<String> CURRENT_TENANT = new InheritableThreadLocal<>();

    public static void setTenant(String tenant) {
        LOGGER.info("setting tenant to {}", tenant);
        CURRENT_TENANT.set(tenant);
    }

    public static String getTenant() {
        return CURRENT_TENANT.get();
    }

    public static void clear(){
        CURRENT_TENANT.remove();
    }
}
