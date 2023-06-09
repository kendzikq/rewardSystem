### HOW TO RUN THE APPLICATION
1. gradle clean generateOpenApiDocs
2. docker compose up
3. run RetailSystemApplication (for instance in intellij)
4. send requests via SWAGGER UI: http://localhost:8080/swagger-ui/index.html

### SPECIAL THINGS TO NOE
1. instead of the word "transaction" it has been decided to use the word "purchase" because "transaction" is related to the technical mechanism.
2. for simplicity the application fetches all the transactions from DB. In Real world it should be restricted to some period of time
3. the application has predefined users with ids: 1, 2, 3 please use them
