package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "singer_and_song")
public class Singer_And_song {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @ManyToOne
    private Song song;

    @ManyToOne
    private Singer singer;
}
