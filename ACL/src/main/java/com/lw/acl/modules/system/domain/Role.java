package com.lw.acl.modules.system.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable(name = "roles_depts", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "dept_id", referencedColumnName = "id")})
    private Set<Dept> depts;



    public @interface Update {}
}
