package com.example.nvclothes.nvclothes.entity;

import com.example.nvclothes.nvclothes.model.Size;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trainers", schema = "nvclothes")
public class Trainers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long trainersId;

    @Column
    private String attribute;

    @Column
    private String value;

    private String brand;

    private String name;

    private Long cost;

    private Size size;

    private Long amount;
}
