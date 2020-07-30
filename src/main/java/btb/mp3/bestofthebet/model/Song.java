package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "song")
public class Song {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @Column (nullable = false)
    private String name;

    private Long likes;
    private Long views;

    @Column (nullable = false)
    private Date creatDate;

    private String songLink;
    @Column (nullable = false)
    private String songImage;

    private String songAuthor;

    @Lob
    private String description;

    @Column (nullable = false)
    private boolean status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;
}
