package ch.tie.gradle.plugins.json2md;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;
import ch.tie.gradle.plugins.json2md.model.TableHeader;

public class Json2mdTask extends DefaultTask {

  private final Project project;
  private final Json2mdReader json2mdReader;
  private final Json2mdWriter json2mdWriter;

  @Input
  private String metadataPath;

  @Input
  private List<String> excludedSources = new ArrayList<>();

  @Inject
  private String outputPath;

  @Input
  private String markdownFilename;

  @Input
  private Set<TableHeader> tableHeaders = TableHeader.DEFAULT_HEADERS;

  public String getMarkdownFilename() {
    return markdownFilename;
  }

  public void setMarkdownFilename(String markdownPath) {
    this.markdownFilename = markdownPath;
  }

  public String getMetadataPath() {
    return metadataPath;
  }

  public void setMetadataPath(String metadataPath) {
    this.metadataPath = metadataPath;
  }

  public List<String> getExcludedSources() {
    return excludedSources;
  }

  public void setExcludedSources(List<String> excludedSources) {
    this.excludedSources = excludedSources;
  }

  public Set<TableHeader> getTableHeaders() {
    return tableHeaders;
  }

  public void setTableHeaders(Set<TableHeader> tableHeaders) {
    this.tableHeaders = tableHeaders;
  }

  public String getOutputPath() {
    return outputPath;
  }

  public void setOutputPath(String outputPath) {
    this.outputPath = outputPath;
  }

  @Inject
  public Json2mdTask(Project project, Json2mdReader json2mdReader, Json2mdWriter json2mdWriter) {
    this.project = project;
    this.markdownFilename = "ConfigurationProperties.md";
    this.outputPath = project.getProjectDir().getPath();
    this.metadataPath =
        project.getLayout().getBuildDirectory().get().getAsFile().getPath() + "/classes/java/main/META-INF/spring-configuration-metadata.json";
    this.json2mdReader = json2mdReader;
    this.json2mdWriter = json2mdWriter;
  }

  @TaskAction
  public void json2mdTask() {
    SpringConfigurationMetadata springConfigurationMetadata = json2mdReader.readMetadata(metadataPath);
    springConfigurationMetadata.setTableHeaders(tableHeaders);
    springConfigurationMetadata.setExcludedSources(excludedSources);
    String markdown = Json2mdConverterUtil.metadata(springConfigurationMetadata, project.getName());
    String output = String.join("/", outputPath, markdownFilename);
    json2mdWriter.writeMarkdownFile(output, markdown);
  }
}
