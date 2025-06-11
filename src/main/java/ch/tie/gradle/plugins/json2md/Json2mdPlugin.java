package ch.tie.gradle.plugins.json2md;

import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

public class Json2mdPlugin implements org.gradle.api.Plugin<Project> {

  static final String TASK_NAME = "json2md";

  @Override
  public void apply(Project project) {

    TaskProvider<Json2mdTask> json2mdTaskTaskProvider = project.getTasks()
        .register(TASK_NAME, Json2mdTask.class, project, Json2mdBeanFactory.json2mdReader(project),
            Json2mdBeanFactory.json2mdWriter(project));
    json2mdTaskTaskProvider.configure(task -> {
      task.setGroup("documentation");
      task.setDescription("Generates markdown documentation from Spring configuration metadata JSON files.");
      task.dependsOn("compileJava");
    });
  }
}
