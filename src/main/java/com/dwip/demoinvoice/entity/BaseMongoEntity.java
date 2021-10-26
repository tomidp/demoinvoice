package com.dwip.demoinvoice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Date;

public class BaseMongoEntity implements Serializable {
    public static final String UPDATED_DATE = "UPDATED_DATE";

    public static final String CREATED_DATE = "CREATED_DATE";

    public static final String VERSION = "version";

    public static final String STORE_ID = "STORE_ID";

    private static final long serialVersionUID = 1L;

    public static final String CREATED_BY = "CREATED_BY";

    public static final String UPDATED_BY = "UPDATED_BY";


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Version
    @Field(value = BaseMongoEntity.VERSION)
    private Long version;

    @CreatedDate
    @Field(value = BaseMongoEntity.CREATED_DATE)
    private Date createdDate;

    @CreatedBy
    @Field(value = BaseMongoEntity.CREATED_BY)
    private String createdBy;

    @LastModifiedDate
    @Field(value = BaseMongoEntity.UPDATED_DATE)
    private Date updatedDate;

    @LastModifiedBy
    @Field(value = BaseMongoEntity.UPDATED_BY)
    private String updatedBy;

    @Field(value = BaseMongoEntity.STORE_ID)
    private String storeId;
}
