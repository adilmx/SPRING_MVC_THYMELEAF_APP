package com.adilmx.spring_mvc_app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }
}
