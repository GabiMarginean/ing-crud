package com.ing.api.product;

import java.util.List;

public class ProductAssignWarehouseJsonListRequest {

    private List<ProductAssignWarehouseJsonRequest> assignRequests;

    public List<ProductAssignWarehouseJsonRequest> getAssignRequests() {
        return assignRequests;
    }

    public ProductAssignWarehouseJsonListRequest setAssignRequests(List<ProductAssignWarehouseJsonRequest> assignRequests) {
        this.assignRequests = assignRequests;
        return this;
    }
}
