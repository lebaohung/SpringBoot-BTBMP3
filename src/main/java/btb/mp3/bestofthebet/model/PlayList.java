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

    private Long id;
    private String name;
    private Long views;
    private Long likes;
    private Date createDate;
}
