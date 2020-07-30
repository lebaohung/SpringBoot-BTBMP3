package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "singer")
public class Singer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;
    private String name;

    @Column (nullable = false)
    private String image;

    @Column (nullable = false)
    private Date createDate;
}
