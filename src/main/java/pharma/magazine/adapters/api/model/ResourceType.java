package pharma.magazine.adapters.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ResourceType {
    @JsonProperty("product")
    PRODUCT,
    @JsonProperty("staff")
    STAFF,
    @JsonProperty("stock")
    STOCK,
    @JsonProperty("store")
    STORE,
    @JsonProperty("unknown")
    UNKNOWN;

    public static ResourceType fromClass(Class<?> clazz) {
        return fromClassName(clazz.getSimpleName());
    }

    public static ResourceType fromClassName(String className) {
        switch (className) {
            case "ProductDto":
                return ResourceType.PRODUCT;
            case "StaffDto":
                return ResourceType.STAFF;
            case "StockDto":
                return ResourceType.STOCK;
            case "StoreDto":
                return ResourceType.STORE;
            default:
                return ResourceType.UNKNOWN;
        }
    }
}
