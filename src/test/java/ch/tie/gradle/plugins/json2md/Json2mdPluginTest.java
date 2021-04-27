package ch.tie.gradle.plugins.json2md;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

class Json2mdPluginTest {

  private static final String JSON2MD_PLUGIN = "ch.tie.gradle.plugins.json2md";
  private final TestUtil testUtil = new TestUtil();

  @Test
  void json2mdTest() throws IOException {
    Project project = ProjectBuilder.builder().build();
    project.getPluginManager().apply(JSON2MD_PLUGIN);
    testUtil.copyResourceToProjectDir(project, "spring-configuration-metadata.json");
    assertTrue(project.getPluginManager().hasPlugin(JSON2MD_PLUGIN));

    Json2mdTask json2mdTask = (Json2mdTask) project.getTasks().getByName(Json2mdPlugin.TASK_NAME);
    assertNotNull(json2mdTask);
    json2mdTask.setMetadataPath(project.getPath() + "");
    json2mdTask.setMarkdownPath("./src/test/resources/Doc.md");
    json2mdTask.json2mdTask();
  }
}
