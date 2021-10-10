# cribl-test

Demonstrates testing Cribl APIs using the available sandbox and swagger documentatation.

## Setup
1. Install the automatically generated [Cribl OpenAPI Client](https://github.com/wonkas-factory/cribl-openapi-client) locally. Pull down the code and run ```mvn clean install -DskipTests```
2. Create a [Cribl sandbox](https://sandbox.cribl.io/course/fundamentals) if you don't have one already with an email. They last 24 hours
3. Update the environment file located at config/environment.yml with the baseHost. Note the port in the path the tutorial seems to be 3000 vs 9000 for just the Cribl application. The un and pw should be the same unless you have a non sandbox account
4. Run the sample tests by ```mvn clean test```
