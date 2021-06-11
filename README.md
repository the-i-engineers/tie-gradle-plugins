# Json2md Gradle Plugin

Reads `build/classes/java/main/META-INF/spring-configuration-metadata.json` generated
by [org.springframework.boot:spring-boot-configuration-processor](https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html)
and generates a Markdown-File to automate documenting your Configuration-Properties.

## Example

Reads metadata from `build/classes/java/main/META-INF/spring-configuration-metadata.json`

```json
{
  "groups": [
    {
      "name": "customer",
      "type": "ch.tie.iengine.config.CustomerProperties",
      "sourceType": "ch.tie.iengine.config.CustomerProperties"
    },
    {
      "name": "metrics.collector.db",
      "type": "ch.tie.iengine.config.DatabaseMetricsProperties",
      "sourceType": "ch.tie.iengine.config.DatabaseMetricsProperties"
    },
    {
      "name": "metrics.collector.discovery",
      "type": "ch.tie.iengine.config.DiscoveryMetricsProperties",
      "sourceType": "ch.tie.iengine.config.DiscoveryMetricsProperties"
    },
    {
      "name": "sender.email",
      "type": "ch.tie.iengine.config.EmailProperties",
      "sourceType": "ch.tie.iengine.config.EmailProperties"
    }
  ],
  "properties": [
    {
      "name": "customer.name",
      "type": "java.lang.String",
      "sourceType": "ch.tie.iengine.config.CustomerProperties"
    },
    {
      "name": "customer.service-level",
      "type": "java.util.List<java.lang.String>",
      "sourceType": "ch.tie.iengine.config.CustomerProperties"
    },
    {
      "name": "metrics.collector.db.enabled",
      "type": "java.lang.Boolean",
      "sourceType": "ch.tie.iengine.config.DatabaseMetricsProperties",
      "defaultValue": false
    },
    {
      "name": "metrics.collector.discovery.enabled",
      "type": "java.lang.Boolean",
      "sourceType": "ch.tie.iengine.config.DiscoveryMetricsProperties",
      "defaultValue": false
    },
    {
      "name": "metrics.collector.discovery.installation-config-path",
      "type": "java.nio.file.Path",
      "sourceType": "ch.tie.iengine.config.DiscoveryMetricsProperties"
    },
    {
      "name": "sender.email.enabled",
      "type": "java.lang.Boolean",
      "sourceType": "ch.tie.iengine.config.EmailProperties",
      "defaultValue": false
    },
    {
      "name": "sender.email.from-address",
      "type": "java.lang.String",
      "sourceType": "ch.tie.iengine.config.EmailProperties"
    },
    {
      "name": "sender.email.interval",
      "type": "java.time.Duration",
      "sourceType": "ch.tie.iengine.config.EmailProperties",
      "defaultValue": "1m"
    },
    {
      "name": "sender.email.subject",
      "type": "java.lang.String",
      "sourceType": "ch.tie.iengine.config.EmailProperties"
    },
    {
      "name": "sender.email.to-addresses",
      "type": "java.util.List<java.lang.String>",
      "sourceType": "ch.tie.iengine.config.EmailProperties"
    }
  ],
  "hints": []
}
```

Generated content in `Projectname-Documentation.md`

```markdown
# Projectname

## Properties

source | name | type | description | defaultValue | deprecation
----- | ----- | ----- | ----- | ----- | -----
ch.tie.iengine.config.CustomerProperties | customer.name | java.lang.String |  |  |
ch.tie.iengine.config.CustomerProperties | customer.service-level | java.util.List<java.lang.String> |  |  |
ch.tie.iengine.config.DatabaseMetricsProperties | metrics.collector.db.enabled | java.lang.Boolean |  | false |
ch.tie.iengine.config.DiscoveryMetricsProperties | metrics.collector.discovery.enabled | java.lang.Boolean |  | false |
ch.tie.iengine.config.DiscoveryMetricsProperties | metrics.collector.discovery.installation-config-path | java.nio.file.Path |  |  |
ch.tie.iengine.config.EmailProperties | sender.email.enabled | java.lang.Boolean |  | false |
ch.tie.iengine.config.EmailProperties | sender.email.from-address | java.lang.String |  |  |
ch.tie.iengine.config.EmailProperties | sender.email.interval | java.time.Duration |  | 1m |
ch.tie.iengine.config.EmailProperties | sender.email.subject | java.lang.String |  |  |
ch.tie.iengine.config.EmailProperties | sender.email.to-addresses | java.util.List<java.lang.String> |  |  |

```

## Usage

```groovy
json2md {
    metadataPath = 'custom-path-to.json'
    // default is $projectBuildDir/classes/java/main/META-INF/spring-configuration-metadata.json
    markdownFilename = 'customFilename.md'      // default is "$projectName-Documentation.md"
}
```
