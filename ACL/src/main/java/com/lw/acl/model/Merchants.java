package com.lw.acl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 商户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchants")
public class Merchants {

    /**
     * 商户id，主键
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * 商户名称，需要唯一
     * @Basic标识表的基本列
     */
    @Basic
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Basic
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @Basic
    @Column(name = "business_license_url", nullable = false)
    private String businessLicenseUrl;

    /**
     * 商户联系电话
     */
    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * 商户地址
     */
    @Basic
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * 是否审核通过
     */
    @Basic
    @Column(name = "is_audit", nullable = false)
    private Integer isAudit = 0;

}
