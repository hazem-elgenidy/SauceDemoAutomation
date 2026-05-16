# 🧪 SauceDemoAutomation

Professional end-to-end test automation framework for the SauceDemo web application using Selenium WebDriver, TestNG, and the Page Object Model design pattern.

## 📋 Overview

This project demonstrates comprehensive automation testing of the SauceDemo e-commerce platform. It showcases best practices in test automation including data-driven testing with JSON, parameterized tests, and structured test case organization.

## ✨ Features

- **End-to-End Testing**: Complete user journey automation (login → browse products → add to cart → checkout)
- **Page Object Model**: Maintainable and scalable test structure with separated page objects
- **Data-Driven Testing**: JSON-based test data for login credentials and product validation
- **TestNG Framework**: Advanced test organization with suites, groups, and parameterization
- **Cross-Browser Ready**: Configured for Chrome; easily extensible to Firefox, Edge, Safari
- **Error Handling**: Comprehensive assertion validation and exception handling
- **Professional Reporting**: TestNG-generated HTML reports for test execution results

## 🎯 Test Coverage

| Module | Test Cases | Status |
|--------|-----------|--------|
| Authentication (Login) | 5+ | ✅ |
| Product Browsing | 4+ | ✅ |
| Shopping Cart | 3+ | ✅ |
| Checkout Flow | 3+ | ✅ |
| **Total** | **15+** | **✅** |

## 🛠️ Tech Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java 24 |
| **Automation Tool** | Selenium WebDriver 4.38.0 |
| **Testing Framework** | TestNG 7.10.2 |
| **Build Tool** | Maven 3.6.3+ |
| **Test Data** | JSON (GSON 2.10.1) |
| **Design Pattern** | Page Object Model (POM) |
| **IDE** | IntelliJ IDEA, Eclipse, VS Code |

## 📦 Installation & Setup

