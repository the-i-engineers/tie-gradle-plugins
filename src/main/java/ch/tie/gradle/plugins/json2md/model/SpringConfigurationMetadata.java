package ch.tie.gradle.plugins.json2md.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.tie.gradle.plugins.json2md.Json2mdConverterUtil;

public class SpringConfigurationMetadata implements ToMarkdown {

  private List<Group> groups = new ArrayList<>();
  private List<Property> properties = new ArrayList<>();
  private List<Hint> hints = new ArrayList<>();

  public List<Property> getProperties() {
    return properties;
  }

  public void setProperties(List<Property> properties) {
    this.properties = properties;
  }

  public List<Hint> getHints() {
    return hints;
  }

  public void setHints(List<Hint> hints) {
    this.hints = hints;
  }

  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }

  @Override
  public String toMarkdown() {
    return propertiesTable();
  }

  private String propertiesTable() {
    return Json2mdConverterUtil.h2("Properties") + Property.tableHeader() + properties.stream()
        .map(Property::toMarkdown)
        .collect(Collectors.joining());
  }
}
