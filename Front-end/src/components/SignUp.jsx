import { useState } from "react";
import axios from "axios";

const SignUp = () => {
  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: ""
  });

  const [imageFile, setImageFile] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleImageChange = (e) => {
    setImageFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
  
    // Exclude confirmPassword înainte de a trimite datele
    const { confirmPassword, ...userWithoutConfirm } = user;
  
    formData.append("product", JSON.stringify(userWithoutConfirm));
    formData.append("imageFile", imageFile);
  
    try {
      const response = await axios.post(
        "http://localhost:8080/api/user/signup",
        formData,
        {
          headers: { "Content-Type": "multipart/form-data" },
        }
      );
  
      alert("User registered successfully!");
      console.log("Response:", response.data);
    } catch (error) {
      console.error("Error signing up:", error.response?.data || error.message);
      alert("Failed to sign up. Please try again.");
    }
  };
  

  return (
    <div className="signup-container">
      <div className="center-container" style={{ marginTop: "7rem" }}>
        <h1 style={{ textAlign: "center" }}>Sign Up</h1>
        <form className="row g-3 pt-1" onSubmit={handleSubmit}>
          <div className="col-md-6">
            <label className="form-label"><h6>First Name</h6></label>
            <input type="text" className="form-control" name="firstName" value={user.firstName} onChange={handleChange} required />
          </div>
          <div className="col-md-6">
            <label className="form-label"><h6>Last Name</h6></label>
            <input type="text" className="form-control" name="lastName" value={user.lastName} onChange={handleChange} required />
          </div>
          <div className="col-12">
            <label className="form-label"><h6>Email</h6></label>
            <input type="email" className="form-control" name="email" value={user.email} onChange={handleChange} required />
          </div>
          <div className="col-md-6">
            <label className="form-label"><h6>Password</h6></label>
            <input type="password" className="form-control" name="password" value={user.password} onChange={handleChange} required />
          </div>
          <div className="col-md-6">
            <label className="form-label"><h6>Confirm Password</h6></label>
            <input type="password" className="form-control" name="confirmPassword" value={user.confirmPassword} onChange={handleChange} required />
          </div>
          <div className="col-md-8">
            <label className="form-label"><h6>Profile Picture</h6></label>
            <input className="form-control" type="file" onChange={handleImageChange} name="imageFile" required />
          </div>
          <div className="col-12">
            <button type="submit" className="btn btn-primary">Sign Up</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default SignUp;
