package ch.tie.gradle.plugins.json2md;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.gradle.api.Project;

public class TestUtil {

  public TestUtil() {
  }

  public void copyResourceToProjectDir(Project testProject, String metadataFile) throws IOException {
    InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(metadataFile);
    String target = testProject.getProjectDir() + "/" + metadataFile;
    FileUtils.copyInputStreamToFile(Objects.requireNonNull(resourceAsStream), new File(target));
  }
}
