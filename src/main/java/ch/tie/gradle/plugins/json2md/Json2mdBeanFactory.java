package ch.tie.gradle.plugins.json2md;


import org.gradle.api.Project;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Json2mdBeanFactory {

  public static Json2mdReader json2mdReader(Project project) {
    return new Json2mdReader(project, new ObjectMapper());
  }

  public static Json2mdWriter json2mdWriter(Project project) {
    return new Json2mdWriter(project);
  }
}
