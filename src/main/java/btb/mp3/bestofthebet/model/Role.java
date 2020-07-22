package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "role")
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private Long id;

    @Column (nullable = false, length = 10)
    private String roleName;


}
