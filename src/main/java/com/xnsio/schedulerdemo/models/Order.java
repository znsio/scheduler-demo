package com.xnsio.schedulerdemo.models;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @Column(nullable = false)
    private String status;

    public Long getId() {
        return id;
    }
}