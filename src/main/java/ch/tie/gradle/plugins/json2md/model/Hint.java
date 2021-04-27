package ch.tie.gradle.plugins.json2md.model;

import java.util.List;

public class Hint {

  private String name;
  private List<ValueHint> values;
  private List<ValueProvider> providers;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ValueHint> getValues() {
    return values;
  }

  public void setValues(List<ValueHint> values) {
    this.values = values;
  }

  public List<ValueProvider> getProviders() {
    return providers;
  }

  public void setProviders(List<ValueProvider> providers) {
    this.providers = providers;
  }
}
