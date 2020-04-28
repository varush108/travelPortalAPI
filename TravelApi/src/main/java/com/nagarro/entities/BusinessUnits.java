package com.nagarro.entities;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "businessUnits")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@ToString
public class BusinessUnits {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
}
