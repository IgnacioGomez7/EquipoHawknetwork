@ECHO OFF
SET DIR=%~dp0
SET CLASSPATH=%DIR%\gradle\wrapper\gradle-wrapper.jar
IF NOT "%JAVA_HOME%"=="" (
  SET "JAVA=%JAVA_HOME%\bin\java.exe"
) ELSE (
  SET "JAVA=java"
)
"%JAVA%" -Xms64m -Xmx64m -cp "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*