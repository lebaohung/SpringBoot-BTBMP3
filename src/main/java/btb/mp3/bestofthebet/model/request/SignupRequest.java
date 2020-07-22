package btb.mp3.bestofthebet.model.request;

/*import btb.mp3.bestofthebet.model.Role;*/

import javax.persistence.Column;
/*import javax.persistence.ManyToOne;*/
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class SignupRequest {

/*    @NotBlank*/
    @Size(min = 6, max = 30)
    private String username;

/*    @NotBlank*/
    @Size(min = 6, max = 120)
    private String password;

/*    @NotBlank*/
    @Size(max = 100)
    private String full_name;

    private String phone_number;

/*    @NotBlank*/
    @Size(max = 50)
    @Email
    private String email;

/*    @NotBlank*/
    private boolean status;

/*    @NotBlank*/
    private Date birthday;

    @Column(nullable = false)
    private Date createDate;

    private Set<String> roles;

    public SignupRequest() {}

    public SignupRequest(@NotBlank @Size(min = 6, max = 30) String username, @NotBlank @Size(min = 6, max = 120) String password, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 100) String full_name, String phone_number, @NotBlank boolean status, @NotBlank Date birthday, Date createDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.status = status;
        this.birthday = birthday;
        this.createDate = createDate;
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

    public void setRoles(Set<String> roles) {
        this.roles = roles;
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

    public String getFullname() {
        return full_name;
    }

    public void setFullname(String full_name) {
        this.full_name = full_name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phone_number;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRole(Set<String> role) {
        this.roles = roles;
    }
}
