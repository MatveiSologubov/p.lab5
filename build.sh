javac -d . ./src/*.java ./src/*/*.java ./src/*/*/*.java

jar cfm app.jar META-INF/MANIFEST.MF -C . ./

export COLLECTION_FILE=~/labs/p.lab5/tickets.xml

java -jar app.jar

