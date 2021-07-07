package ch.tie.gradle.plugins.json2md.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ch.tie.gradle.plugins.json2md.Json2mdConverterUtil;

public class SpringConfigurationMetadata implements ToMarkdown {

  private List<Group> groups = new ArrayList<>();
  private List<Property> properties = new ArrayList<>();
  private List<Hint> hints = new ArrayList<>();
  private List<String> excludedSources = new ArrayList<>();
  private Set<TableHeader> tableHeaders = TableHeader.DEFAULT_HEADERS;

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

  public void setExcludedSources(List<String> excludedSources) {
    this.excludedSources = excludedSources;
  }

  @Override
  public String toMarkdown() {
    return propertiesTable();
  }

  private String propertiesTable() {
    List<TableHeader> tableHeaders = TableHeader.tableHeaders(this.tableHeaders);
    return Json2mdConverterUtil.h2("Properties") + Json2mdConverterUtil.tableHeader(tableHeaders) + properties.stream()
        .peek(property -> property.setTableHeaders(tableHeaders))
        .filter(property -> excludedSources.stream()
            .noneMatch(source -> property.getSourceType().toLowerCase().contains(source.toLowerCase())))
        .map(Property::toMarkdown)
        .collect(Collectors.joining());
  }

  public void setTableHeaders(Set<TableHeader> tableHeaders) {
    this.tableHeaders = tableHeaders;
  }
}
