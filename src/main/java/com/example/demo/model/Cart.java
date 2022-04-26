package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    @NotNull
    private Integer id;
    @OneToOne
    User user;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Product> productList;
}
