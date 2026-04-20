package ch.tie.gradle.plugins.json2md;


import org.gradle.api.Project;

import tools.jackson.databind.json.JsonMapper;

public class Json2mdBeanFactory {

  private Json2mdBeanFactory() {
  }

  public static Json2mdReader json2mdReader(Project project) {
    JsonMapper jsonMapper = new JsonMapper();
    // ignore unknown properties in Json
    return new Json2mdReader(project, jsonMapper);
  }

  public static Json2mdWriter json2mdWriter(Project project) {
    return new Json2mdWriter(project);
  }
}
