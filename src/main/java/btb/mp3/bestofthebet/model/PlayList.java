package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "playlist")
public class PlayList {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (nullable = false)
    private Long id;

    @Column (nullable = false)
    private String name;

    private Long views;
    private Long likes;

    @Column (nullable = false)
    private String image;

    @Column (nullable = false)
    private Date createDate;
}
