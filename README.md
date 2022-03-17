![Build Status](https://github.com/zeidon/zeidon-joe/actions/workflows/ci.yml/badge.svg)

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
      <version>2.0.1</version>
    </dependency>
```

Scala DSL:

```
    <dependency>
      <groupId>com.quinsoft.zeidon</groupId>
      <artifactId>zeidon-scala</artifactId>
      <version>2.0.1</version>
    </dependency>
```

### Gradle

```
dependencies {
    compile 'com.quinsoft.zeidon:zeidon-joe:2.0.1'
    compile 'com.quinsoft.zeidon:zeidon-scala:2.0.1'
}
```

## Javadoc/Scaladoc (master)

* Scala: http://zeidon.github.io/zeidon-joe/javadoc/2.0/scala/
* JOE: http://zeidon.github.io/zeidon-joe/javadoc/2.0/joe/
* Android: http://zeidon.github.io/zeidon-joe/javadoc/2.0/android/

## Build Zeidon

Zeidon requires Java 1.8, Maven 3.04+, and node/npm.  Build using standard Maven:

```
mvn install
```

Later versions of Java can be used for local builds but only 1.8 should be used to deploy.

## Running tests

To make it easier to set up tests, most of them are in the `zeidon-tests` project; e.g. the `zeidon-joe` project has no tests.  The tests will be run as part of the full repo test:

`mvn test`

Some tests require a proprietary Sqlite DB to run.  If you have the DBs, set `ZENCAS_SQLITE_DIR` environment variable to refer to the directory containing the DBs.

There are a few tests for future functionality that currently fail.  These tests are ignored by default.  To run all the tests, including the ones that fail, use:

`mvn test -DrunAllTests=true`

## Additional links
