@echo off
setlocal

@REM Compilation des java dans framework/src
javac -d . framework/src/annotation/Url.java
javac -d . framework/src/framework/Mapping.java
javac -d . framework/src/framework/View.java
javac -d . framework/src/framework/FrontServlet.java

IF EXIST classes (
    RD /S /Q classes
) ELSE (
    mkdir classes
)

set source_dir=classes
cd %source_dir%

@REM Deplacement de ces fichiers compiles vers classes, s'ils existent deja alors les supprimer avant de les deplacer
IF EXIST annotation (
    RD /S /Q annotation
    MOVE /Y ..\annotation .
) ELSE (
    MOVE /Y ..\annotation .
)
IF EXIST etu2039/ (
    RD /S /Q etu2039
    MOVE /Y ..\etu2039 .
) ELSE (
    MOVE /Y ..\etu2039 .
)

@REM Creation de JAR
set jar_file=framework.jar

jar cvf %jar_file% *

@REM Deplacement de ce jar vers lib du projet test
IF EXIST ..\testFramework\WEB-INF\lib\framework.jar (
    DEL ..\testFramework\WEB-INF\lib\framework.jar
    MOVE /Y framework.jar ..\testFramework\WEB-INF\lib
) ELSE (
    MOVE /Y framework.jar ..\testFramework\WEB-INF\lib
)

cd ..

@REM Compilation des java dans testFramework
javac -cp testFramework/WEB-INF/lib/framework.jar -d . testFramework/modele/Dept.java

cd testFramework/WEB-INF/classes

IF EXIST model (
    RD /S /Q model
    MOVE /Y ..\..\..\model .
) ELSE (
    MOVE /Y ..\..\..\model .
)

cd ../../

set war_file=testFramework.war

jar cvf %war_file% *

IF EXIST "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\testFramework" (
    RD /S /Q "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\testFramework"
) 

IF EXIST "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\testFramework.war" (
    DEL "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\testFramework.war"
    MOVE /Y testFramework.war "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps"
) ELSE (
    MOVE /Y testFramework.war "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps"
)









