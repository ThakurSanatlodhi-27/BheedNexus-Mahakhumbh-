"use client";
import { useState } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";
import { FaRegUser, FaPhone, FaIdCard, FaMapMarkerAlt } from "react-icons/fa";

export default function RegisterPage() {
  const router = useRouter();

  const [formData, setFormData] = useState({
    username: "",
    mobileNumber: "",
    aadharNumber: "",
    age: "",
    travelMode: "",
    fromDate: "",
    toDate: "",
    stateName: "",
    districtName: "",
    nearestPoliceStation: "",
  });

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [response, setResponse] = useState(null);

  // VALIDATION
  const validate = () => {
    let newErrors = {};

    if (!formData.username || formData.username.length < 3)
      newErrors.username = "Username must be at least 3 characters";

    if (!/^[0-9]{10}$/.test(formData.mobileNumber))
      newErrors.mobileNumber = "Mobile number must be 10 digits";

    if (!/^[0-9]{12}$/.test(formData.aadharNumber))
      newErrors.aadharNumber = "Aadhar number must be 12 digits";

    if (!formData.age || formData.age < 1 || formData.age > 120)
      newErrors.age = "Enter valid age between 1 and 120";

    if (formData.fromDate && formData.toDate) {
      if (new Date(formData.fromDate) > new Date(formData.toDate))
        newErrors.date = "From Date cannot be greater than To Date";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: "" });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    setLoading(true);
    try {
      const res = await axios.post(
        "http://localhost:8080/api/auth/register-user",
        formData,
        { headers: { "Content-Type": "application/json" } }
      );
      setResponse(res.data);
    } catch (error) {
      setResponse(error.response?.data || "Error occurred");
    }
    setLoading(false);
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-blue-100 flex justify-center items-center p-6 relative">

      {/* 🔵 Sign In Button */}
      <button
        onClick={() => router.push("/signin")}
        className="absolute top-6 right-6 bg-blue-600 text-white px-5 py-2 rounded-xl shadow-md font-semibold hover:bg-blue-700 active:scale-95 transition"
      >
        Sign In
      </button>

      <div className="bg-white shadow-2xl rounded-2xl p-10 w-full max-w-3xl border border-blue-100">
        <h1 className="text-4xl font-extrabold text-center mb-8 text-blue-700 tracking-wide">
          🚨 User Registration
        </h1>

        <form
          onSubmit={handleSubmit}
          className="grid grid-cols-1 md:grid-cols-2 gap-6"
        >
          {/* Username */}
          <div>
            <label className="block text-gray-600 font-medium mb-1">
              Username
            </label>
            <div className="flex items-center bg-gray-100 border rounded-lg px-3">
              <FaRegUser className="text-gray-500 mr-2" />
              <input
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
                className="w-full py-2 bg-transparent outline-none"
                required
              />
            </div>
            {errors.username && (
              <p className="text-red-500 text-sm">{errors.username}</p>
            )}
          </div>

          {/* Mobile */}
          <div>
            <label className="block text-gray-600 font-medium mb-1">
              Mobile Number
            </label>
            <div className="flex items-center bg-gray-100 border rounded-lg px-3">
              <FaPhone className="text-gray-500 mr-2" />
              <input
                type="text"
                name="mobileNumber"
                value={formData.mobileNumber}
                onChange={handleChange}
                className="w-full py-2 bg-transparent outline-none"
                required
              />
            </div>
            {errors.mobileNumber && (
              <p className="text-red-500 text-sm">{errors.mobileNumber}</p>
            )}
          </div>

          {/* Aadhar */}
          <div>
            <label className="block text-gray-600 font-medium mb-1">
              Aadhar Number
            </label>
            <div className="flex items-center bg-gray-100 border rounded-lg px-3">
              <FaIdCard className="text-gray-500 mr-2" />
              <input
                type="text"
                name="aadharNumber"
                value={formData.aadharNumber}
                onChange={handleChange}
                className="w-full py-2 bg-transparent outline-none"
                required
              />
            </div>
            {errors.aadharNumber && (
              <p className="text-red-500 text-sm">{errors.aadharNumber}</p>
            )}
          </div>

          {/* Age */}
          <div>
            <label className="block text-gray-600 font-medium mb-1">Age</label>
            <input
              type="number"
              name="age"
              value={formData.age}
              onChange={handleChange}
              className="w-full py-2 px-4 bg-gray-100 border rounded-lg outline-none"
              required
            />
            {errors.age && (
              <p className="text-red-500 text-sm">{errors.age}</p>
            )}
          </div>

          {/* Travel Mode */}
          <div>
            <label className="block text-gray-600 font-medium mb-1">
              Travel Mode
            </label>
            <input
              type="text"
              name="travelMode"
              value={formData.travelMode}
              onChange={handleChange}
              className="w-full py-2 px-4 bg-gray-100 border rounded-lg outline-none"
            />
          </div>

          {/* Dates */}
          <div>
            <label className="block text-gray-600 font-medium mb-1">
              From Date
            </label>
            <input
              type="date"
              name="fromDate"
              value={formData.fromDate}
              onChange={handleChange}
              className="w-full py-2 px-4 bg-gray-100 border rounded-lg outline-none"
            />
          </div>

          <div>
            <label className="block text-gray-600 font-medium mb-1">
              To Date
            </label>
            <input
              type="date"
              name="toDate"
              value={formData.toDate}
              onChange={handleChange}
              className="w-full py-2 px-4 bg-gray-100 border rounded-lg outline-none"
            />
          </div>

          {errors.date && (
            <p className="text-red-500 text-sm md:col-span-2">{errors.date}</p>
          )}

          {/* State */}
          <div>
            <label className="block text-gray-600 font-medium mb-1">
              State Name
            </label>
            <div className="flex items-center bg-gray-100 border rounded-lg px-3">
              <FaMapMarkerAlt className="text-gray-500 mr-2" />
              <input
                type="text"
                name="stateName"
                value={formData.stateName}
                onChange={handleChange}
                className="w-full py-2 bg-transparent outline-none"
              />
            </div>
          </div>

          {/* District */}
          <div>
            <label className="block text-gray-600 font-medium mb-1">
              District Name
            </label>
            <input
              type="text"
              name="districtName"
              value={formData.districtName}
              onChange={handleChange}
              className="w-full py-2 px-4 bg-gray-100 border rounded-lg outline-none"
            />
          </div>

          {/* Nearest PS */}
          <div className="md:col-span-2">
            <label className="block text-gray-600 font-medium mb-1">
              Nearest Police Station
            </label>
            <input
              type="text"
              name="nearestPoliceStation"
              value={formData.nearestPoliceStation}
              onChange={handleChange}
              className="w-full py-2 px-4 bg-gray-100 border rounded-lg outline-none"
            />
          </div>

          {/* Submit */}
          <button
            type="submit"
            className="md:col-span-2 bg-blue-600 text-white py-3 rounded-lg text-xl font-bold hover:bg-blue-700 transition shadow-lg active:scale-95"
          >
            {loading ? "Submitting..." : "Submit Registration"}
          </button>
        </form>

        {response && (
          <div className="mt-8 bg-gray-900 text-green-300 p-4 rounded-lg text-sm overflow-auto">
            <pre>{JSON.stringify(response, null, 2)}</pre>
          </div>
        )}
      </div>
    </div>
  );
}
