classDir=./../bin;
sourceDir=./main/java
javac -d ${classDir} ${sourceDir}/particles/*.java ${sourceDir}/particles/cim/*.java;
java -Xmx6G -cp ${classDir} particles.Main