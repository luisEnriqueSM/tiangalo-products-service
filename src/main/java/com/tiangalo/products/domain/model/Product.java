package com.tiangalo.products.domain.model;

public class Product {

    private final ProductId id;
    private String name;
    private String description;
    private CategoryId categoryId;
    private ProductStatus status;

    private Product(ProductId id,
                    String name,
                    String description,
                    CategoryId categoryId,
                    ProductStatus status){
        requireNonNull(id, "ProductId");
        validateRequiredText(name, "name");
        validateRequiredText(description, "description");
        requireNonNull(categoryId, "CategoryId");
        requireNonNull(status, "ProductStatus");
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.status = status;
    }

    public static Product create(String name,
                          String description,
                          CategoryId categoryId){
        return new Product(ProductId.newId(),
                name,
                description,
                categoryId,
                ProductStatus.DRAFT);
    }

    public void rename(String newName){
        validateRequiredText(newName, "name");
        if(newName.equals(this.name)){
            return;
        }
        this.name = newName;
    }

    public void changeDescription(String newDescription){
        validateRequiredText(newDescription, "description");
        if(newDescription.equals(this.description)){
            return;
        }
        this.description = newDescription;
    }

    public void activate(){
        if(this.status == ProductStatus.INACTIVE || this.status == ProductStatus.DRAFT){
            this.status = ProductStatus.ACTIVE;
        }
    }

    public void deactivate(){
        if(this.status == ProductStatus.DRAFT){
            throw new IllegalStateException("Cannot deactivate this product");
        } else if(this.status == ProductStatus.ACTIVE){
            this.status = ProductStatus.INACTIVE;
        }
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

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }

    public ProductStatus getStatus() {
        return status;
    }
}
