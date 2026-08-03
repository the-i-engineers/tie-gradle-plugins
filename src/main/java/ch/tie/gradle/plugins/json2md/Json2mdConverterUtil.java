package ch.tie.gradle.plugins.json2md;

import ch.tie.gradle.plugins.json2md.model.SpringConfigurationMetadata;
import ch.tie.gradle.plugins.json2md.model.TableHeader;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Json2mdConverterUtil {

  private static final String HEADER = "#";
  private static final String SPACE = " ";
  private static final String TABLE_SEPARATOR = "-----";
  private static final String TABLE_DELIMITER = " | ";

  private static final String TABLE_DELIMITER_START = "| ";
  private static final String[] BOLD = {"**", "**"};
  private static final String[] ITALIC = {"*", "*"};

  private Json2mdConverterUtil() {
  }

  public static String metadata(SpringConfigurationMetadata metadata, String projectName) {
    return h1(projectName) + metadata.toMarkdown();
  }

  public static String h1(String heading) {
    return header(1, heading);
  }

  public static String h2(String heading) {
    return header(2, heading);
  }

  public static String newLine() {
    return "\n";
  }

  public static String tableHeader(List<TableHeader> tableHeaders) {
    return headers(tableHeaders.stream().map(TableHeader::description).toList());
  }

  private static String headers(List<String> headers) {
    String[] separators = new String[headers.size()];
    Arrays.fill(separators, TABLE_SEPARATOR);
    return addSideBorders(String.join(TABLE_DELIMITER, headers)) + newLine() + addSideBorders(
        String.join(TABLE_DELIMITER, separators)) + newLine();
  }

  public static String tableRow(List<String> data) {
    return addSideBorders(String.join(TABLE_DELIMITER, data)) + newLine();
  }

  public static String tableRow(String... data) {
    return tableRow(Arrays.asList(data));
  }

  private static String header(int num, String heading) {
    return HEADER.repeat(Math.max(0, num)) + SPACE + heading + newLine().repeat(2);
  }

  public static String bold(String text) {
    return String.join(text, BOLD);
  }

  public static String italic(String text) {
    return String.join(text, ITALIC);
  }

  public static String text(String... lines) {
    return Arrays.stream(lines).filter(StringUtils::isNotBlank).collect(Collectors.joining(SPACE));
  }

  private static String addSideBorders(String content) {
    return TABLE_DELIMITER_START + content + TABLE_DELIMITER;
  }

  public static String replaceLineBreaks(String line) {
    return Optional.ofNullable(line)
            .map(l -> l.replaceAll("\n", " "))
            .orElse(null);
  }
}
