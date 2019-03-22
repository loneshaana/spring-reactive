package com.java.reactive.in.mongo.dao.entity;

import com.java.reactive.in.mongo.dao.entity.common.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "blog")
public class Blog extends BaseEntity {

    @TextIndexed
    private String title;


    private String content;

    @Indexed
    private String author;
}
