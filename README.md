# 🛠️ JDBC Database Manager

Welcome to a Java-powered backend adventure! 🌍  
This project is a practical implementation of **JDBC** for managing database connections, running SQL operations, and handling exceptions with style (and a few well-placed `try-catch` blocks). Built for learning and expanding backend skills while keeping your data secure and your sanity intact!

---

## 📂 Project Overview

- ✅ Connect to your SQL database using `db.properties`
- 🔍 Run `SELECT` queries and display results
- ✍️ Insert new data into your database
- 🧾 Update values in tables
- 🗑️ Delete records when necessary
- 🔄 Perform transactions with rollback safety

---

## 📚 Technologies

- 💻 **Java SE**
- 🗃️ **JDBC**
- ⚙️ **SQL (MySQL, PostgreSQL, etc.)**
- 🧩 **Exception Handling**
- 🧠 **Object-Oriented Design**

---

## 🧠 Key Concepts

### 📡 Database Connection
```java
Properties props = loadProperties();
String url = props.getProperty("dburl");
conn = DriverManager.getConnection(url, props);
```

### 📊 Running Queries
```java
Statement st = conn.createStatement();
ResultSet rs = st.executeQuery("SELECT * FROM department");
```

### 📝 Inserting Data
```java
preSt.setString(1, "Carl Purple");
preSt.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
```

### 💸 Updating Salaries
```java
preSt.setDouble(1, 200.0); // Give everyone a raise! 🎉
```

### ❌ Deleting Records
```java
preSt.setInt(1, 5); // Say goodbye to department #5 👋
```

### 🔄 Transaction Handling
```java
conn.setAutoCommit(false); // Let's play it safe
conn.commit(); // If all is well
conn.rollback(); // If chaos ensues
```

---

## ⚠️ Error Handling

Custom exception classes:
- `DbException`: generic database errors
- `DbIntegrityException`: for integrity violations

These keep your code clean and your debugging stress low 💆‍♂️

---

## 🙋 About the Author

I'm passionate about writing backend logic that’s reliable, clean, and scalable.  
This project is part of my journey to master database management with Java.  
Feel free to explore, fork, or suggest improvements. Let's build stronger systems together! 🚀

---

## 📄 License

MIT License — feel free to clone, learn, improve, and share 💚

```

Ready to jazz it up with some badges, install instructions, or a visual data flow diagram? I’d love to help tailor this even more to your style 😄📘
```
