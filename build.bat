@echo off

IF /I "x%JAVA_HOME%" == "x" (
  ECHO JAVA_HOME is not set!
  GOTO :END
)

"%JAVA_HOME%\bin\javac" SSLProtocolTests.java JndiTest.java


:END