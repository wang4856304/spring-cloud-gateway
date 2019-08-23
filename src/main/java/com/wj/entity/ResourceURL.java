package com.wj.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

/**
 * A ResourceURL.
 * @author zz.zhang
 */
@Entity
@Table(name = "s_resource_url")
@Data
@NoArgsConstructor
public class ResourceURL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "resource_id", nullable = false)
    private String resourceId;

    @Column(name = "parent_resource_id", nullable = false)
    private String parentResourceId;

    @Column(name = "resource_type", nullable = false)
    private Integer resourceType;

    @Column(name = "resource_name", length = 40)
    private String resourceName;

    @Column(name = "url", length = 300)
    private String url;

    @Column(name = "method", length = 10)
    private String method;

    @Column(name = "remark", length = 200)
    private String remark;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;
}
