package ch.tie.gradle.plugins.json2md;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;

public class TestUtil {

  private static final String PROJECT_DIR = "./test-project";
  private Project project;

  public TestUtil() {
  }

  public String copyResourceToProjectDir(String metadataFile) throws IOException {
    try(InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(metadataFile)) {
      String target = project.getLayout().getBuildDirectory().get() + "/classes/java/main/META-INF/" + metadataFile;
      FileUtils.copyInputStreamToFile(Objects.requireNonNull(resourceAsStream), new File(target));
      return target;
    }
  }

  public Project createTestProject() {
    project = ProjectBuilder.builder().withProjectDir(new File(PROJECT_DIR)).build();
    return project;
  }

  public void deleteTestProject() {
    try(Stream<Path> list = Files.walk(Paths.get(PROJECT_DIR))) {
      list.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    } catch (IOException e) {
      //ignore
    }
  }
}
