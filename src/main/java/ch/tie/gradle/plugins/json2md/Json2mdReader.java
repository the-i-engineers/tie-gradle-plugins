package ch.tie.gradle.plugins.json2md;


import java.io.File;

import javax.inject.Inject;

import org.gradle.api.Project;

import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

public class Json2mdReader {

  private final JsonMapper jsonMapper;
  private final Project project;

  @Inject
  public Json2mdReader(Project project, JsonMapper jsonMapper) {
    this.project = project;
    this.jsonMapper = jsonMapper;
  }

  public SpringConfigurationMetadata readMetadata(String metadataPath) {
    File file = project.file(metadataPath);
    if (!file.exists()) {
      project.getLogger().warn("No such file found '{}'", file.getAbsolutePath());
      return SpringConfigurationMetadata.noMetadata();
    }
    try {
      return jsonMapper.readValue(file, SpringConfigurationMetadata.class);
    } catch (JacksonException e) {
      project.getLogger().warn(e.getMessage(), e);
      return SpringConfigurationMetadata.noMetadata();
    }
  }
}
