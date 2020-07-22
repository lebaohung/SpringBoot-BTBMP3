package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "comment_playlist")
public class Comment_Playlist {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (nullable = false)
    private Long id;

    @Column (nullable = false)
    private String content;

    @Column (nullable = false)
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private PlayList playlist;
}
