# Release

Bump the version in build.gradle in the Pull Request to be released. After merging you can create a new release with this script.

Use the release script to create a new release: `./release.sh -t <tag> -n <notes>`

This script uses the `gh` cli to create a new release on GitHub. It requires the `gh` cli to be installed and authenticated with your GitHub account.

The script will:

1. Create a new tag for the checked out commit with the specified name.
2. Create a new release with generated release notes and additional notes if provided.
3. This will also push the tag to the remote repository and trigger the CI/CD workflow to publish the new version.

