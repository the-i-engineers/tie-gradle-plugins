package ch.tie.gradle.plugins.json2md.model;


import org.apache.commons.lang3.StringUtils;

import ch.tie.gradle.plugins.json2md.Json2mdConverterUtil;

public class Deprecation implements ToMarkdown {

  private String level = "";
  private String reason = "";
  private String replacement = "";

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getReplacement() {
    return replacement;
  }

  public void setReplacement(String replacement) {
    this.replacement = replacement;
  }

  @Override
  public String toMarkdown() {
    return Json2mdConverterUtil.text(formatProperty(level), formatProperty(reason), formatProperty(replacement));
  }

  private String formatProperty(String value) {
    if (StringUtils.isNotBlank(value)) {
      return value;
    }
    return "";
  }
}
