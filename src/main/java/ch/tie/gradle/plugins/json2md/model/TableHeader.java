package ch.tie.gradle.plugins.json2md.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum TableHeader {
  // @formatter:off
  SOURCE("source"),
  NAME("name"),
  TYPE("type"),
  DESCRIPTION("description"),
  DEFAULT_VALUE("defaultValue"),
  DEPRECATION("deprecation");
  // @formatter:on

  private final String description;

  TableHeader(String description) {
    this.description = description;
  }

  public String description() {
    return description;
  }

  public static final Set<TableHeader> DEFAULT_HEADERS = Set.of(TableHeader.NAME, TableHeader.TYPE,
      TableHeader.DESCRIPTION, TableHeader.DEFAULT_VALUE);

  // @formatter:off
  public static final List<TableHeader> ALL_HEADERS = List.of(
      TableHeader.NAME,
      TableHeader.TYPE,
      TableHeader.DESCRIPTION,
      TableHeader.DEFAULT_VALUE,
      TableHeader.DEPRECATION,
      TableHeader.SOURCE
  );
  // @formatter:on

  public static List<TableHeader> tableHeaders(Set<TableHeader> includedHeaders) {
    return TableHeader.ALL_HEADERS.stream()
        .filter(header -> includedHeaders.stream().anyMatch(included -> included.equals(header)))
        .collect(Collectors.toList());
  }
}
