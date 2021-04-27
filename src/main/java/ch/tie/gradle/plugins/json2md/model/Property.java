package ch.tie.gradle.plugins.json2md.model;

import ch.tie.gradle.plugins.json2md.Json2mdConverterUtil;

public class Property implements ToMarkdown {

  private String name = "";
  private String type = "";
  private String description = "";
  private String sourceType = "";
  private Object defaultValue = "";
  private Deprecation deprecation = new Deprecation();

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

  public Object getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(Object defaultValue) {
    this.defaultValue = defaultValue;
  }

  public Deprecation getDeprecation() {
    return deprecation;
  }

  public void setDeprecation(Deprecation deprecation) {
    this.deprecation = deprecation;
  }

  @Override
  public String toMarkdown() {
    return Json2mdConverterUtil.tableRow(name, type, description, sourceType, defaultValue.toString(),
        deprecation.toMarkdown());
  }

  public static String tableHeader() {
    return Json2mdConverterUtil.tableHeader("name", "type", "description", "sourceType", "defaultValue", "deprecation");
  }
}
