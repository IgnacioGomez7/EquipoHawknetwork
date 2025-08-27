#!/usr/bin/env sh
DIR="$(cd "$(dirname "$0")" && pwd)"
CLASSPATH="$DIR/gradle/wrapper/gradle-wrapper.jar"
exec java -Xms64m -Xmx64m -cp "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"