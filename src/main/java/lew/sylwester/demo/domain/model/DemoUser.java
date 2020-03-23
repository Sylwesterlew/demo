package lew.sylwester.demo.domain.model;

import lew.sylwester.demo.domain.model.enu.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "DEMO_USERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    @SequenceGenerator(name = "userSequence", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN", unique = true)
    private String login;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

}
