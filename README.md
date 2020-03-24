# sdir

scan directory example app to learn about graalVM 

# run it using SBT

```shell script
sbt "run sync $PWD"
...
Took 312 ms. to process 4020 items. 12 files/ms
```

# run it using a Jar

```shell script
sbt assembly
time java -jar target/scala-2.13/sdir.jar sync $PWD
...
Took 409 ms. to process 4024 items. 9 files/ms
java -jar target/scala-2.13/sdir.jar sync $PWD  1.38s user 0.19s system 207% cpu 0.754 total
```


# run it using graal in a VM
Make sure you are running docker

```shell script
sbt graalvm-native-image:packageBin
time docker run -it -v $(pwd)/target:/target oracle/graalvm-ce:20.0.0-java11 /target/graalvm-native-image/sdir sync /target
...
Took 4252 ms. to process 3690 items. 0 files/ms
docker run -it -v $(pwd)/target:/target oracle/graalvm-ce:20.0.0-java11  sync  0.05s user 0.02s system 1% cpu 5.015 total
...
```

# run it using graal installed locally
Make sure you have `native-image` is in your PATH, follow instructions https://blog.softwaremill.com/graalvm-installation-and-setup-on-macos-294dd1d23ca2

Comment line `graalVMNativeImageGraalVersion := Some("20.0.0-java11")` in build.sbt

```shell script
sbt graalvm-native-image:packageBin
time target/graalvm-native-image/sdir sync $PWD
...
Took 132 ms. to process 4024 items. 30 files/ms 
target/graalvm-native-image/sdir sync $PWD  0.08s user 0.06s system 96% cpu 0.143 total
```


