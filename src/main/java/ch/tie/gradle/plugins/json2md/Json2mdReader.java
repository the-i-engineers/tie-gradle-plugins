package ch.tie.gradle.plugins.json2md;


import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.gradle.api.Project;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;

public class Json2mdReader {

  private final ObjectMapper objectMapper;
  private final Project project;

  @Inject
  public Json2mdReader(Project project, ObjectMapper objectMapper) {
    this.project = project;
    this.objectMapper = objectMapper;
  }

  public SpringConfigurationMetadata readMetadata(String metadataPath) {
    File file = project.file(metadataPath);
    if (!file.exists()) {
      project.getLogger().warn("No such file found '{}'", file.getAbsolutePath());
      return SpringConfigurationMetadata.noMetadata();
    }
    try {
      return objectMapper.readValue(file,
          SpringConfigurationMetadata.class);
    } catch (IOException e) {
      project.getLogger().warn(e.getMessage(), e);
      return SpringConfigurationMetadata.noMetadata();
    }
  }
}
