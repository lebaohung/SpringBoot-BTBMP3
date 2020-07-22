package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "category")
public class Category {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @Column (nullable = false)
    private String name;
}
