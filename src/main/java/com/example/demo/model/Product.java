package com.example.demo.model;

import com.sun.istack.NotNull;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @GeneratedValue
    @Id
    @NotNull
    private Integer id;
    private Integer price;
    private String name;
    private Integer quantity;
}
