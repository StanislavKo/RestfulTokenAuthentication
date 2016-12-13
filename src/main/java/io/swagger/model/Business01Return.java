package io.swagger.model;




import io.swagger.annotations.*;
import java.util.Objects;


public class Business01Return   {
  
  private String response01 = null;

  /**
   **/
  public Business01Return response01(String response01) {
    this.response01 = response01;
    return this;
  }

  
  @ApiModelProperty(example = "null", value = "")
  public String getResponse01() {
    return response01;
  }
  public void setResponse01(String response01) {
    this.response01 = response01;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Business01Return business01Return = (Business01Return) o;
    return Objects.equals(response01, business01Return.response01);
  }

  @Override
  public int hashCode() {
    return Objects.hash(response01);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Business01Return {\n");
    
    sb.append("    response01: ").append(toIndentedString(response01)).append("\n");
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
