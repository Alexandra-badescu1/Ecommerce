import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const LogIn = () => {
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  const [error, setError] = useState("");
  const navigate = useNavigate(); // Hook pentru navigare

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/user/login", {
        email: user.email,
        password: user.password,
      });

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("role", response.data.role);

      // Redirecționează utilizatorul în funcție de rol
      if (response.data.role === "Antreprenor" || response.data.role === "Manager") {
        navigate("/add_product");
      } else {
        navigate("/");
      }
    } catch (error) {
      setError("Login failed. Check your credentials.");
      console.error("Login failed", error);
    }
  };

  return (
    <div className="login-container">
      <div className="center-container" style={{ marginTop: "7rem" }}>
        <h1 style={{ textAlign: "center" }}>Log In</h1>
        {error && <p className="text-danger">{error}</p>}
        <form className="row g-3 pt-1" onSubmit={handleSubmit}>
          <div className="col-12">
            <label className="form-label">
              <h6>Email</h6>
            </label>
            <input
              type="email"
              className="form-control"
              name="email"
              value={user.email}
              onChange={handleChange}
              required
            />
          </div>
          <div className="col-12">
            <label className="form-label">
              <h6>Password</h6>
            </label>
            <input
              type="password"
              className="form-control"
              name="password"
              value={user.password}
              onChange={handleChange}
              required
            />
          </div>
          <div className="col-12">
            <button type="submit" className="btn btn-primary">
              Log In
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default LogIn;
