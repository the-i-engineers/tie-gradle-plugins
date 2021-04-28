package ch.tie.gradle.plugins.json2md;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;

public class Json2mdTask extends DefaultTask {

  private final Project project;
  private final Json2mdReader json2mdReader;
  private final Json2mdWriter json2mdWriter;

  private String metadataPath;

  public String getMarkdownFilename() {
    return markdownFilename;
  }

  public void setMarkdownFilename(String markdownPath) {
    this.markdownFilename = markdownPath;
  }

  private String markdownFilename;

  public String getMetadataPath() {
    return metadataPath;
  }

  public void setMetadataPath(String metadataPath) {
    this.metadataPath = metadataPath;
  }

  @Inject
  public Json2mdTask(Project project, Json2mdReader json2mdReader, Json2mdWriter json2mdWriter) {
    this.project = project;
    this.markdownFilename = String.format("%s-Documentation.md", project.getName());
    this.metadataPath =
        project.getBuildDir().getPath() + "/classes/java/main/META-INF/spring-configuration-metadata.json";
    this.json2mdReader = json2mdReader;
    this.json2mdWriter = json2mdWriter;
  }

  @TaskAction
  public void json2mdTask() {
    SpringConfigurationMetadata springConfigurationMetadata = json2mdReader.readMetadata(metadataPath);
    String markdown = Json2mdConverterUtil.metadata(springConfigurationMetadata, project.getName());
    json2mdWriter.writeMarkdownFile(markdownFilename, markdown);
  }
}
