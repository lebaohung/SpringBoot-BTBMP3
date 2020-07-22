package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "comment_singer")
public class Comment_Singer {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Singer singer;
}
