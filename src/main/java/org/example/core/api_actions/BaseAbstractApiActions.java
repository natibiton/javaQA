package org.example.core.api_actions;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseAbstractApiActions {
    @Getter
    protected String baseEndPoint;

    @Getter
    private Map<String, String> headers;

    public BaseAbstractApiActions(String baseEndPoint) {
        headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Content-Type", "application/json");
        this.baseEndPoint = baseEndPoint;
    }

}
