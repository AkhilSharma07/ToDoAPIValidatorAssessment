
# ToDo API Validator Assessment

This project is an automation framework to validate the functionality of the ToDo API from jsonplaceholder.typicode.com. The objective is to verify that all users from the city FanCode (identified by specific latitude and longitude ranges) have completed more than 50% of their todo tasks.

The project is designed with extensibility, reusability, and modularity in mind, following best practices for API testing using Java, RestAssured, and TestNG.


## Features

- API Validation: Automated testing for the ToDo and User API endpoints.
- FanCode City Validation: Validates that users located within a specific geographic region have completed more than 50% of their tasks.
- Dynamic Reporting: Generates detailed test execution reports using ExtentReports, including pass/fail results for test case.
- Configuration Management: Uses a configuration class to handle base URLs and report paths, which can be easily adjusted for different environments.
- Error Handling: Handles scenarios such as unrecognized JSON fields gracefully.
## Tech Stack

**Java**: The core programming language.

**RestAssured**: For HTTP request handling and API testing

**TestNG**: Used as the testing framework.

**ExtentReports**: For generating detailed and dynamic test execution reports.

**Maven**: Build automation tool for dependency management and test execution.



## Setup and Installation

**Prerequisites**
Make sure you have the following installed:

- Java 8 or higher
- Maven (for building the project)
- Git (for cloning the repository)

**Clone the Repository**

To clone this repository using HTTPS:

```bash
  git clone https://github.com/AkhilSharma07/ToDoAPIValidatorAssessment.git
```
**Building the Project**

Navigate to the project directory and use Maven to build the project:

```bash
  cd ToDoAPIValidatorAssessment
  mvn clean install
```

This will download all necessary dependencies and compile the project.





## Running Tests

To run tests, run the following command

```bash
  mvn test
```

This will run all the test cases defined in the project, and the test reports will be generated in the Reports directory.

**Test Report**

After running the tests, a detailed report will be generated in the Reports directory.

- Report Path: Reports/FanCodeTestResults_[timestamp].html

Open the .html file in your browser to view the test results.




## Project Structure

```bash
  src/
├── main/
│   └── java/
│       ├── com/
│       │   └── fancode/
│       │       ├── common/
│       │       │   └── TestBase.java          # Base class for test setup and teardown
│       │       ├── config/
│       │       │   └── Config.java            # Configuration settings for API base URI and reports
│       │       ├── endpoints/
│       │       │   └── Endpoints.java         # Stores API endpoints as constants
│       │       ├── model/
│       │       │   ├── Address.java           # Model class for Address
│       │       │   ├── Geo.java               # Model class for Geo coordinates
│       │       │   ├── Todo.java              # Model class for Todo tasks
│       │       │   └── User.java              # Model class for User details
│       │       └── utils/
│       │           └── CityHelper.java        # Helper methods for geolocation validation and task completion
└── test/
    └── java/
        └── com/
            └── assignment/
                └── todoAPIFanCodeTest/
                    └── ToDoAPIFanCodeTest.java # Main test class for API validation


```

**Key Directories**

- TestBase.java: Provides common setup and teardown functionality for tests, such as initializing RestAssured.
- Config.java: Holds configuration constants like the base URI for the APIs and the directory for storing test reports.
- Endpoints.java: Centralized repository for all API endpoint constants (e.g., /users, /todos).
- Model Classes (User.java, Todo.java, Address.java, Geo.java): Represent the data structures returned by the API.
- CityHelper.java: Contains utility functions to check if a user belongs to FanCode city based on their latitude and longitude, and to calculate the percentage of completed tasks.
- ToDoAPIFanCodeTest.java: The main test class that runs the API tests for validating users in FanCode city.
## Test Scenario

**Scenario: Validate Users from FanCode City**

- Given: A user has todo tasks assigned.
- And: The user belongs to the city FanCode (latitude between -40 and 5, longitude between 5 and 100).
- Then: The percentage of completed tasks for that user should be greater than 50%.

**The framework will:**

- Fetch all users from the /users endpoint.
- Validate if a user falls within the FanCode city boundaries.
- Fetch the todos for each user.
- Calculate the completion percentage for each user's tasks.
- Assert that the completion percentage is greater than 50%.



## Configurable Settings

You can change certain settings like the base URI or the report directory by modifying the Config.java file.

- Base URI: The base URI for the API can be updated in the Config class:
```bash
  public static final String BASE_URI = "http://jsonplaceholder.typicode.com";
```
- Report Path: The directory for storing test reports can also be updated:
```bash
  public static final String REPORT_DIRECTORY = "Reports";
```

## Known Issues

- The project assumes the API returns data in a specific format. If the API changes or includes unexpected fields, you may encounter UnrecognizedPropertyException. You can resolve this by adding @JsonIgnoreProperties in the model classes.
## Future Enhancements

- **Additional API Endpoints:** Extend validation to other endpoints, such as /posts, /comments, or /albums.
- **Parameterized Tests:** Implement data-driven testing to validate users from multiple cities or regions.
- **CI/CD Integration:** Integrate with Jenkins or GitHub Actions to automate test execution as part of the continuous integration pipeline.
## Contributing

If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcomed.
## License

This project is licensed under the MIT License


## Authors

- [@AkhilSharma07](https://github.com/AkhilSharma07)

