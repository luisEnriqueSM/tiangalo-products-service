package com.tiangalo.products.domain.model;

public class Category {

    private final CategoryId id;
    private String name;

    private Category(CategoryId id, String name) {
        validateRequiredText(name, "name");
        requireNonNull(id, "CategoryId");
        this.id = id;
        this.name = name;
    }

    public static Category create(String name){
        return new Category(CategoryId.newId(), name);
    }

    private static void validateRequiredText(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException(fieldName + " is required");
        }
    }

    private static void requireNonNull(Object value, String filedName){
        if(value == null){
            throw new IllegalArgumentException(filedName + " is required");
        }
    }
    public CategoryId getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}