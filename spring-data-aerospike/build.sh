# Build Java projects with Maven.
# Maven and Java must be installed prior to running this script.

# Add gnu-crypto to local maven repository. This only needs to be done once.
mvn install:install-file -Dfile=depends/gnu-crypto.jar -DgroupId=org.gnu -DartifactId=gnu-crypto -Dversion=2.0.1 -Dpackaging=jar
mvn install:install-file -Dfile=depends/com/aerospike/aerospike-client/3.0.6/aerospike-client-3.0.6.jar -DgroupId=com.aerospike -DartifactId=aerospike-client -Dversion=3.0.6 -Dpackaging=jar

mvn clean
mvn package

