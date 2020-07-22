package btb.mp3.bestofthebet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = "user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (nullable = false)
    private Long id;

    @Column (nullable = false)
    private String username;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false)
    private String fullName;

    @Column (nullable = true)
    private Long phoneNumber;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private boolean status;

    @Column (nullable = true)
    private Date birthday;

    @Column (nullable = false)
    private Date createDate;

    @ManyToOne
    private Role role;

    //    @ManyToMany
//    @JoinTable(name = "developer_project",
//            joinColumns = @JoinColumn(name = "developer_id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id"))
//
//    private Collection <Role> roles;
}
