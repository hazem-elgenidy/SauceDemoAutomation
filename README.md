# SauceDemoAutomation

[![UI tests](https://github.com/hazem-elgenidy/SauceDemoAutomation/actions/workflows/tests.yml/badge.svg)](https://github.com/hazem-elgenidy/SauceDemoAutomation/actions/workflows/tests.yml)

UI test automation for [saucedemo.com](https://www.saucedemo.com) (the "Swag Labs" demo store), written in **Java** with **Selenium WebDriver** and **TestNG**, structured with the **Page Object Model**, and using **JSON test data** for login scenarios.

The suite runs automatically in headless Chrome with **GitHub Actions** on every push to `main` and every pull request.

## What is tested

8 tests across login, the inventory page and the shopping cart:

| Test class | Test | What it checks |
|---|---|---|
| `LoginTest` | `verifySuccessfulLogin` | A valid user lands on the inventory page |
| | `verifyUnsuccessfulLogin` | Invalid credentials show the "Epic sadface" error |
| | `verifyLoginWithoutPassword` | An empty password shows "Password is required" |
| `InventoryTest` | `verifyInventoryPageElements` | Page title is "Swag Labs", the cart icon is visible, and 6 products are listed |
| `CartTest` | `testCartEmpty` | The cart starts empty |
| | `testAddThreeProducts` | Three added products appear in the cart |
| | `testRemoveOneProduct` | A product removed in the cart shows "Add to cart" again; the others still show "Remove" |
| | `testSocialIcons` | The footer LinkedIn, Facebook and Twitter/X links open the right sites in new tabs |

## Tech stack

| | |
|---|---|
| Language | Java 24 |
| Browser automation | Selenium WebDriver 4.38.0 |
| Test framework | TestNG 7.10.2 |
| Build | Maven |
| Test data | JSON, read with Gson 2.10.1 |
| CI | GitHub Actions (headless Chrome) |

## Project structure

```
SauceDemoAutomation/
├── pom.xml
├── .github/workflows/tests.yml     # CI: runs the suite on GitHub Actions
└── src/
    ├── main/java/
    │   ├── Pages/                  # Page Objects
    │   │   ├── LoginPage.java
    │   │   ├── InventoryPage.java
    │   │   └── CartPage.java
    │   └── Utils/
    │       └── DataDriven.java     # reads the JSON test data
    └── test/
        ├── TestData.json           # login data: valid, invalid, username only
        └── java/
            ├── BaseTest.java       # opens a fresh Chrome before each test, closes it after
            ├── LoginTest.java
            ├── InventoryTest.java
            └── CartTest.java
```

## Design notes

- **Page Object Model:** locators and page actions live in `Pages/`; tests only describe behaviour.
- **Test isolation:** `BaseTest` starts a new browser before every test (`@BeforeMethod`) and quits it after (`@AfterMethod`), so tests don't depend on each other.
- **Data-driven login:** credentials come from `src/test/TestData.json`, not from the test code.
- **CI-ready:** Chrome runs headless automatically on CI (when the `CI` environment variable is set), and Chrome's password-manager pop-ups are disabled so they can't block the tests.

## Running the tests locally

Requirements: **Java 24+**, **Maven** and **Google Chrome**. Selenium downloads the matching ChromeDriver automatically, so no manual driver setup is needed.

```bash
git clone https://github.com/hazem-elgenidy/SauceDemoAutomation.git
cd SauceDemoAutomation
mvn test
```

Run a single class:

```bash
mvn test -Dtest=LoginTest
```

Run without opening a browser window:

```bash
mvn test -Dheadless=true
```

TestNG reports are written to `target/surefire-reports/`. On GitHub, each CI run also saves them as a downloadable `test-reports` artifact.

## Possible next steps

- Checkout flow tests
- API tests with REST Assured
- Allure reporting
- Cross-browser runs (Firefox, Edge)

## Author

Hazem Elgenidy, QA Engineer · [LinkedIn](https://www.linkedin.com/in/hazem-elgenidy-6a7334181)
