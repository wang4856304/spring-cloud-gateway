package com.wj.entity.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
public class GatewayRouteDto {

    private String id;

    @NotBlank(message = "路径为空")
    private String predicates;

    @NotBlank(message = "url为空")
    private String uri;

    private String filters;

    private Integer deleteFlag;

    private String createAt;

    private String updateAt;

}
