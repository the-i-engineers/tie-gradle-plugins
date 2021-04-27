package ch.tie.gradle.plugins.json2md;

import static org.gradle.internal.impldep.org.hamcrest.CoreMatchers.containsString;
import static org.gradle.internal.impldep.org.hamcrest.CoreMatchers.is;
import static org.gradle.internal.impldep.org.hamcrest.CoreMatchers.notNullValue;
import static org.gradle.internal.impldep.org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.gradle.api.GradleException;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;

class Json2mdReaderTest {

  private static final String PROJECT_DIR = "./src/test/resources/project";
  private static final String VALID_METADATA_FILE = "spring-configuration-metadata.json";
  private static final String MALFORMED_METADATA = "malformed-metadata.json";
  private final TestUtil testUtil = new TestUtil();
  private Json2mdReader json2mdReader;
  private Project testProject;

  @BeforeEach
  void setupJson2mdReaderTest() {
    deleteTestProject();
    testProject = ProjectBuilder.builder().withProjectDir(new File(PROJECT_DIR)).build();
    json2mdReader = new Json2mdReader(testProject, new ObjectMapper());
  }

  @Test
  void shouldDeserializeValidMetadataFile() throws IOException {
    testUtil.copyResourceToProjectDir(testProject, VALID_METADATA_FILE);

    SpringConfigurationMetadata springConfigurationMetadata = json2mdReader.readMetadata(VALID_METADATA_FILE);

    assertThat(springConfigurationMetadata, notNullValue());
    assertThat(springConfigurationMetadata.getProperties().size(), is(10));
    assertThat(springConfigurationMetadata.getGroups().size(), is(4));
    assertThat(springConfigurationMetadata.getHints().size(), is(0));
  }

  @Test
  void shouldThrowExceptionIfMetadataMalformed() throws IOException {
    testUtil.copyResourceToProjectDir(testProject, MALFORMED_METADATA);

    GradleException gradleException = assertThrows(GradleException.class,
        () -> json2mdReader.readMetadata(MALFORMED_METADATA));

    assertThat(gradleException.getMessage(), containsString("Unrecognized field \"groupies\""));
  }

  @Test
  void shouldThrowExceptionIfFileNotFound() {
    GradleException gradleException = assertThrows(GradleException.class,
        () -> json2mdReader.readMetadata("invalid filepath"));

    assertThat(gradleException.getMessage(), containsString("No such file found 'invalid filepath'"));
  }

  @AfterEach
  void teardown() {
    deleteTestProject();
  }

  private void deleteTestProject() {
    try {
      Files.walk(Paths.get(PROJECT_DIR)).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    } catch (IOException e) {
      //ignore
    }
  }
}
