#!/bin/sh

if ! command -v gh > /dev/null; then
  echo "Please install 'gh cli' (https://cli.github.com/)"
  exit 3
fi

usage() {
  echo '''
  Usage: release PARAMS
  PARAMS:
  -t TAG             Tag to create
  -p                 (optional) Marks the release as a pre-release
  -n NOTES           (optional) Adds custom release notes'''
}

while getopts "t:pn:" opt; do
  case $opt in
    t)
      tag=$OPTARG
      ;;
    p)
      prerelease=true
      ;;
    n)
      notes=$OPTARG
      ;;
    *)
      usage
      exit 3
      ;;
  esac
done

if [ -z "$tag" ]
then
  echo "no tag set!"
  usage
  exit 1
fi

if [ -z "$notes" ]
then
  notes="Release $tag"
fi

if [ -z "$prerelease" ]
then
  prerelease=false
fi

echo "Creating release $tag with notes: $notes"
gh --version | head -1
gh release create "$tag" --title "$tag" --generate-notes --notes "$notes" --prerelease="$prerelease"
