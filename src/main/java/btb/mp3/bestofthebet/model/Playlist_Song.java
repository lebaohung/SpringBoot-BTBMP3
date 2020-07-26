package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "playlist_song")
public class Playlist_Song {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @ManyToOne
    private PlayList playlist;

    @ManyToOne
    private Song song;


}
