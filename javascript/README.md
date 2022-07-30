# JavaScript object engine and related projects

## Overview

This project is a sub-set of the Zeidon Object Engine written in TypeScript for use in JavaScript and frameworks.  This project has the following sub-projects:

### JS OE and associated packages

The JS OE is written in TypeScript and broken up into multiple npm packages managed using [Lerna](https://github.com/lerna/lerna).

### LOD Generator

Scala code to generate `*.ts` files from LODs.  The .ts files can then be used in JS projects.

## Building

All the sub-projects can be compiled/built using Maven:

`mvn install` or
`mvn test`

This will compile the Scala generator and then use npm to build/test all of the TypeScript packages.

## Testing

Use `npm run test` to execute the tests.  This will start up a jetty server to handle activate/commits.  Tests can alwo be run from VS Code.

Run `mvn install` to rebuild code changes.  (Something should be possible via npm?)
