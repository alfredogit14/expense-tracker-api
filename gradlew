#!/usr/bin/env sh
DIR="$(cd "$(dirname "$0")" && pwd)"
JAVA_EXE="${JAVA_HOME:+$JAVA_HOME/bin/java}${JAVA_HOME:+' '}'${JAVA_HOME:+$JAVA_HOME/bin/java}${JAVA_HOME:+}'"
if [ -n "$JAVA_HOME" ]; then
  JAVA_CMD="$JAVA_HOME/bin/java"
else
  JAVA_CMD="java"
fi
exec "$JAVA_CMD" -cp "$DIR/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
