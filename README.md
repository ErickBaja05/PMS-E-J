# PMS-E-J
Proyecto de desarollo del Sistema de Gestion para la Farmacia Santa Anita E&amp;J


## ⚙️ Execution Instructions

To run this application locally, please follow the steps below carefully:

---

### 1️ Install PostgreSQL

Make sure PostgreSQL is installed on your system.

If you don’t have it installed, download it from the official website:  
https://www.postgresql.org/download/

---

### 2️ Create the PostgreSQL User

Open **pgAdmin** or the PostgreSQL command line and create the required user:

```sql
CREATE USER postgres WITH PASSWORD '1234';
ALTER USER postgres WITH SUPERUSER;
```
---
### 3️ Create and Restore the Database

Create the database named **farmacia** on port **5432**:

```bash
createdb -U postgres -p 5432 farmacia
```
Restore the provided backup file in the database_backup folder using:

```bash
pg_restore -U postgres -p 5432 -d farmacia -v "C:\Users\YourUser\Desktop\farmacia.backup"
```
---
### 4 Create and Restore the Database

Create the database named **farmacia** on port **5432**:

```bash
createdb -U postgres -p 5432 farmacia
```
Restore the provided backup file in the database_backup folder using:

```bash
pg_restore -U postgres -p 5432 -d farmacia -v "C:\Users\YourUser\Desktop\farmacia.backup"
```
---

### 5 Create and Restore the Database

Clone this repository to your Desktop (or any preferred directory):

```bash
git clone https://github.com/ErickBaja05/PMS-E-J
```
### 6 Open the project in and IDE and run the main class

Open the project in your preferred IDE:
IntelliJ IDEA
NetBeans
Eclipse
VS Code
Locate the MainApp class of the project. It is placed in the route: src/main/java/com/grupo2/PMSEYJ/app/MainApp.java
Execute the application from the IDE.

## 🗄️ Database Configuration (Required)

Before running the application, ensure your PostgreSQL environment matches the connection parameters expected by the system.

The application is configured to connect using the following credentials:

```properties
# Database configuration
db.url=jdbc:postgresql://localhost:5432/farmacia
db.user=postgres
db.password=1234


