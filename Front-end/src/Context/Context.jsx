import axios from "../axios";
import { useState, useEffect, createContext } from "react";

const AppContext = createContext({
  data: [],
  isError: "",
  cart: [],
  user: null, // Store user info
  login: (userData) => {},
  logout: () => {},
  addToCart: (product) => {},
  removeFromCart: (productId) => {},
  refreshData: () => {},
  clearCart: () => {},
});


export const AppProvider = ({ children }) => {
  const [data, setData] = useState([]);
  const [isError, setIsError] = useState("");
  const [cart, setCart] = useState(JSON.parse(localStorage.getItem("cart")) || []);
  const [user, setUser] = useState(JSON.parse(localStorage.getItem("user")) || null);

  const login = (userData) => {
    setUser(userData);
    localStorage.setItem("user", JSON.stringify(userData));
  };

  // ... alte hooks și funcții
const logout = () => {
  setUser(null);
  localStorage.removeItem("user");
  localStorage.removeItem("cart");
};

const addToCart = (product) => {
  setCart((prevCart) => {
    const updatedCart = [...prevCart, product];
    localStorage.setItem("cart", JSON.stringify(updatedCart));
    return updatedCart;
  });
};

const removeFromCart = (productId) => {
  setCart((prevCart) => {
    const updatedCart = prevCart.filter((item) => item.id !== productId);
    localStorage.setItem("cart", JSON.stringify(updatedCart));
    return updatedCart;
  });
};

const clearCart = () => {
  setCart([]);
  localStorage.removeItem("cart");
};


  const refreshData = async () => {
    try {
      const response = await axios.get("/getall");
      setData(response.data);
    } catch (error) {
      setIsError(error.message);
    }
  };

  useEffect(() => {
    refreshData();
  }, []);

  return (
    <AppContext.Provider
      value={{
        data,
        isError,
        cart,
        user,
        login,
        logout,
        addToCart,
        removeFromCart,
        refreshData,
        clearCart,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

export default AppContext;
