package btb.mp3.bestofthebet.model.request;

/*import btb.mp3.bestofthebet.model.Role;*/

import javax.persistence.Column;
/*import javax.persistence.ManyToOne;*/
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

public class SignupRequest {

    @NotNull
    @NotBlank
    @Size(min = 6, max = 30)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 120)
    private String password;

    /*    @NotBlank*/
    @Size(max = 100)
    private String full_name;

    @Size(max = 100)
    private String phone_number;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    /*    @NotBlank*/
    private Boolean status;

    /*    @NotBlank*/
    private Date birthday;

    @Column(nullable = false)
    private Timestamp createDate;

    private Set<String> roles;

    /*    public SignupRequest() {}*/

    public SignupRequest(String phone_number, @NotBlank @Size(max = 100) String full_name, @NotNull @NotBlank @Size(min = 6, max = 30) String username, @NotNull @NotBlank @Size(min = 6, max = 120) String password, @NotNull @NotBlank @Size(max = 50) @Email String email, @NotBlank Boolean status, @NotBlank Date birthday, Timestamp createDate) {
        this.username = username;
        this.full_name = full_name;
        this.password = password;
        this.phone_number = phone_number;
        this.email = email;
        this.status = status;
        this.birthday = birthday;
        this.createDate = createDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phoneNumber) {
        this.phone_number = phone_number;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
