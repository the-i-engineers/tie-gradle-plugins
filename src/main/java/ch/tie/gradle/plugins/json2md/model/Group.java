package ch.tie.gradle.plugins.json2md.model;

public class Group {

  private String name;
  private String type;
  private String description;
  private String sourceType;
  private String sourceMethod;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSourceType() {
    return sourceType;
  }

  public void setSourceType(String sourceType) {
    this.sourceType = sourceType;
  }

  public String getSourceMethod() {
    return sourceMethod;
  }

  public void setSourceMethod(String sourceMethod) {
    this.sourceMethod = sourceMethod;
  }
}
