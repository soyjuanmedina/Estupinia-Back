package com.columns.tools;

import java.util.Collection;
import com.fasterxml.jackson.databind.annotation.JsonSerialize; 
import java.util.Collections;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonSerialize
public class RestResponse<T> {
  @XmlElement(name = "METADATA")
  private JsonMetadata  jsonMetadata;
  private Collection<T> result;

  public RestResponse() {
    jsonMetadata = new JsonMetadata();
  }

  public RestResponse(JsonMetadata metadata) {
    this.jsonMetadata = metadata;
  }

  public JsonMetadata getJsonMetadata() {
    return jsonMetadata;
  }

  public void setJsonMetadata(JsonMetadata jsonMetadata) {
    this.jsonMetadata = jsonMetadata;
  }

  public Collection<T> getResult() {
    return result;
  }

  public void setResult(Collection<T> result) {
    this.result = result;
  }

  public void setObjectList(Collection<T> objectList) {
    if (objectList != null) {
      this.result = objectList;  
    }    
  }

  public void setObject(T object) {
    if (object != null) {
      setObjectList(Collections.singletonList(object));
    }
  }
  
  public void setErrorMessage(String msg) {
    jsonMetadata.setErrorMessage(msg);
  }
  
  public void setWarnMessage(String msg) {
    jsonMetadata.setWarnMessage(msg);
  }
  
}
