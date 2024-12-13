package com.btl.n4j.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "fieldtype")
public class FieldType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fieldtype_id")
    private Integer fieldTypeId;

    @Column(name = "typeName", length = 50)
    private String typeName;

    @Column(name = "detail", length = 100)
    private String detail;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "fieldType", cascade = CascadeType.ALL)
    private List<Field> fields;

    public FieldType() {
    }

    public FieldType(Integer fieldTypeId, String typeName, String detail, Category category, List<Field> fields) {
        this.fieldTypeId = fieldTypeId;
        this.typeName = typeName;
        this.detail = detail;
        this.category = category;
        this.fields = fields;
    }

    public Integer getFieldTypeId() {
        return fieldTypeId;
    }

    public void setFieldTypeId(Integer fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
