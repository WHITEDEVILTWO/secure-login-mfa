# Secure Login System with JWT and MFA (Spring Boot + Gradle)

This project is a secure web-based login system built with **Spring Boot**, using **JWT (JSON Web Token)** for authentication and **MFA (Multi-Factor Authentication)** via email-based OTP.

## 💡 Features

- ✅ Login with username & password (stored credentials)
- 🔐 Secure authentication using Spring Security
- 🛡️ JWT token generation & validation
- 📩 MFA using OTP sent to user's email
- 📧 OTP-based email validation before final login
- 🚫 Access restricted without email OTP verification
- 🌐 REST APIs for login, OTP validation, and user access
- 🧪 Postman collection testing support

## 🛠️ Technologies

- Java 17+
- Spring Boot 3+
- Spring Security
- JWT (jjwt)
- JavaMailSender
- Gradle
- Postman (for API testing)

---

## ⚙️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/secure-login-mfa.git
cd secure-login-mfa
```

### 2. Configure Email in `application.properties`

If using Gmail:

```properties
#Email otp config
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=ganesh.repalle949@gmail.com
spring.mail.password=vlxijkwdynflejwb
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=tru
```

➡️ Generate an [App Password](https://support.google.com/accounts/answer/185833) from your Google account (after enabling 2FA).

### 3. Build and Run

```bash
./gradlew clean build
./gradlew bootRun
```

---

## 📮 API Endpoints

### 1. `POST /login`
Authenticate with username & password. Triggers OTP email.

**Body:**
```json
{
  "username": "ganesh",
  "password": "your_password"
}
```

➡️ Response: "OTP sent to your email"

---

### 2. `POST /validate-otp`
Validate OTP and receive the JWT token.

**Body:**
```json
{
  "username": "ganesh",
  "otp": "123456"
}
```

➡️ Response: JWT token if OTP is valid.

---

### 3. `GET /dashboard`
Access protected page with JWT in header.

**Headers:**
```
Authorization: Bearer <your-jwt-token>
```

---

## 🧪 Testing via Postman

1. 🔑 Send `POST /login` request.
2. 📬 Check your email for OTP.
3. ✅ Send `POST /validate-otp` with received OTP.
4. 🔓 Use returned token to access `GET /dashboard`.

---

## 📁 Folder Structure

```
src
├── main
│   ├── java/com/example/security
│   │   ├── controller
│   │   ├── config
│   │   ├── service
│   │   ├── filter
│   │   └── model
│   └── resources
│       ├── application.properties
```

---

## 📌 Note

- Ensure SMTP settings are valid for email sending.
- OTP is stored temporarily in memory (use Redis in production).
- Adjust expiration, retries, and token secret via `application.properties`.

---

## 👨‍💻 Author

Ganesh Babu REPALLE  
📧 ganesh.repalle949@gmail.com