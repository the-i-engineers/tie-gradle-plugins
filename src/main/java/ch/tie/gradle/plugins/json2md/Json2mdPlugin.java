package ch.tie.gradle.plugins.json2md;

import org.gradle.api.Project;

public class Json2mdPlugin implements org.gradle.api.Plugin<Project> {

  @Override
  public void apply(Project project) {
    project.getTasks().create("json2md", Json2mdTask.class);
  }
}
