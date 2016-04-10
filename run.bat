@echo off

IF /I "x%JRE_HOME%" == "x" (
  ECHO JRE_HOME is not set!
  GOTO :END
)
  
"%JRE_HOME%\bin\java" SSLProtocolTests

:END