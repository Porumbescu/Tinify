<h1 align="center">Tinify - A Streamlined URL Shortening Tool</h1>
<p align="center">
    <img src="https://img.shields.io/github/repo-size/Porumbescu/tinify">
    <img src="https://img.shields.io/github/languages/top/Porumbescu/tinify">
    <img src="https://img.shields.io/github/last-commit/Porumbescu/tinify">
    <img src="https://img.shields.io/github/license/Porumbescu/tinify">
</p>

**Tech Stack:**
- **Backend**: <img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/Spring%20Boot-green?style=flat-square&logo=springboot">
- **Database**: <img src="https://img.shields.io/badge/PostgreSQL-blue?style=flat-square&logo=postgresql"> <img src="https://img.shields.io/badge/Hibernate-black?style=flat-square&logo=hibernate">
- **Frontend**: <img src="https://img.shields.io/badge/JavaScript-yellow?style=flat-square&logo=javascript"> <img src="https://img.shields.io/badge/React-blue?style=flat-square&logo=react">

## 🚀 API Endpoints

<br>

### 🔗 Shorten URL

- **Endpoint**: `/api/tinify/shorten`
- **HTTP Method**: `POST`

- **Payload**:
```json
{
   "originalUrl": "https://www.example.com"
}
```
- **Description**: Accepts a lengthy URL and returns its shortened form. If the provided URL is either invalid or inaccessible, an error will be thrown. The function first verifies if the URL is already present in the database. If so, its existing shortened code will be returned; otherwise, a new code will be generated and stored alongside the original URL in the database.

<br>

### 🔍 Redirect to Original URL

- **Endpoint**: `/{shortCode}`
- **HTTP Method**: `GET`
- **Parameter**:
  - **shortCode**: Represents the shortened version of an original URL.
- **Description**: Given a `shortCode`, the system fetches the associated original URL and redirects the user to that URL. If no match is found, a 404 Not Found response is triggered.

