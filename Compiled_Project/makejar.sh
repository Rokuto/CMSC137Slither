javac src/client/*.java -d bin/client
cd bin/client
jar cfm ../../GlutonSnake.jar META-INF/MANIFEST.MF *.class
cd ../..

javac src/server/*.java -d bin/server
cd bin/server
jar cfm ../../ServerGlutonSnake.jar META-INF/MANIFEST.MF *.class
cd ../..

# java -jar GlutonSnake.jar
# java -jar GlutonSnake.jar
