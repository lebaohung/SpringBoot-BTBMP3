package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "category")
public class Category {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private Long id;
    private String name;
}
