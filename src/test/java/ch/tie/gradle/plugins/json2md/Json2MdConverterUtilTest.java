package ch.tie.gradle.plugins.json2md;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ch.tie.gradle.plugins.json2md.model.Deprecation;
import ch.tie.gradle.plugins.json2md.model.Property;
import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;
import ch.tie.gradle.plugins.json2md.model.TableHeader;

class Json2MdConverterUtilTest {

  @Test
  void shouldWriteTableHeader() {
    String tableHeader = Json2mdConverterUtil.tableHeader(
        List.of(TableHeader.NAME, TableHeader.TYPE, TableHeader.DESCRIPTION));
    assertThat(tableHeader, is("""
        | name | type | description |\040
        | ----- | ----- | ----- |\040
        """));
  }

  @Test
  void shouldWriteTableRow() {
    String tableRow = Json2mdConverterUtil.tableRow("dataOne", "dataTwo", "dataThree");

    assertThat(tableRow, is("| dataOne | dataTwo | dataThree | \n"));
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
    assertThat(markdown, is("""
       ## Properties
                    
       | name | type | description | defaultValue |\040
       | ----- | ----- | ----- | ----- |\040
       | name0 | type0 | description0 | 0 |\040
       | name1 | type1 | description1 | 1 |\040
       | name2 | type2 | description2 | 2 |\040
        """));
  }

  @Test
  void shouldExcludeSources() {
    SpringConfigurationMetadata metadata = metadata(5);
    metadata.setExcludedSources(List.of("sourceType1", "sourceType2"));
    String markdown = metadata.toMarkdown();

    assertThat(markdown, is("""
        ## Properties
          
        | name | type | description | defaultValue |\040
        | ----- | ----- | ----- | ----- |\040
        | name0 | type0 | description0 | 0 |\040
        | name3 | type3 | description3 | 3 |\040
        | name4 | type4 | description4 | 4 |\040
        """));
  }

  @Test
  void shouldConvertDeprecation() {
    String markdown = deprecation(1).toMarkdown();
    assertThat(markdown, is("level1 reason1 replacement1"));
  }

  @Test
  void shouldConvertAllFieldsOfProperty() {
    Property property = property(1);
    property.setTableHeaders(TableHeader.ALL_HEADERS);
    String markdown = property.toMarkdown();
    assertThat(markdown, is("| name1 | type1 | description1 | 1 | level1 reason1 replacement1 | sourceType1 | \n"));
  }

  @Test
  void shouldConvertIncludedFieldsOfProperty() {
    Property property = property(1);
    property.setTableHeaders(List.of(TableHeader.NAME, TableHeader.DESCRIPTION, TableHeader.DEFAULT_VALUE));
    String markdown = property.toMarkdown();
    assertThat(markdown, is("| name1 | description1 | 1 | \n"));
  }

  @Test
  void shouldConvertDefaultFieldsOfProperty() {
    Property property = property(1);
    String markdown = property.toMarkdown();
    assertThat(markdown, is("| name1 | type1 | description1 | 1 | level1 reason1 replacement1 | sourceType1 | \n"));
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
