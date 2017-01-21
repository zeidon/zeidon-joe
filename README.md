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
      <version>1.4.1</version>
    </dependency>
```

Scala DSL:

```
    <dependency>
      <groupId>com.quinsoft.zeidon</groupId>
      <artifactId>zeidon-joe</artifactId>
      <version>1.4.1</version>
    </dependency>
```

### Gradle

```
dependencies {
    compile 'com.quinsoft.zeidon:zeidon-joe:1.4.1'
    compile 'com.quinsoft.zeidon:zeidon-scala:1.4.1'
}
```

## Javadoc/Scaladoc (current dev)

* Scala: http://zeidon.github.io/zeidon-joe/javadoc/1.5/scala/
* JOE: http://zeidon.github.io/zeidon-joe/javadoc/1.5/joe/
* Android: http://zeidon.github.io/zeidon-joe/javadoc/1.5/android/

## Build Zeidon

Zeidon requires Maven 3.04+.  Build using standard Maven:

```
mvn install
```

## Additional links

* Travis CI: https://travis-ci.org/zeidon/zeidon-joe
