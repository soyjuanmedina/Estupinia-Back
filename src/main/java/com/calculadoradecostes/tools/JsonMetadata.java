package com.calculadoradecostes.tools;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@XmlRootElement
@JsonSerialize
public class JsonMetadata
{
  @XmlElement(name = "STATUS")
  private String status;
  @XmlElement(name = "ERROR_MESSAGE")
  private String errorMessage;

  public JsonMetadata() {
    status = SiteConstants.JSON.RESULT_OK;
    errorMessage = "";
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  @XmlTransient
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    status = SiteConstants.JSON.RESULT_ERROR;
  } 
  
  @XmlTransient
  public void setErrorMessage(String errNumber, String errorMessage) {
    this.errorMessage = errorMessage;
    status = errNumber;
  } 

  @XmlTransient
  public void setWarnMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    status = SiteConstants.JSON.RESULT_WARNING;
  }

  public String getStatus() {
    return status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
 
  @XmlTransient
  public void resetStatus() {
    status = SiteConstants.JSON.RESULT_OK;
    errorMessage = "";
  }
 
}
