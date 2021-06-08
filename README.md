[![Build Status](https://travis-ci.org/zeidon/zeidon-joe.svg?branch=current-dev)](https://travis-ci.org/zeidon/zeidon-joe)

# Zeidon Java Object Engine (JOE) and related projects.

Zeidon is an application framework for developing data-oriented applications.  It enables engineers to design their solutions at a higher level of abstraction to separate the business logic from the underlying storage system.  Zeidon developers use the abstraction to build "logical objects" which group multiple entities from the ER model into structures that are given object-oriented traits.  The abstraction also eliminates the the impedance mismatch that occurs between relational DBs and OOP applications.  The Java Object Engine is the runtime component of Zeidon.

For more information see the [Zeidon documentation page](http://zeidon.github.io/zeidon-joe).

## Usage

### Maven

Object engine:

```
    <dependency>
      <groupId>com.quinsoft.zeidon</groupId>
      <artifactId>zeidon-joe</artifactId>
      <version>2.0.0-SNAPSHOT</version>
    </dependency>
```

Scala DSL:

```
    <dependency>
      <groupId>com.quinsoft.zeidon</groupId>
      <artifactId>zeidon-scala</artifactId>
      <version>2.0.0-SNAPSHOT</version>
    </dependency>
```

### Gradle

```
dependencies {
    compile 'com.quinsoft.zeidon:zeidon-joe:2.0.0-SNAPSHOT'
    compile 'com.quinsoft.zeidon:zeidon-scala:2.0.0-SNAPSHOT'
}
```

## Javadoc/Scaladoc (current dev)

* Scala: http://zeidon.github.io/zeidon-joe/javadoc/2.0/scala/
* JOE: http://zeidon.github.io/zeidon-joe/javadoc/2.0/joe/
* Android: http://zeidon.github.io/zeidon-joe/javadoc/2.0/android/

## Build Zeidon

Zeidon requires Maven 3.04+.  Build using standard Maven:

```
mvn install
```

## Additional links

* Travis CI: https://travis-ci.org/zeidon/zeidon-joe
