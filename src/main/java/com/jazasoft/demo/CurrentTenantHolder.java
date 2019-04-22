package com.jazasoft.demo;

public class CurrentTenantHolder {
    private static final ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    public static String get() {
        return currentTenant.get();
    }

    public static void set(String tenant) {
        currentTenant.set(tenant);
    }

    public static String remove() {
        synchronized (currentTenant) {
            String tenant = currentTenant.get();
            currentTenant.remove();
            return tenant;
        }
    }
}
