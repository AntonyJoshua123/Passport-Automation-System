# Passport Automation System

A comprehensive web-based passport application and management system built with Spring Boot following MVC architecture.

## 📋 Project Overview

This system automates the entire passport issuance process, providing online facilities for:
- Passport application submission
- Document management
- Appointment scheduling
- Police verification
- Application status tracking
- Administrative controls

## 🏗️ Architecture

**Design Pattern:** MVC (Model-View-Controller)

### Project Structure

```
passport-automation-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/passport/automation/
│   │   │       ├── PassportAutomationSystemApplication.java
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── ApplicantController.java
│   │   │       │   ├── OfficerController.java
│   │   │       │   ├── PoliceController.java
│   │   │       │   └── AdminController.java
│   │   │       ├── service/
│   │   │       │   ├── UserService.java
│   │   │       │   └── PassportApplicationService.java
│   │   │       ├── repository/
│   │   │       │   ├── UserRepository.java
│   │   │       │   └── PassportApplicationRepository.java
│   │   │       ├── domain/
│   │   │       │   ├── User.java
│   │   │       │   ├── UserRole.java
│   │   │       │   ├── PassportApplication.java
│   │   │       │   ├── ApplicationStatus.java
│   │   │       │   └── VerificationStatus.java
│   │   │       └── config/
│   │   │           └── SecurityConfig.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   ├── applicant/
│   │       │   │   ├── dashboard.html
│   │       │   │   └── apply.html
│   │       │   ├── officer/
│   │       │   ├── police/
│   │       │   └── admin/
│   │       ├── static/
│   │       └── application.properties
│   └── test/
│       └── java/
└── pom.xml
```

## 👥 User Roles

1. **Applicant (Citizen)**
   - Register and login
   - Submit passport application
   - Upload documents
   - Book appointment
   - Track application status

2. **Passport Officer**
   - View applications
   - Verify documents
   - Approve/reject applications
   - Manage system data

3. **Police Verification Officer**
   - View assigned applications
   - Update verification status (Verified/Not Verified)

4. **Administrator**
   - Manage users
   - Generate reports
   - System configuration

## 🛠️ Technology Stack

- **Backend:** Java 21, Spring Boot 3.2.5
- **Frontend:** Thymeleaf, HTML5, CSS3
- **Database:** MySQL 8.0
- **Build Tool:** Maven
- **ORM:** Spring Data JPA / Hibernate
- **Security:** Spring Security
- **Template Engine:** Thymeleaf

## 📦 Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Validation
- MySQL Connector
- Lombok
- Spring Boot DevTools

## 🚀 Getting Started

### Prerequisites

1. **Java Development Kit (JDK) 21**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **MySQL Server 8.0+**
   - Download from: https://dev.mysql.com/downloads/mysql/
   - Or use XAMPP/WAMP for Windows

3. **Visual Studio Code**
   - Download from: https://code.visualstudio.com/

4. **VS Code Extensions**
   - Extension Pack for Java (Microsoft)
   - Spring Boot Extension Pack (VMware)
   - Live Server (Optional, for frontend testing)

### Database Setup

1. **Start MySQL Server**

2. **Create Database** (Optional - will be created automatically)
   ```sql
   CREATE DATABASE passport_db;
   ```

3. **Update Database Credentials**
   - Open `src/main/resources/application.properties`
   - Update the following lines with your MySQL credentials:
   ```properties
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   ```

### Installation Steps

1. **Extract the Project**
   - Extract the `passport-automation-system` folder to your desired location

2. **Open in VS Code**
   - Open VS Code
   - File → Open Folder
   - Select the `passport-automation-system` folder

3. **Wait for Dependencies**
   - VS Code will automatically detect the Maven project
   - Wait for all dependencies to download (check bottom status bar)

4. **Run the Application**

   **Method 1: Using Spring Boot Dashboard**
   - Open Spring Boot Dashboard (left sidebar)
   - Right-click on `passport-automation-system`
   - Click "Run" or "Debug"

   **Method 2: Using Terminal**
   ```bash
   ./mvnw spring-boot:run
   ```

   **Method 3: Using VS Code Run**
   - Open `PassportAutomationSystemApplication.java`
   - Click "Run" above the `main` method
   - Or press `F5`

5. **Access the Application**
   - Open your browser
   - Navigate to: `http://localhost:8080`

## 📱 Usage Guide

### For Applicants

1. **Register**
   - Click "Register" on homepage
   - Fill in personal details
   - Select role as "Applicant"
   - Submit registration

2. **Login**
   - Use registered email and password
   - Click "Login"

