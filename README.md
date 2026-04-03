# 📌 QR Attendance Management System

## 📖 Description
The QR Attendance Management System is a Spring Boot-based backend application designed to automate student attendance using QR codes. The system allows students to mark attendance by scanning QR codes, while administrators can manage students, attendance records, and leave requests efficiently.

---

## 🚀 Features

### 🎓 Student Management
- Add new student
- View all students
- Get student by ID
- Update student details
- Delete student

### 📷 QR Code Attendance
- Generate QR codes for attendance
- Mark attendance using QR scan
- Track attendance records

### 📅 Attendance Management
- View attendance by student
- Manage daily attendance records

### 📝 Leave Request System
- Apply for leave
- Approve / Reject leave requests
- Track leave status

---

## 🛠️ Tech Stack
- Backend: Spring Boot  
- Language: Java  
- Database: MySQL / H2  
- ORM: Spring Data JPA (Hibernate)  
- Build Tool: Maven  
- Libraries:
  - Lombok
  - REST APIs
  - QR Code Generator

---

## 📁 Project Structure
```
QR_Attendence_Management_System/
│── src/main/java/com/harsh/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── exceptions/
│   └── main application file
│
│── src/main/resources/
│   └── application.properties
│
│── pom.xml
```

---

## 🔗 API Endpoints

### 🎓 Student APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /students | Create student |
| GET | /students | Get all students |
| GET | /students/{id} | Get student by ID |
| PUT | /students/{id} | Update student |
| DELETE | /students/{id} | Delete student |

---

### 📷 Attendance APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /attendance/mark | Mark attendance |
| GET | /attendance/student/{id} | Get attendance |

---

### 📝 Leave APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /leave/apply | Apply leave |
| GET | /leave/all | Get all leaves |
| PUT | /leave/{id} | Update leave status |

---

### 🔳 QR APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /qr/generate/{studentId} | Generate QR |

---

## ▶️ How to Run

### 🔧 Prerequisites
- Java 17+
- Maven
- MySQL (optional)

---

### 🧪 Steps

1. Clone repo:
```bash
git clone https://github.com/your-username/QR_Attendence_Management_System.git
```

2. Go to folder:
```bash
cd QR_Attendence_Management_System
```

3. Configure DB:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/attendance_db
spring.datasource.username=root
spring.datasource.password=yourpassword
```

4. Run project:
```bash
mvn spring-boot:run
```

5. Open:
http://localhost:8080

---

## 👤 Author

CodeWithHarshad01
Java Backend Developer  
Spring Boot Enthusiast  

---

## 📌 Future Improvements
- Add JWT Authentication
- Add React Frontend
- Add Swagger Documentation
- Role-based access control
