import { useState } from "react";

const UserProfile = () => {
  const [user] = useState({
    name: "John Doe",
    email: "johndoe@example.com",
    address: "123 Main St, Springfield",
    profilePic: "https://via.placeholder.com/150",
    orders: [
      { id: 1, item: "Wireless Headphones", date: "2024-02-15", price: "$99.99" },
      { id: 2, item: "Gaming Mouse", date: "2024-01-20", price: "$49.99" },
    ],
  });

  return (
    <div className="max-w-2xl mx-auto p-4">
      <div className="bg-white p-4 shadow-lg rounded-2xl">
        <div className="flex flex-col items-center">
          <img
            src={user.profilePic}
            alt="Profile"
            className="w-24 h-24 rounded-full border-4 border-gray-300"
          />
          <h2 className="text-xl font-semibold mt-2">{user.name}</h2>
          <p className="text-gray-500">{user.email}</p>
          <p className="text-gray-600 mt-2">{user.address}</p>
          <button className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition">
            Edit Profile
          </button>
        </div>
      </div>

      <h3 className="text-lg font-semibold mt-6 mb-2">Order History</h3>
      <div className="space-y-2">
        {user.orders.map((order) => (
          <div key={order.id} className="bg-white p-3 shadow-md rounded-xl flex justify-between items-center">
            <div>
              <p className="font-medium">{order.item}</p>
              <p className="text-gray-500 text-sm">{order.date}</p>
            </div>
            <span className="font-semibold">{order.price}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserProfile;
