package com.pixelheartsoftware.usermanager.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Table(name = "USER_ACCOUNT")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "from")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Length(min = 5, max = 16)
    @NotNull
    @Column(unique = true)
    private String username;

    private String password;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @Length(max = 50)
    private String firstName;

    @Length(max = 50)
    private String lastName;

    private LocalDate birthDate;
}
