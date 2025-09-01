#!/usr/bin/env sh
# Minimal wrapper script; Android Studio can also use its own Gradle
DIR="$(cd "$(dirname "$0")" && pwd)"
exec "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
