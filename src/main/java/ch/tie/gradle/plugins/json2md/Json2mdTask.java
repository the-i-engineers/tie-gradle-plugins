package ch.tie.gradle.plugins.json2md;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;

public class Json2mdTask extends DefaultTask {

  private final Json2mdReader json2mdReader;
  private final Json2mdWriter json2mdWriter;

  private String metadataPath = "$buildDir/classes/java/main/META-INF/spring-configuration-metadata.json";

  public String getMarkdownPath() {
    return markdownPath;
  }

  public void setMarkdownPath(String markdownPath) {
    this.markdownPath = markdownPath;
  }

  private String markdownPath = "$";

  public String getMetadataPath() {
    return metadataPath;
  }

  public void setMetadataPath(String metadataPath) {
    this.metadataPath = metadataPath;
  }

  @Inject
  public Json2mdTask(Json2mdReader json2mdReader, Json2mdWriter json2mdWriter) {
    this.json2mdReader = json2mdReader;
    this.json2mdWriter = json2mdWriter;
  }

  @TaskAction
  public void json2mdTask() {
    SpringConfigurationMetadata springConfigurationMetadata = json2mdReader.readMetadata(metadataPath);
    String markdown = springConfigurationMetadata.toMarkdown();
    json2mdWriter.writeMarkdownFile(markdownPath, markdown);
  }
}
