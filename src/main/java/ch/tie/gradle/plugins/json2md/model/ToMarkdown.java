package ch.tie.gradle.plugins.json2md.model;

public interface ToMarkdown {

  default String toMarkdown() {
    return "";
  }
}
