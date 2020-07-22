package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "song")
public class Song {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (nullable = false)
    private Long id;

    @Column (nullable = false)
    private String name;

    private Long likes;
    private Long views;

    @Column (nullable = false)
    private Date creatDate;

    private String songImage;
    private String songAuthor;
    private String description;
    private boolean status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;
}
