Ecommerce Platform
A simple Ecommerce platform with basic CRUD operations, authentication using JWT, and administrator-only access to management functions.
Features
User Access & Authentication
User Authentication:
Secure login and access management using JSON Web Tokens (JWT) and a JWT filter to protect private routes.

Public Product Viewing:
Anyone (including non-logged-in users) can access the public GET endpoints to browse products displayed on the home page.

Guest Purchases:
Users can buy products without creating an account, enabling quick checkout.

Registered Users Benefits:
Registered users have an easier experience by being able to:

View their purchase history.

Manage shipping addresses.

Save payment choices for faster future purchases.

Admin-only CRUD: Create, Update, and Delete operations for products and other entities accessible exclusively to authenticated administrators.

Product Management: Administrators can manage products through the CRUD API.

Role-based Access Control: Only users with admin privileges can perform data modifications.

Technologies Used
Backend: Java Spring Boot

Security: Spring Security with JWT-based authentication

Frontend: React 
<img width="1903" height="839" alt="Screenshot 2025-07-16 152415" src="https://github.com/user-attachments/assets/c903b288-f6b8-47b2-88fd-b7d5f4f1433a" />
<img width="1914" height="834" alt="Screenshot 2025-07-16 152356" src="https://github.com/user-attachments/assets/5f0b84db-318a-4c42-ae7a-e29aff05c749" />

<img width="1884" height="892" alt="Screenshot 2025-07-16 152148" src="https://github.com/user-attachments/assets/8e1b4410-4c10-4680-b4da-e1ecf8403eb1" />
<img width="1875" height="906" alt="Screenshot 2025-07-16 152524" src="https://github.com/user-attachments/assets/c2d3a4ea-b032-4ab8-86a5-9d0b394c6ba4" />


Database: H2
