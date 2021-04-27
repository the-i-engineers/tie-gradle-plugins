package ch.tie.gradle.plugins.json2md;

import static org.gradle.internal.impldep.org.hamcrest.CoreMatchers.is;
import static org.gradle.internal.impldep.org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.tie.gradle.plugins.json2md.model.Deprecation;
import ch.tie.gradle.plugins.json2md.model.Property;
import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;

class Json2MdConverterUtilTest {

  @Test
  void shouldWriteTableHeader() {
    String tableHeader = Json2mdConverterUtil.tableHeader("one", "two", "three");
    System.out.println(tableHeader);
    assertThat(tableHeader, is("one | two | three\n----- | ----- | -----\n"));
  }

  @Test
  void shouldWriteTableRow() {
    String tableRow = Json2mdConverterUtil.tableRow("dataOne", "dataTwo", "dataThree");

    assertThat(tableRow, is("dataOne | dataTwo | dataThree\n"));
  }

  @Test
  void shouldWriteBold() {
    String bold = Json2mdConverterUtil.bold("bold");
    assertThat(bold, is("**bold**"));
  }

  @Test
  void shouldWriteItalic() {
    String italic = Json2mdConverterUtil.italic("italic");
    assertThat(italic, is("*italic*"));
  }

  @Test
  void shouldConvertMetadata() {
    String markdown = metadata(3).toMarkdown();
    System.out.println(markdown);
    assertThat(markdown,
        is("## Properties\n\nname | type | description | sourceType | defaultValue | deprecation\n----- | ----- | ----- | ----- | ----- | -----\nname0 | type0 | description0 | sourceType0 | 0 | level0 reason0 replacement0\nname1 | type1 | description1 | sourceType1 | 1 | level1 reason1 replacement1\nname2 | type2 | description2 | sourceType2 | 2 | level2 reason2 replacement2\n"));
  }

  @Test
  void shouldConvertDeprecation() {
    String markdown = deprecation(1).toMarkdown();
    assertThat(markdown, is("level1 reason1 replacement1"));
  }

  @Test
  void shouldConvertProperty() {
    String markdown = property(1).toMarkdown();
    assertThat(markdown, is("name1 | type1 | description1 | sourceType1 | 1 | level1 reason1 replacement1\n"));
  }

  private Property property(int id) {
    Property property = new Property();
    property.setName("name" + id);
    property.setDescription("description" + id);
    property.setType("type" + id);
    property.setSourceType("sourceType" + id);
    property.setDefaultValue(defaultValue(id));
    property.setDeprecation(deprecation(id));

    return property;
  }

  private SpringConfigurationMetadata metadata(int properties) {
    SpringConfigurationMetadata springConfigurationMetadata = new SpringConfigurationMetadata();
    springConfigurationMetadata.setProperties(properties(properties));
    return springConfigurationMetadata;
  }

  private Deprecation deprecation(int id) {
    Deprecation deprecation = new Deprecation();
    deprecation.setLevel("level" + id);
    deprecation.setReason("reason" + id);
    deprecation.setReplacement("replacement" + id);
    return deprecation;
  }

  private Object defaultValue(int id) {
    return id;
  }

  private List<Property> properties(int num) {
    ArrayList<Property> properties = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      properties.add(property(i));
    }
    return properties;
  }
}
