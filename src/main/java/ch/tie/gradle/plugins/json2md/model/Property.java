package ch.tie.gradle.plugins.json2md.model;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import ch.tie.gradle.plugins.json2md.Json2mdConverterUtil;

public class Property implements ToMarkdown {

  // @formatter:off
  private final Map<TableHeader, Supplier<String>> rowMapper = Map.of(
      TableHeader.NAME, this::getName,
      TableHeader.TYPE, this::getType,
      TableHeader.DESCRIPTION, this::getDescription,
      TableHeader.DEFAULT_VALUE, () -> getDefaultValue().toString(),
      TableHeader.SOURCE, this::getSourceType,
      TableHeader.DEPRECATION, () -> getDeprecation().toMarkdown()
  );
  // @formatter:on

  private String name = "";
  private String type = "";
  private String description = "";
  private String sourceType = "";
  private Object defaultValue = "";
  private Deprecation deprecation = new Deprecation();
  private List<TableHeader> tableHeaders = TableHeader.ALL_HEADERS;

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
    return Json2mdConverterUtil.tableRow(
        tableHeaders.stream().map(rowMapper::get).map(Supplier::get).collect(Collectors.toList()));
  }

  public void setTableHeaders(List<TableHeader> tableHeaders) {
    this.tableHeaders = tableHeaders;
  }
}
