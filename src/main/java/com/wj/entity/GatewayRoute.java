package com.wj.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * @author jun.wang
 * @title: GatewayRoute
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/5/8 13:58
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "gateway_route")
@DynamicInsert
public class GatewayRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "predicates")
    private String predicates;

    @Column(name = "uri")
    private String uri;

    @Column(name = "filters")
    private String filters;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;

    public List<PredicateDefinition> getPredicateDefinition() {
        if (this.predicates != null) {
            return JSONArray.parseArray(this.predicates, PredicateDefinition.class);
        } else {
            return Collections.emptyList();
        }
    }

    public List<FilterDefinition> getFilterDefinition() {
        if (this.filters != null) {
            return JSON.parseArray(this.filters, FilterDefinition.class);
        } else {
            return Collections.emptyList();
        }
    }
}
