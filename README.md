<h1 align="left">🛒 E-Commerce Shopping Website</h1>

<p align="left">
A <b>full-stack E-Commerce web application</b> built using <b>Spring Boot</b> and <b>Thymeleaf</b>, featuring session-based authentication, product management, cart flow, and order handling.
</p>

---

<h2 align="left">🚀 Key Features</h2>

<p align="left">
👤 Session-based User Login & Registration<br>
🛍 Product listing with categories<br>
🛒 Add to Cart & Cart management<br>
📦 Order placement & order history<br>
🔐 Secure session handling (no JWT)<br>
📊 Admin-ready backend structure<br>
🧩 Dynamic data stored in MySQL
</p>

---

<h2 align="left">🧱 Tech Stack</h2>

<p align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Session%20Based%20Auth-3F51B5?style=for-the-badge"/>
</p>

<p align="left">
  <img src="https://img.shields.io/badge/JPA%20%26%20Hibernate-59666C?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/MySQL-00758F?style=for-the-badge&logo=mysql&logoColor=white"/>
</p>

<p align="left">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5"/>
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3"/>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"/>
</p>

---

<h2 align="left">🛠️ IDEs & Tools</h2>

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" height="40" alt="intellij"/>
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/eclipse/eclipse-original.svg" height="40" alt="eclipse"/>
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vscode/vscode-original.svg" height="40" alt="vscode"/>
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" height="40" alt="mysql"/>
</div>

---

<h2 align="left">📁 Project Structure</h2>

<pre>
src/main/java/com/ecommerce/
├── EcommerceApplication.java
├── controller/
│   ├── AuthController.java
│   ├── ProductController.java
│   ├── CartController.java
│   └── OrderController.java
├── model/
│   ├── User.java
│   ├── Product.java
│   ├── Cart.java
│   └── Order.java
├── repository/
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   └── OrderRepository.java
├── service/
│   ├── UserService.java
│   ├── ProductService.java
│   └── OrderService.java

src/main/resources/templates/
├── login.html
├── register.html
├── products.html
├── cart.html
└── orders.html
</pre>

---

<h2 align="left">🔄 Application Flow</h2>

<p align="left">
<b>1️⃣ User Login</b> → Session created after authentication<br>
<b>2️⃣ Browse Products</b> → Products loaded from MySQL<br>
<b>3️⃣ Add to Cart</b> → Cart stored per session/user<br>
<b>4️⃣ Place Order</b> → Order saved in database<br>
<b>5️⃣ Order History</b> → User can view previous orders
</p>

---

<h2 align="left">🔐 Authentication Details</h2>

<p align="left">
✔ Session-based authentication using HttpSession<br>
✔ Protected pages accessible only after login<br>
✔ Auto redirect to login if session expires<br>
✔ No JWT or token usage
</p>

---

<h2 align="left">▶️ Setup & Run</h2>

<pre>
git clone &lt;your-repo-url&gt;
cd ecommerce-shopping-website
</pre>

<p align="left"><b>Configure application.properties</b></p>

<pre>
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
</pre>

<pre>
./mvnw spring-boot:run
</pre>

<p align="left">
Open browser: <b>http://localhost:8080/</b>
</p>

---

<h2 align="left">📌 Notes</h2>

<p align="left">
✔ Clean MVC architecture<br>
✔ Ideal for small-scale e-commerce systems<br>
✔ Easily extendable for admin panel & payment gateway
</p>

---

<p align="left">
⭐ If you like this project, don’t forget to star the repository
</p>
