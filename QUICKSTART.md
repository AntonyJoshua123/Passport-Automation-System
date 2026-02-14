# Quick Start Guide - Passport Automation System

## ⚡ Fast Setup (5 Minutes)

### Step 1: Prerequisites Check
```bash
# Check Java version (should be 21+)
java -version

# Check if MySQL is running
mysql --version
```

### Step 2: Database Setup
1. Start MySQL Server (XAMPP/MySQL Workbench/Command line)
2. The database will be created automatically when you run the app
3. **OR** manually create it:
```sql
CREATE DATABASE passport_db;
```

### Step 3: Configure Database Connection
1. Open `src/main/resources/application.properties`
2. Update these lines (if needed):
```properties
spring.datasource.username=root
spring.datasource.password=your_password_here
```

### Step 4: Open Project in VS Code
1. Open Visual Studio Code
2. File → Open Folder
3. Select `passport-automation-system` folder
4. Wait for Java extensions to load (bottom right corner)

### Step 5: Run the Application

**Option A - Using Spring Boot Dashboard:**
1. Look for Spring Boot Dashboard icon in left sidebar
2. Find `passport-automation-system` under APPS
3. Click ▶️ Run button

**Option B - Using Main File:**
1. Open `src/main/java/com/passport/automation/PassportAutomationSystemApplication.java`
2. Look for "Run | Debug" text above `public static void main`
3. Click "Run"

**Option C - Using Terminal:**
```bash
# Windows
mvnw.cmd spring-boot:run

# Mac/Linux
./mvnw spring-boot:run
```

### Step 6: Access the Application
1. Wait for the application to start (look for "Started PassportAutomationSystemApplication")
2. Open browser
3. Go to: **http://localhost:8080**

## 🎯 First Time Usage

### Create Your First User
1. Click "Register" on homepage
2. Fill in details:
   - Name: Your Name
   - Date of Birth: Select date
   - Address: Your address
   - Email: your.email@example.com
   - Mobile: 9876543210
   - Password: password123
   - Role: **Applicant** (for testing)
3. Click "Register"

### Login
1. Click "Login"
2. Enter email and password
3. Click "Login"

### Submit Application
1. After login, click "Apply for Passport"
2. Fill the application form
3. Submit
4. View your application on dashboard

## 🔧 Common Issues & Quick Fixes

### Port Already in Use
If you see "Port 8080 is already in use":
1. Open `application.properties`
2. Add: `server.port=8081`
3. Restart application
4. Access at: `http://localhost:8081`

### Database Connection Error
```
Error: Cannot connect to database
```
**Fix:**
1. Ensure MySQL is running
2. Check username/password in `application.properties`
3. Try creating database manually

### Dependencies Not Loading
```
Error: Could not resolve dependencies
```
**Fix:**
1. Press `Ctrl + Shift + P`
2. Type "Java: Clean Java Language Server Workspace"
3. Reload VS Code
4. Wait for dependencies to download

### Application Won't Start
**Fix:**
1. Check console/terminal for errors
2. Ensure Java 21 is installed
3. Try: `mvnw clean install`
4. Then run again

## 📱 Test the System

### Test Scenario 1: Applicant Journey
1. Register as Applicant
2. Login
3. Submit passport application
4. Track status on dashboard

### Test Scenario 2: Officer Workflow
1. Register as Passport Officer
2. Login to officer dashboard
3. View pending applications
4. Approve/Reject application

### Test Scenario 3: Police Verification
1. Register as Police Officer
2. View applications needing verification
3. Update verification status

## 🎓 Learning the Code

### Key Files to Understand:
1. **Controllers** (`controller/` folder)
   - Handle web requests
   - Route to appropriate pages

2. **Services** (`service/` folder)
   - Business logic
   - Data processing

3. **Repositories** (`repository/` folder)
   - Database operations
   - Data access layer

4. **Models/Domain** (`domain/` folder)
   - Entity classes
   - Database table structures

5. **Templates** (`templates/` folder)
   - HTML pages
   - User interface

## 💡 Tips for VS Code

### Useful Shortcuts:
- `Ctrl + P`: Quick file search
- `Ctrl + Shift + F`: Search in all files
- `F5`: Debug application
- `Ctrl + C`: Stop application (in terminal)

### Spring Boot Dashboard:
- Green icon = Running
- Click ⏹️ to stop
- Right-click for more options

## 📊 Verify Everything Works

After starting, check:
- ✅ Application starts without errors
- ✅ Can access http://localhost:8080
- ✅ Can register new user
- ✅ Can login successfully
- ✅ Can submit application
- ✅ Data is saved in database

## 🆘 Need Help?

1. **Check README.md** - Detailed documentation
2. **Check Console/Terminal** - Look for error messages
3. **Verify Database** - Ensure MySQL is running
4. **Restart** - Sometimes a simple restart fixes issues

## 🚀 Next Steps

Once basic setup works:
1. Create multiple user types (Applicant, Officer, Police, Admin)
2. Test complete workflow
3. Explore the code structure
4. Customize templates
5. Add new features

---

**Ready to go? Start with Step 1! 🎉**
