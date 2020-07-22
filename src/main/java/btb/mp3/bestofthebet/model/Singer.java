package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "singer")
public class Singer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private Date createDate;
}
