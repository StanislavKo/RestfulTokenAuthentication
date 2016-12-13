package io.swagger.model;




import io.swagger.annotations.*;
import java.util.Objects;


public class Signup   {
  
  private String username = null;
  private String password1 = null;
  private String password2 = null;
  private String email = null;

  /**
   **/
  public Signup username(String username) {
    this.username = username;
    return this;
  }

  
  @ApiModelProperty(example = "null", value = "")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   **/
  public Signup password1(String password1) {
    this.password1 = password1;
    return this;
  }

  
  @ApiModelProperty(example = "null", value = "")
  public String getPassword1() {
    return password1;
  }
  public void setPassword1(String password1) {
    this.password1 = password1;
  }

  /**
   **/
  public Signup password2(String password2) {
    this.password2 = password2;
    return this;
  }

  
  @ApiModelProperty(example = "null", value = "")
  public String getPassword2() {
    return password2;
  }
  public void setPassword2(String password2) {
    this.password2 = password2;
  }

  /**
   **/
  public Signup email(String email) {
    this.email = email;
    return this;
  }

  
  @ApiModelProperty(example = "null", value = "")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Signup signup = (Signup) o;
    return Objects.equals(username, signup.username) &&
        Objects.equals(password1, signup.password1) &&
        Objects.equals(password2, signup.password2) &&
        Objects.equals(email, signup.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password1, password2, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Signup {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password1: ").append(toIndentedString(password1)).append("\n");
    sb.append("    password2: ").append(toIndentedString(password2)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
