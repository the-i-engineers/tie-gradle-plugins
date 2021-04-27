package ch.tie.gradle.plugins.json2md;

import org.gradle.api.Project;

public class Json2mdPlugin implements org.gradle.api.Plugin<Project> {

  static final String TASK_NAME = "json2md";
  private static final String TASK_DEPENDENCY = "compileJava";

  @Override
  public void apply(Project project) {
    Json2mdTask json2mdTask = project.getTasks()
        .create(TASK_NAME, Json2mdTask.class, Json2mdBeanFactory.json2mdReader(project),
            Json2mdBeanFactory.json2mdWriter(project));
    json2mdTask.dependsOn(TASK_DEPENDENCY);
  }
}
