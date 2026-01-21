"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { FaPhone, FaLock } from "react-icons/fa";

export default function SignInPage() {
  const [mobile, setMobile] = useState("");
  const [otp, setOtp] = useState("");
  const [otpSent, setOtpSent] = useState(false);
  const [msg, setMsg] = useState("");
  const [loading, setLoading] = useState(false);

  const router = useRouter();

  const requestOtp = async () => {
    setLoading(true);
    setMsg("");

    try {
      const res = await fetch("http://localhost:8080/api/auth/request-otp", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ mobileNumber: mobile }),
      });

      if (res.ok) {
        setOtpSent(true);
        setMsg("OTP sent successfully!");
      } else {
        setMsg("Failed to send OTP");
      }
    } catch (e) {
      setMsg("Server not reachable");
    }

    setLoading(false);
  };

  const verifyOtp = async () => {
    setLoading(true);
    setMsg("");

    try {
      const res = await fetch("http://localhost:8080/api/auth/verify-otp", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ mobileNumber: mobile, otp: otp }),
      });

      if (res.ok) {
        router.push("/admin");
      } else {
        setMsg("Invalid OTP!");
      }
    } catch (e) {
      setMsg("Server not reachable");
    }

    setLoading(false);
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-blue-100 flex justify-center items-center p-6 relative">

      {/* 🔵 Register Button (Top Right) */}
      <button
        onClick={() => router.push("/register")}
        className="absolute top-6 right-6 bg-blue-600 text-white px-5 py-2 rounded-xl shadow-md font-semibold hover:bg-blue-700 active:scale-95 transition"
      >
        Register
      </button>

      <div className="bg-white shadow-2xl rounded-2xl p-10 w-full max-w-md border border-blue-100">

        <h1 className="text-4xl font-extrabold text-center mb-8 text-blue-700 tracking-wide">
          🔐 Login with OTP
        </h1>

        {/* MOBILE INPUT */}
        {!otpSent && (
          <div className="grid gap-4 animate-fadeIn">
            <label className="block text-gray-600 font-medium mb-1">
              Mobile Number
            </label>

            <div className="flex items-center bg-gray-100 border rounded-lg px-3">
              <FaPhone className="text-gray-500 mr-2" />
              <input
                type="text"
                value={mobile}
                onChange={(e) => setMobile(e.target.value)}
                className="w-full py-3 bg-transparent outline-none"
                placeholder="Enter your mobile number"
              />
            </div>

            <button
              onClick={requestOtp}
              disabled={loading}
              className="w-full bg-blue-600 text-white py-3 rounded-lg text-xl font-bold hover:bg-blue-700 transition shadow-lg active:scale-95 mt-4"
            >
              {loading ? "Sending..." : "Send OTP"}
            </button>
          </div>
        )}

        {/* OTP INPUT */}
        {otpSent && (
          <div className="grid gap-4 animate-fadeIn">
            <label className="block text-gray-600 font-medium mb-1">
              Enter OTP
            </label>

            <div className="flex items-center bg-gray-100 border rounded-lg px-3">
              <FaLock className="text-gray-500 mr-2" />
              <input
                type="text"
                value={otp}
                onChange={(e) => setOtp(e.target.value)}
                className="w-full py-3 bg-transparent outline-none"
                placeholder="Enter 6-digit OTP"
              />
            </div>

            <button
              onClick={verifyOtp}
              disabled={loading}
              className="w-full bg-green-600 text-white py-3 rounded-lg text-xl font-bold hover:bg-green-700 transition shadow-lg active:scale-95 mt-4"
            >
              {loading ? "Verifying..." : "Verify OTP"}
            </button>

            {/* Resend OTP */}
            <button
              onClick={requestOtp}
              className="text-blue-600 mt-2 text-sm font-semibold hover:underline"
            >
              Resend OTP
            </button>
          </div>
        )}

        {/* MESSAGE */}
        {msg && (
          <p className="mt-6 text-center text-gray-700 font-semibold animate-fadeIn">
            {msg}
          </p>
        )}
      </div>
    </div>
  );
}
