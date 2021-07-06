package ch.tie.gradle.plugins.json2md;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.gradle.api.Project;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Json2mdPluginTest {

  private static final String JSON2MD_PLUGIN = "ch.tie.gradle.plugins.json2md";
  public static final String METADATA_FILE = "spring-configuration-metadata.json";
  private TestUtil testUtil;
  private Project testProject;

  @BeforeEach
  void setupJson2mdPluginTest() throws IOException {
    testUtil = new TestUtil();
    testProject = testUtil.createTestProject();
    testUtil.copyResourceToProjectDir(METADATA_FILE);
  }

  @Test
  void json2mdTest() {
    testProject.getPluginManager().apply(JSON2MD_PLUGIN);
    assertTrue(testProject.getPluginManager().hasPlugin(JSON2MD_PLUGIN));

    Json2mdTask json2mdTask = (Json2mdTask) testProject.getTasks().getByName(Json2mdPlugin.TASK_NAME);
    assertNotNull(json2mdTask);
    json2mdTask.json2mdTask();

    assertTrue(testProject.file("ConfigurationProperties.md").exists());
  }

  @AfterEach
  void teardownTestProject() {
    testUtil.deleteTestProject();
  }
}
