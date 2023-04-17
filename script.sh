mkdir -p ../TestFramework/WEB-INF/lib
mv $jar_file ../TestFramework/WEB-INF/lib/
echo JAR file created

# Création du fichier WAR pour l'application Web
# cd ../
mkdir -p ./TestFramework/WEB-INF/classes
cp -r ./classes/* ./TestFramework/WEB-INF/classes

jar cvf frameworkTest.war *

# Déploiement du fichier WAR sur Tomcat
cp TestFramework/frameworkTest.war "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps"
# cp TestFramework/frameworkTest.war $TOMCAT_HOME/webapps

set SOURCE_DIR=C:\path\to\source\directory
set BUILD_DIR=C:\path\to\build\directory
set WEBAPP_DIR=C:\path\to\webapp\directory
set TOMCAT_DIR=C:\path\to\tomcat\directory

rem Compilation des fichiers de code source Java
javac -d %BUILD_DIR% %SOURCE_DIR%\*.java

rem Création du fichier WAR
cd %BUILD_DIR%
jar -cvf %WEBAPP_DIR%\myapp.war *

rem Déploiement du fichier WAR sur Tomcat
cd %TOMCAT_DIR%\bin
catalina.bat stop
copy /Y %WEBAPP_DIR%\myapp.war %TOMCAT_DIR%\webapps
catalina.bat start