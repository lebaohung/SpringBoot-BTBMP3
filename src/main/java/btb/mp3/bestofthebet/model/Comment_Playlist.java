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

    private Long id;
    private String content;
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private PlayList playlist;
}
