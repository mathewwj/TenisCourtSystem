package com.jakuboc.TennisCourtSystem.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="users")
public class User {
    @Id
    String phoneNumber;

    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="user_id_seq")
    String name;
    String surname;
}
