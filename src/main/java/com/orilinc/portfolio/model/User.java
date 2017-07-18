package com.orilinc.portfolio.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @Column(nullable = false)
    private Address address;

}
