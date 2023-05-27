package com.example.springbootbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tables")
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacity;

    private String description;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToOne(mappedBy = "table")
//    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonIgnore
    private Order order;
}
