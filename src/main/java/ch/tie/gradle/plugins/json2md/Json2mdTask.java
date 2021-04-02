package ch.tie.gradle.plugins.json2md;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskAction;

public class Json2mdTask extends DefaultTask {

  @TaskAction
  public void json2mdTask() {
    dependsOn(Task.TASK_NAME);
    System.out.println("json2mdTask");
  }
}
