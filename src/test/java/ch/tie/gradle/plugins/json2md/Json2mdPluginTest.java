package ch.tie.gradle.plugins.json2md;

import static org.junit.jupiter.api.Assertions.*;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

class Json2mdPluginTest {

  private static final String JSON2MD_PLUGIN = "ch.tie.gradle.plugins.json2md";

  @Test
  void json2mdTest() {
    Project project = ProjectBuilder.builder().build();
    project.getPluginManager().apply(JSON2MD_PLUGIN);

    assertTrue(project.getPluginManager().hasPlugin(JSON2MD_PLUGIN));

    assertNotNull(project.getTasks().getByName("json2md"));
  }
}