### Prerequisites
- **Java Development Kit (JDK)**: Java 24 or higher
- **Maven**: 3.6.3 or higher
- **Google Chrome**: Latest version
- **ChromeDriver**: Matching your Chrome version ([Download](https://googlechromelabs.github.io/chrome-for-testing/))

### Step 1: Clone the Repository
```bash
git clone https://github.com/hazem-elgenidy/SauceDemoAutomation.git
cd SauceDemoAutomation
```

### Step 2: Install Dependencies
```bash
mvn clean install
```

### Step 3: Configure ChromeDriver
**Option A**: Add ChromeDriver to system PATH
```bash
# Verify ChromeDriver is in PATH
chromedriver --version
```

**Option B**: Update pom.xml with your ChromeDriver path
```bash
mvn -Dwebdriver.chrome.driver="C:/path/to/chromedriver.exe" clean test
```

### Step 4: Run Tests
```bash
# Run all tests
mvn clean test

# Run specific test class
mvn -Dtest=LoginTest test

# Run tests by group
mvn -Dtest=*Test -Dgroups="smoke" test
```

## 🚀 Quick Start Example

```java
// Example: Login Test using Page Object Model
@Test
public void testValidLogin() {
    LoginPage loginPage = new LoginPage(driver);
    
    // Use JSON test data
    loginPage.login("standard_user", "secret_sauce");
    
    // Verify successful login
    assert driver.getCurrentUrl().contains("inventory");
}
```

## 📁 Project Structure

```
SauceDemoAutomation/
├── pom.xml                          # Maven configuration with dependencies
├── README.md                        # This file
├── .gitignore
├── src/
│   ├── main/java/
│   │   ├── pages/                  # Page Object classes
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   ├── utils/                  # Utility classes
│   │   │   ├── DriverFactory.java  # WebDriver singleton
│   │   │   └── JsonDataReader.java # JSON test data parser
│   │   └── config/                 # Configuration files
│   │       └── AppConfig.java
│   │
│   └── test/java/
│       ├── tests/                  # Test classes
│       │   ├── LoginTest.java
│       │   ├── ShoppingTest.java
│       │   ├── CheckoutTest.java
│       │   └── BaseTest.java       # Test base class
│       └── testdata/               # Test data files
│           └── testData.json
│
└── target/                         # Build output & test reports
    └── surefire-reports/           # TestNG reports (HTML)
```

## 🧪 Test Design Patterns

### **Page Object Model (POM)**
Each page of the application has a corresponding Page Object class:
```java
public class LoginPage {
    private WebDriver driver;
    
    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    
    // Methods
    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
```

### **Data-Driven Testing with JSON**
Test data is externalized for easy maintenance:
```json
{
  "users": [
    {"username": "standard_user", "password": "secret_sauce", "status": "valid"},
    {"username": "invalid_user", "password": "wrong_pass", "status": "invalid"}
  ]
}
```

## 📊 Test Execution & Reports

After running tests, view results:
```bash
# Open TestNG HTML report
target/surefire-reports/index.html
```

Report includes:
- ✅ Passed/Failed test counts
- ⏱️ Execution duration
- 📈 Test trends
- 🔍 Detailed step logs
- 📸 Screenshots (if implemented)

## 🔧 Configuration

### Modify Test Properties
Update `src/main/java/config/AppConfig.java`:
```java
public class AppConfig {
    public static final String APP_URL = "https://www.saucedemo.com";
    public static final long WAIT_TIME = 10;
    public static final String BROWSER = "chrome";
}
```

### TestNG Groups (Selective Execution)
```java
@Test(groups = "smoke")
public void testLoginSmoke() { ... }

@Test(groups = "regression")
public void testLoginRegression() { ... }
```

Run specific group:
```bash
mvn clean test -Dgroups="smoke"
```

## 📖 Documentation

### Test Case Examples
| Test ID | Title | Description | Expected Result |
|---------|-------|-------------|-----------------|
| TC_001 | Valid Login | Login with correct credentials | User redirected to inventory page |
| TC_002 | Invalid Login | Login with wrong credentials | Error message displayed |
| TC_003 | Add to Cart | Add product to cart | Product count increases |
| TC_004 | Complete Checkout | Complete purchase flow | Order confirmation displayed |

## 🤝 Contributing

We welcome contributions! Follow these steps:

1. **Fork** the repository
2. **Create a feature branch**:
   ```bash
   git checkout -b feature/new-tests
   ```
3. **Make your changes** and add tests
4. **Commit** with clear messages:
   ```bash
   git commit -m "Add tests for checkout flow"
   ```
5. **Push** to your fork:
   ```bash
   git push origin feature/new-tests
   ```
6. **Create a Pull Request** with description

### Code Style Guidelines
- Follow Java naming conventions (camelCase for methods/variables, PascalCase for classes)
- Keep methods small and focused (Single Responsibility Principle)
- Add meaningful comments for complex logic
- Use meaningful variable names (avoid x, y, temp)
- Maintain consistent indentation (4 spaces)

## 🐛 Known Issues & Limitations

- Currently supports Chrome browser only (Firefox/Edge support planned)
- Requires stable internet connection for SauceDemo access
- No headless browser mode configured (can be added)

## 🚀 Future Enhancements

- [ ] Add Allure reporting for better visualizations
- [ ] Implement headless browser testing
- [ ] Add API testing with REST Assured
- [ ] GitHub Actions CI/CD integration
- [ ] Multi-browser cross-browser testing
- [ ] Performance testing capabilities
- [ ] Video recording of test execution

## 📞 Support & Contact

- **Issues**: Report bugs via GitHub Issues
- **Questions**: Create a Discussion or reach out via LinkedIn
- **Author**: Hazem Elgenidy | QA Automation Engineer

## 📜 License

This project is open source and available under the MIT License.

---

**Happy Testing! 🎉**

*Last Updated: May 2026*
