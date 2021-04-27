package ch.tie.gradle.plugins.json2md;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.gradle.api.GradleException;
import org.gradle.api.Project;

public class Json2mdWriter {

  private final Project project;

  @Inject
  public Json2mdWriter(Project project) {
    this.project = project;
  }

  public void writeMarkdownFile(String path, String markdown) {
    File file = project.file(path);
    try {
      FileUtils.write(file, markdown, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new GradleException(e.getMessage());
    }
  }
}
