package com.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicles_id")
    private int id;
    @Column(name = "licenses_plate")
    private String licensePlate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_users_id")
    private User user;
}
