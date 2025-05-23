# 📘 University REST API - Professors & Students Management

A modern, clean, and robust RESTful API for managing professors and students at a university.  
Built with **Spring Boot**, this project showcases backend API development using best practices, with CRUD endpoints and clean architecture.

---

## 🚀 Features

- 📄 **CRUD operations** for `Prof` and `Student` entities
- 🔍 Fetch, insert, update, and delete operations via RESTful endpoints
- 🧪 Ready for integration with frontend or mobile clients
- 💾 In-memory or persistent DB support (H2/MySQL ready)
- ⚡ JSON-based communication with fast and intuitive endpoints

---

## 📦 Technologies Used

- ⚙️ Spring Boot
- 🌐 Spring Web / REST
- 🗃️ JPA / Hibernate
- 📚 Maven
- 🧪 JUnit (Optional)
- 🔧 Postman / Swagger for testing

---

## 📁 Project Structure


---

## 🔗 Sample API Endpoints

| Method | Endpoint            | Description                 |
|--------|---------------------|-----------------------------|
| GET    | `/profs`            | List all professors         |
| POST   | `/profs`            | Add one or many professors  |
| GET    | `/students`         | List all students           |
| POST   | `/students`         | Add a student               |
| DELETE | `/profs/{id}`       | Delete a professor by ID    |
| PUT    | `/students/{id}`    | Update student info         |

---

## 📥 Sample JSON (Insert 10 Profs)

```jsong
[
  { "id": 1, "email": "karim.elamrani@univ.ma", "firstName": "Karim", "lastName": "El Amrani" },
  { "id": 2, "email": "amina.zahraoui@univ.ma", "firstName": "Amina", "lastName": "Zahraoui" },
  { "id": 3, "email": "rachid.bennani@univ.ma", "firstName": "Rachid", "lastName": "Bennani" },
  { "id": 4, "email": "laila.karrouchi@univ.ma", "firstName": "Laila", "lastName": "Karrouchi" },
  { "id": 5, "email": "youssef.elmoutaouakil@univ.ma", "firstName": "Youssef", "lastName": "El Moutaouakil" },
  { "id": 6, "email": "nawal.fikri@univ.ma", "firstName": "Nawal", "lastName": "Fikri" },
  { "id": 7, "email": "hamza.aittaleb@univ.ma", "firstName": "Hamza", "lastName": "Ait Taleb" },
  { "id": 8, "email": "salma.belkadi@univ.ma", "firstName": "Salma", "lastName": "Belkadi" },
  { "id": 9, "email": "mehdi.ouazzani@univ.ma", "firstName": "Mehdi", "lastName": "Ouazzani" },
  { "id": 10, "email": "fatima.zerouali@univ.ma", "firstName": "Fatima", "lastName": "Zerouali" }
]
