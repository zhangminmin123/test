package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "java_item", shards = 1, replicas = 0)
public class Item implements Serializable {
    /**
     * Id - 当前的属性，对应Elasticsearch中索引的_id元数据。代表主键。
     *      且，当前属性会在Elasticsearch保存的数据结构中。{"id":"", "title":"", "remark":""}
     */
    @Id
    @Field(name = "id", type = FieldType.Keyword)
    private String id; // 主键
    /**
     * Field - 描述Java类型中的属性的映射
     *  name - 对应的ES索引中的字段名。默认和属性同名。
     *  type - 对应的字段类型，默认是FieldType.Auto。建议定义正确的映射类型。避免可能发生的映射错误。
     *  index - 是否创建索引，text类型创建倒排索引，其他类型创建正排索引。默认true
     */
    @Field(name = "title", type = FieldType.Text, analyzer = "ik_max_word")
    private String title; // 标题
    @Field(name = "remark", type = FieldType.Text, analyzer = "ik_smart")
    private String remark; // 详情
    @Field(name = "price", type = FieldType.Long)
    private Long price; // 单价， 单位是分
    @Field(name = "number", type = FieldType.Integer, index = false)
    private int num; // 库存数量
}