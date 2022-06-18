# Contributing to AutoCompiler

First off, thanks for taking the time to contribute

1. [Fork](https://help.github.com/articles/fork-a-repo/) this repository to your own GitHub account and then [clone](https://help.github.com/articles/cloning-a-repository/) it to your local device.
2. Create a new branch: `git checkout -b MY_BRANCH_NAME`
3. Install maven: `apt install maven`, or if u don't have maven installed use `mvnw` script. (VSCode will automatelly use it if you have java setuped in VScode.) 
4. Install the dependencies: `maven install`
5. Run `java -jar target/autocompiler-0.0.1-SNAPSHOT.jar` and watch for Sample application errors get fixed

For running autocompiler on any other file pass change the value in `-Dexec.args`.

```bash
mvn compile exec:java 
-Dexec.mainClass="com.ap.autocompiler.AutocompilerApplication"
-Dexec.args="src/main/resources/samples/MissingSemicolonSource.java"
```
