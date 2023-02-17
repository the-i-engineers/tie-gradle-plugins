package ch.tie.gradle.plugins.json2md;

import org.gradle.api.Project;
import org.gradle.api.Task;

public class Json2mdPlugin implements org.gradle.api.Plugin<Project> {

  static final String TASK_NAME = "json2md";

  @Override
  public void apply(Project project) {
    project.getTasks()
        .create(TASK_NAME, Json2mdTask.class, project, Json2mdBeanFactory.json2mdReader(project),
            Json2mdBeanFactory.json2mdWriter(project));
  }
}
