package ch.tie.gradle.plugins.json2md;

import static org.gradle.internal.impldep.org.hamcrest.CoreMatchers.is;
import static org.gradle.internal.impldep.org.hamcrest.CoreMatchers.notNullValue;
import static org.gradle.internal.impldep.org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;

class Json2mdReaderTest {

  private static final String VALID_METADATA_FILE = "spring-configuration-metadata.json";
  private static final String MALFORMED_METADATA = "malformed-metadata.json";
  private TestUtil testUtil;
  private Json2mdReader json2mdReader;

  @BeforeEach
  void setupJson2mdReaderTest() {
    testUtil = new TestUtil();
    json2mdReader = Json2mdBeanFactory.json2mdReader(testUtil.createTestProject());
  }

  @Test
  void shouldDeserializeValidMetadataFile() throws IOException {
    String target = testUtil.copyResourceToProjectDir(VALID_METADATA_FILE);

    SpringConfigurationMetadata springConfigurationMetadata = json2mdReader.readMetadata(target);

    assertThat(springConfigurationMetadata, notNullValue());
    assertThat(springConfigurationMetadata.getProperties().size(), is(10));
    assertThat(springConfigurationMetadata.getGroups().size(), is(4));
    assertThat(springConfigurationMetadata.getHints().size(), is(0));
  }

  @Test
  void shouldCreateEmptyMetadataFileIfMetadataMalformed() throws IOException {
    String target = testUtil.copyResourceToProjectDir(MALFORMED_METADATA);

    SpringConfigurationMetadata springConfigurationMetadata = json2mdReader.readMetadata(target);

    assertThat(springConfigurationMetadata, notNullValue());
    assertThat(springConfigurationMetadata.toMarkdown(), is(""));
  }

  @Test
  void shouldCreateEmptyMetadataFileIfNoMetadataFile() {
    SpringConfigurationMetadata springConfigurationMetadata = json2mdReader.readMetadata("unexisting_annotation.json");

    assertThat(springConfigurationMetadata.toMarkdown(), is(""));
  }

  @AfterEach
  void teardownTestProject() {
    testUtil.deleteTestProject();
  }
}
