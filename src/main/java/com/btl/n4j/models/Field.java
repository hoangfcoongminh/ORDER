package com.btl.n4j.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Integer fieldId;

    @Column(name = "fieldName", length = 100)
    private String fieldName;

    @Column(name = "fieldImage", length = 100)
    private String fieldImage;

    @Column(name = "address", length = 100)
    private String address;

    @ManyToOne
    @JoinColumn(name = "fieldtype_id")
    private FieldType fieldType;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Field_TimeSlot> fieldTimeslots;

    public Field() {
    }

    public Field(Integer fieldId, String fieldName, String fieldImage, String address, FieldType fieldType, List<Field_TimeSlot> fieldTimeslots) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldImage = fieldImage;
        this.address = address;
        this.fieldType = fieldType;
        this.fieldTimeslots = fieldTimeslots;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldImage() {
        return fieldImage;
    }

    public void setFieldImage(String fieldImage) {
        this.fieldImage = fieldImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public List<Field_TimeSlot> getFieldTimeslots() {
        return fieldTimeslots;
    }

    public void setFieldTimeslots(List<Field_TimeSlot> fieldTimeslots) {
        this.fieldTimeslots = fieldTimeslots;
    }
}
