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

    private Long id;
    private String username;
    private String password;
    private String fullName;
    private Long phoneNumber;
    private String email;
    private boolean status;
    private Date birthday;
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
