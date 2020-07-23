package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "role")
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="name", nullable = false, length = 20)
    private EnumRole name;
}
