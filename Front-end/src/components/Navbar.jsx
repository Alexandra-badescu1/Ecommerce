import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import AppContext from "../Context/Context";

const Navbar = ({ onSelectCategory }) => {
  const getInitialTheme = () => localStorage.getItem("theme") || "light-theme";
  const [selectedCategory, setSelectedCategory] = useState("");
  const [theme, setTheme] = useState(getInitialTheme());
  const [input, setInput] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [noResults, setNoResults] = useState(false);
  const [showSearchResults, setShowSearchResults] = useState(false);

  const { user, logout } = useContext(AppContext);
  const isAuthorized = user && (user.role === "Antreprenor" || user.role === "Manager");

  useEffect(() => {
    document.body.className = theme;
    localStorage.setItem("theme", theme);
  }, [theme]);

  const fetchData = async (value) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/get/search?keyword=${value}`);
      setSearchResults(response.data);
      setNoResults(response.data.length === 0);
    } catch (error) {
      console.error("Error fetching search results:", error);
    }
  };

  const handleChange = (value) => {
    setInput(value);
    if (value.trim().length > 0) {
      setShowSearchResults(true);
      fetchData(value);
    } else {
      setShowSearchResults(false);
      setSearchResults([]);
      setNoResults(false);
    }
  };

  const handleCategorySelect = (category) => {
    setSelectedCategory(category);
    onSelectCategory(category);
  };

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme === "dark-theme" ? "light-theme" : "dark-theme"));
  };

  const categories = ["Laptop", "Headphone", "Mobile", "Electronics", "Toys", "Fashion"];

  return (
    <header>
      <nav className="navbar navbar-expand-lg fixed-top">
        <div className="container-fluid">
        <a className="navbar-brand" href="/">
            <img src="logo.png" alt="Logo" style={{ height: "40px" }} />
          </a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item"><a className="nav-link active" href="/">Home</a></li>

              {isAuthorized && (
                <li className="nav-item"><a className="nav-link" href="/add_product">Add Product</a></li>
              )}

              <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Categories</a>
                <ul className="dropdown-menu">
                  {categories.map((category) => (
                    <li key={category}>
                      <button className="dropdown-item" onClick={() => handleCategorySelect(category)}>
                        {category}
                      </button>
                    </li>
                  ))}
                </ul>
              </li>

              {user ? (
                <>
                  <li className="nav-item"><a className="nav-link" href="/profile">Profile</a></li>
                  <li className="nav-item">
                    <button className="nav-link btn btn-link" onClick={logout}>Logout</button>
                  </li>
                </>
              ) : (
                <>
                  <li className="nav-item"><a className="nav-link" href="/signup">Sign Up</a></li>
                  <li className="nav-item"><a className="nav-link" href="/login">Log In</a></li>
                </>
              )}
            </ul>

            <button className="theme-btn" onClick={toggleTheme}>
              <i className={`bi ${theme === "dark-theme" ? "bi-moon-fill" : "bi-sun-fill"}`}></i>
            </button>

            <div className="d-flex align-items-center cart">
              <a href="/cart" className="nav-link text-dark">
                <i className="bi bi-cart me-2"> Cart</i>
              </a>
              <input
                className="form-control me-2"
                type="search"
                placeholder="Search"
                value={input}
                onChange={(e) => handleChange(e.target.value)}
              />
              {showSearchResults && (
                <ul className="list-group">
                  {searchResults.length > 0 ? (
                    searchResults.map((result) => (
                      <li key={result.id} className="list-group-item">
                        <a href={`product/${result.id}`} className="search-result-link">{result.name}</a>
                      </li>
                    ))
                  ) : (
                    noResults && <p className="no-results-message">No Product with such Name</p>
                  )}
                </ul>
              )}
            </div>
          </div>
        </div>
      </nav>
    </header>
  );
};

export default Navbar;
