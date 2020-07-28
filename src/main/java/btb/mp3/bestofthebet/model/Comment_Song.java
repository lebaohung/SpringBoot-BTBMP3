package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "comment_song")
public class Comment_Song {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @Column (nullable = false)
    private String content;

    @Column (nullable = false)
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Song song;
}
