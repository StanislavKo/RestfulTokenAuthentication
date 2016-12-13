package io.swagger.model;




import io.swagger.annotations.*;
import java.util.Objects;


public class Signin   {
  
  private String username = null;
  private String password = null;

  /**
   **/
  public Signin username(String username) {
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
  public Signin password(String password) {
    this.password = password;
    return this;
  }

  
  @ApiModelProperty(example = "null", value = "")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Signin signin = (Signin) o;
    return Objects.equals(username, signin.username) &&
        Objects.equals(password, signin.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Signin {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
