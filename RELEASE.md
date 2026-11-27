# Release

Bump the version in build.gradle in the Pull Request to be released. After merging you can create a new release with this script.

Use the release script to create a new release: `./release.sh -t <tag> -n <notes>`

This script uses the `gh` cli to create a new release on GitHub. It requires the `gh` cli to be installed and authenticated with your GitHub account.

The script will:

1. Create a new tag for the checked out commit with the specified name.
2. Create a new release with generated release notes and additional notes if provided.
3. This will also push the tag to the remote repository and trigger the CI/CD workflow to publish the new version.

The `cd` job in `.github/workflows/gradle-ci-cd.yml` publishes each tagged version to two repositories:

| Target | Gradle task | Required secrets |
| ----- | ----- | ----- |
| Gradle Plugin Portal (`plugins.gradle.org`) | `publishPlugins` | `GRADLE_PUBLISH_KEY`, `GRADLE_PUBLISH_SECRET` |
| TIE Nexus (`https://dev-repository.tie.ch/repository/maven-releases/`) | `publishAllPublicationsToTieNexusRepository` | `NEXUS_USERNAME`, `NEXUS_PASSWORD` (account with deploy rights) |

TIE builds that resolve `ch.tie.gradle.plugins` only from Nexus need the Nexus publication.
Versions released before the Nexus step existed are not in Nexus. To publish one manually, check out its tag and run:

```shell
./gradlew publishAllPublicationsToTieNexusRepository -PnexusUsername=<user> -PnexusPassword=<password>
```

Nexus release repositories reject redeploying an existing version.


