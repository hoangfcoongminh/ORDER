package com.btl.n4j.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "categoryName", length = 50)
    private String categoryName;

    @Column(name = "detail", length = 100)
    private String detail;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<FieldType> fieldTypes;

    public Category() {
    }

    public Category(Integer categoryId, String categoryName, String detail, List<FieldType> fieldTypes) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.detail = detail;
        this.fieldTypes = fieldTypes;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<FieldType> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(List<FieldType> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }
}
