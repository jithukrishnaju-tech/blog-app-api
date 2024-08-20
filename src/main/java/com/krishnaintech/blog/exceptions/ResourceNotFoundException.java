package com.krishnaintech.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String resourceType;
    private long resourceValue;

    public ResourceNotFoundException(String resourceName, String resourceType, Integer resourceValue) {
        super(String.format("%s resource is not found with value %s : %s", resourceName, resourceType, resourceValue));
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.resourceValue = resourceValue;
    }
}
