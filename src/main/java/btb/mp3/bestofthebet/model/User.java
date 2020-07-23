package btb.mp3.bestofthebet.model;

/*import lombok.Data;*/

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
/*@Data*/
@Table (name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames="username"),
                @UniqueConstraint(columnNames="email")
        }
)
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @Column (nullable = false)
/*    @NotBlank*/
    @Size(min = 6, max = 30)
    private String username;

/*    @Column (nullable = false)*/
/*    @NotBlank*/
    @Size(min = 60, max = 120)
    @Column(nullable = false)
    private String password;

    @Column (nullable = false, name="full_name")
    private String full_name;

    @Column (nullable = false, name="phone_number")
    private String phone_number;

    @Column (nullable = false)
    @Email
    @Size(max = 50)
    private String email;

    @Column (nullable = false)
    private boolean status;

    @Column (nullable = true)
    private Date birthday;

    @Column (nullable = false)
    private Date createDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<Role>();

    public User() {}

    public User(String phone_number, String full_name, @NotBlank @Size(min = 6, max = 30) String username, @NotBlank @Size(max = 120) String password, @NotBlank @Email @Size(max = 50) String email, boolean status, Date birthday, Date createDate) {
        this.username = username;
        this.full_name = full_name;
        this.password = password;
        this.phone_number = phone_number;
        this.email = email;
        this.status = status;
        this.birthday = birthday;
        this.createDate = createDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
