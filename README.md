# Selenium BDD Framework - Automation Project

This repository contains a Selenium-based automation framework using Java, Cucumber (BDD), and Maven. It is designed to validate [Selenium's Web Form Demo Page](https://www.selenium.dev/selenium/web/web-form.html) with robust structure, reusability, and CI/CD readiness.

---

## Project Structure

```
Interview/
├── pom.xml                        # Maven project config with all dependencies
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.automation.seleniumwebform
│   │   │       ├── config           # ConfigReader.java loads testdata.properties
│   │   │       ├── hooks            # Hooks.java manages setup/teardown, logging, screenshots
│   │   │       ├── pageobjects      # WebFormPage.java (POM for the form)
│   │   │       ├── utils            # DriverFactory.java, WaitHelper.java, ScreenshotUtil.java
│   │   └── resources
│   │       └── logback.xml          # Logging configuration for SLF4J + Logback
│   ├── test
│   │   ├── java
│   │   │   └── com.automation.seleniumwebform
│   │   │       ├── stepdefs         # Step definition classes
│   │   │       └── testrunner       # TestRunner.java (Cucumber test runner)
│   │   └── resources
│   │       ├── features             # Gherkin feature files
│   │       ├── testdata.properties  # External test data
│   │       └── testfiles            # Uploadable test files
├── logs/                           # Logs created here
└── reports/                        # HTML reports stored here (Extent Reports)
```

---

## Prerequisites

- Java 11+ installed and JAVA\_HOME set
- Maven 3.x installed and in PATH
- Google Chrome browser
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) or any Java IDE
- Internet access (to fetch Maven dependencies)

---

## Configuration

### 1. **testdata.properties** (under `src/test/resources`):

Contains key-value pairs for data-driven testing.

```
name=John Doe
email=john@example.com
password=Password123
date=2024-04-07
color=#ff0000
range=7
file=src/test/resources/testfiles/sample.txt
message=Hello from automation!
```

### 2. **logback.xml** (under `src/main/resources`):

Controls logging levels and output to `logs/automation.log`. Ensure the file starts with this:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/automation.log</file>
        <append>true</append>
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="debug">
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```

Make sure there are **no spaces or BOM** characters before `<?xml...`.

---

## How to Run

### From IntelliJ:

1. Right-click `TestRunner.java` in `testrunner` package.
2. Click **Run**.

### From Terminal:

```bash
mvn test
```

After the run, check:

- `logs/automation.log` for detailed logs
- `reports/` for test report
- `screenshots/` for captured failures

---

## Features Covered

- ✅ Full form submission (text fields, password, email, textarea, color, range, file upload)
- ✅ Element state checks (disabled, readonly, selected)
- ✅ Synchronization via implicit/explicit waits
- ✅ Screenshot capture on failure
- ✅ Logging and reporting with SLF4J, Logback, ExtentReports

---

## CI/CD Ready

- Clean separation of concerns
- `pom.xml` ready for Jenkins/GitHub Actions
- Support for headless mode via Chrome options (configurable)

---

## Screenshots & Reports

- Screenshots saved on failure in `screenshots/`
- ExtentReports HTML output saved in `reports/`

---

##  Notes

- Element selectors are ID-based where possible for stability.
- All test data is externalized for flexibility.
- Easily extendable to cover additional pages or flows.

---

## 📡 API Testing with Rest Assured

This framework can be easily extended to include **API testing** using the [Rest Assured](https://rest-assured.io/) library. This allows you to test both UI and API in a unified project. As I did not have time to carry this out below I have discussed how this can be done

---

### Add Dependencies

Update your `pom.xml` with the following if not already present:

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
```

---

### Recommended Structure

Add the following to your Java test folder:

```
src/test/java/com/automation/seleniumwebform/api/
├── APITestBase.java         # Common API setup (base URI, headers)
├── endpoints/
│   └── UserEndpoints.java   # Endpoint definitions
└── tests/
    └── UserAPITests.java    # Actual API test cases
```

---

### Example: Simple GET API Test

**APITestBase.java**
```java
package com.automation.seleniumwebform.api;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class APITestBase {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://xample.com";
    }
}
```

**UserAPITests.java**
```java
package com.automation.seleniumwebform.api.tests;

import com.automation.seleniumwebform.api.APITestBase;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserAPITests extends APITestBase {

    @Test
    public void getUsers_returns200() {
        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }
}
```

---

### Run API Tests

Run tests via IntelliJ or terminal:
```bash
mvn test
```

If needed, tag your tests using JUnit or organize Cucumber-style scenarios for API flows.

---

### ✅ Benefits
- Reuse logs, reports, and config setup from UI framework
- Share data between UI/API layers
- One-click execution and CI-ready

---


