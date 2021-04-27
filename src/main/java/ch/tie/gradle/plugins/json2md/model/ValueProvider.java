package ch.tie.gradle.plugins.json2md.model;

public class ValueProvider {

  private String name;
  private Object parameters;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Object getParameters() {
    return parameters;
  }

  public void setParameters(Object parameters) {
    this.parameters = parameters;
  }
}