3. **Apply for Passport**
   - Click "Apply for Passport" on dashboard
   - Fill in application form
   - Select identity and address proof types
   - Submit application

4. **Track Status**
   - View all applications on dashboard
   - Click "View Details" to see application status
   - Check police verification status
   - View remarks (if any)

### For Officers

1. **Login**
   - Use officer credentials
   - Role: Passport Officer

2. **View Applications**
   - Dashboard shows pending applications
   - View statistics

3. **Process Application**
   - Click on application to view details
   - Approve or reject with remarks
   - Application status updated automatically

### For Police Officers

1. **Login**
   - Use police officer credentials
   - Role: Police Verification Officer

2. **Verify Applications**
   - View assigned applications
   - Update verification status
   - Mark as Verified/Not Verified

### For Administrators

1. **Login**
   - Use admin credentials
   - Role: Administrator

2. **Manage System**
   - View all users
   - View all applications
   - Generate reports
   - Manage user access

## 🔐 Default Test Users

After first run, you can create test users through registration or add them directly to database:

```sql
INSERT INTO users (name, date_of_birth, address, email, mobile_number, password, role, active, created_date)
VALUES 
('John Applicant', '1990-01-01', '123 Main St', 'applicant@test.com', '9876543210', 'password123', 'APPLICANT', true, CURDATE()),
('Sarah Officer', '1985-05-15', '456 Oak Ave', 'officer@test.com', '9876543211', 'password123', 'PASSPORT_OFFICER', true, CURDATE()),
('Mike Police', '1982-08-20', '789 Pine Rd', 'police@test.com', '9876543212', 'password123', 'POLICE_OFFICER', true, CURDATE()),
('Admin User', '1980-03-10', '321 Admin St', 'admin@test.com', '9876543213', 'password123', 'ADMINISTRATOR', true, CURDATE());
```

## 🐛 Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   - Change port in `application.properties`:
   ```properties
   server.port=8081
   ```

2. **Database connection error**
   - Ensure MySQL is running
   - Check username/password in `application.properties`
   - Verify database exists or enable auto-creation

3. **Dependencies not downloading**
   - Check internet connection
   - Delete `.m2/repository` folder and reload
   - Run: `./mvnw clean install`

4. **Application not starting**
   - Check Java version: `java -version`
   - Ensure JDK 21 is installed
   - Check for errors in console/terminal

## 📊 Database Schema

### Users Table
- userId (Primary Key)
- name
- dateOfBirth
- address
- email (Unique)
- mobileNumber
- password
- role
- active
- createdDate

### Passport Applications Table
- applicationId (Primary Key)
- userId (Foreign Key)
- fullName
- dateOfBirth
- gender
- address
- city
- state
- pincode
- identityProof
- addressProof
- status
- appointmentDate
- appointmentTime
- policeVerificationStatus
- remarks
- submittedDate
- lastUpdatedDate

## 🔄 Application Flow

1. Citizen registers on the platform
2. Citizen submits passport application
3. Application status: SUBMITTED
4. Police officer verifies application
5. Verification status: VERIFIED/NOT_VERIFIED
6. Passport officer reviews application
7. Officer approves/rejects application
8. Final status: APPROVED/REJECTED
9. Citizen receives notification

## 📝 Features

✅ User registration and authentication
✅ Role-based access control
✅ Online passport application
✅ Document upload management
✅ Appointment scheduling
✅ Real-time status tracking
✅ Police verification workflow
✅ Officer approval system
✅ Administrative dashboard
✅ Responsive design
✅ Form validation
✅ Secure data handling

## 🎯 Future Enhancements

- Email/SMS notifications
- File upload for documents
- Payment gateway integration
- QR code generation
- Multi-language support
- Mobile application
- Advanced reporting
- Document OCR verification

## 👨‍💻 Development

### Running in Development Mode

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Building for Production

```bash
./mvnw clean package
java -jar target/passport-automation-system-1.0.0.jar
```

### Running Tests

```bash
./mvnw test
```

## 📄 License

This is an academic project developed for learning purposes.

## 👤 Author

**Student:** Antony Joshua P  
**Roll No:** 311123205006  
**Institution:** LICET (Loyola-ICAM College of Engineering and Technology)

## 📞 Support

For issues or questions:
- Check the troubleshooting section
- Review application logs in console
- Verify database connection
- Ensure all dependencies are loaded

## 🙏 Acknowledgments

- Spring Boot Documentation
- Thymeleaf Documentation
- LICET Faculty for guidance
- Reference: Exp_2_Devops.pdf

---

**Happy Coding! 🚀**
