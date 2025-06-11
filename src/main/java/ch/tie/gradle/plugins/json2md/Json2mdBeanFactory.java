package ch.tie.gradle.plugins.json2md;


import org.gradle.api.Project;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Json2mdBeanFactory {

  private Json2mdBeanFactory() {
  }

  public static Json2mdReader json2mdReader(Project project) {
    ObjectMapper objectMapper = new ObjectMapper();
    // ignore unknown properties in Json
    objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return new Json2mdReader(project, objectMapper);
  }

  public static Json2mdWriter json2mdWriter(Project project) {
    return new Json2mdWriter(project);
  }
}
