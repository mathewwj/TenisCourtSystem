package com.jakuboc.TennisCourtSystem.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="USERS")
public class User {
    @Id
    @Column(name = "phone_number")
    String phoneNumber;
    String name;
    String surname;
}
