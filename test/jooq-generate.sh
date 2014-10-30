mvn compile
cp src/java/com/rapleaf/jack/test_project/database_1/database_1.xml build/classes/com/rapleaf/jack/test_project/database_1/
mvn exec:java -Dexec.mainClass="org.jooq.util.GenerationTool" -Dexec.args="com/rapleaf/jack/test_project/database_1/database_1.xml"
