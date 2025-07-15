import { useEffect, useState } from "react";
import axios from "axios";

const UserProfile = () => {
  const [user, setUser] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get("http://localhost:8080/api/user/profile", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setUser(response.data);
      } catch (err) {
        setError("Nu s-au putut încărca datele.");
      }
    };

    fetchProfile();
  }, []);

  if (error) return <p className="text-red-600 text-center mt-6">{error}</p>;
  if (!user) return <p className="text-center mt-6">Se încarcă...</p>;

  return (
    <div className="max-w-2xl mx-auto p-4">
      <div className="bg-white p-6 shadow-lg rounded-2xl">
        <div className="flex flex-col items-center">
          <div className="w-32 h-32 rounded-full border-4 border-gray-300 overflow-hidden flex items-center justify-center bg-gray-100">
            <img
              src={user.profilePic || "https://via.placeholder.com/150"}
              alt="Profile"
              className="w-full h-full object-cover"
            />
          </div>

          <h2 className="text-2xl font-semibold mt-3">{user.name}</h2>
          <p className="text-gray-500">{user.email}</p>
          <p className="text-gray-600 mt-2">{user.address || "Adresă indisponibilă"}</p>
        </div>
      </div>

      <h3 className="text-xl font-semibold mt-8 mb-3">Comenzi recente</h3>
      <div className="space-y-3">
        {user.orders.length > 0 ? (
          user.orders.map((order) => (
            <div key={order.id} className="bg-white p-4 shadow-md rounded-xl flex justify-between items-center">
              <div>
                <p className="font-medium">{order.item}</p>
                <p className="text-gray-500 text-sm">{order.date}</p>
              </div>
              <span className="font-semibold">{order.price}</span>
            </div>
          ))
        ) : (
          <p className="text-gray-500">Nu ai comenzi.</p>
        )}
      </div>
    </div>
  );
};

export default UserProfile;
