package com.orilinc.portfolio.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = true)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String phone;

}
