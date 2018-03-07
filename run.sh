#!/bin/bash


files=`find . -name "*.jar"`
cp=""
for file in $files
do
    cp=$file:$cp
done
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/resources.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/rt.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jsse.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jce.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/charsets.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jfr.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunec.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/nashorn.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/cldrdata.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/jfxrt.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/dnsns.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/localedata.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/jaccess.jar
cp=$cp:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/zipfs.jar
cp=$cp:/System/Library/Java/Extensions/MRJToolkit.jar
cp=$cp:/Users/kgoldstein/dev/neeve/other/SDTGui/target/classes
cp=$cp:/Users/kgoldstein/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/jfree/jfreechart/1.5.0/jfreechart-1.5.0.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/slf4j/slf4j-log4j12/1.7.25/slf4j-log4j12-1.7.25.jar
cp=$cp:/Users/kgoldstein/.m2/repository/log4j/log4j/1.2.17/log4j-1.2.17.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/apache/commons/commons-configuration2/2.0/commons-configuration2-2.0.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar
cp=$cp:/Users/kgoldstein/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar
cp=$cp:/Users/kgoldstein/.m2/repository/commons-beanutils/commons-beanutils/1.9.3/commons-beanutils-1.9.3.jar
cp=$cp:/Users/kgoldstein/.m2/repository/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/neeve/nvx-talon/3.8.188/nvx-talon-3.8.188.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.4.3/jackson-core-2.4.3.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.4.3/jackson-annotations-2.4.3.jar
cp=$cp:/Users/kgoldstein/.m2/repository/colt/colt/1.2/colt-1.2.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/dspace/xmlui/concurrent/concurrent/1.3.4/concurrent-1.3.4.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/googlecode/disruptor/disruptor/2.10.1/disruptor-2.10.1.jar
cp=$cp:/Users/kgoldstein/.m2/repository/jargs/jargs/1.0/jargs-1.0.jar
cp=$cp:/Users/kgoldstein/.m2/repository/commons-codec/commons-codec/1.6/commons-codec-1.6.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/github/os72/protoc-jar/2.4.1.0/protoc-jar-2.4.1.0.jar
cp=$cp:/Users/kgoldstein/.m2/repository/jline/jline/2.11/jline-2.11.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/google/guava/guava/18.0/guava-18.0.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/neeve/nvx-adm/3.8.188/nvx-adm-3.8.188.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/fusesource/jansi/jansi/1.11/jansi-1.11.jar
cp=$cp:/Users/kgoldstein/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/google/protobuf/protobuf-java/2.4.1/protobuf-java-2.4.1.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/neeve/nvx-hummingbird/3.8.188/nvx-hummingbird-3.8.188.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/rubiconproject/oss/jchronic/0.2.6/jchronic-0.2.6.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/google/truth/truth/0.23/truth-0.23.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/google/guava/guava-testlib/18.0/guava-testlib-18.0.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/google/code/findbugs/jsr305/1.3.9/jsr305-1.3.9.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/higherfrequencytrading/affinity/1.6.0/affinity-1.6.0.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/sun/jna/3.3.0/jna-3.3.0.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/mapdb/mapdb/1.0.7/mapdb-1.0.7.jar
cp=$cp:/Users/kgoldstein/.m2/repository/commons-net/commons-net/2.2/commons-net-2.2.jar
cp=$cp:/Users/kgoldstein/.m2/repository/tanukisoft/wrapper/3.5.25/wrapper-3.5.25.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.4.3/jackson-databind-2.4.3.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/neeve/nvx-license-verifier/1.0.3/nvx-license-verifier-1.0.3.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/slf4j/slf4j-api/1.7.2/slf4j-api-1.7.2.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/neeve/nvx-license/1.0.3/nvx-license-1.0.3.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/eaio/uuid/uuid/3.2/uuid-3.2.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/hdrhistogram/HdrHistogram/2.1.8/HdrHistogram-2.1.8.jar
cp=$cp:/Users/kgoldstein/.m2/repository/org/apache/commons/commons-compress/1.3/commons-compress-1.3.jar
cp=$cp:/Users/kgoldstein/.m2/repository/com/akiban/akiban-sql-parser/1.0.16/akiban-sql-parser-1.0.16.jar



package=com.neeve.tools.gui.sdt.view.EntryPoint
profile="-p desktop"
vmargs="-Djava.net.preferIPv4Stack=true"
if [ "$1" == "cp" ] ; then
    echo $cp
elif [ "$1" == "main" ] ; then
    java -cp $cp $package
else
    echo "$0 main"
fi
