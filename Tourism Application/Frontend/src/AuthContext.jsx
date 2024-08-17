import { createContext, useContext, useEffect, useState } from "react";
import { Children } from "react";
import React from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AuthContext = createContext()

export const AuthProvider = ({children})=>{

    const [user,setUser] = useState(null);
    const navigate = useNavigate();
    console.log("User ",user)
    const [status,setStatus] = useState(false)

    useEffect(()=>{},[status])

    const login = async (email, password) => {
    // Basic validation for email and password
    if (!email || !password) {
        console.error("Email and password are required");
        return { success: false, message: "Email and password are required" };
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        console.error("Invalid email format");
        return { success: false, message: "Invalid email format" };
    }

    const passwordRegex = /^(?=.*[!@#$%^&*(),.?":{}|<>])[A-Za-z\d!@#$%^&*(),.?":{}|<>]{8,}$/;
    if (!passwordRegex.test(password)) {
        console.error("Password must be at least 8 characters long and contain at least one special character");
        return { success: false, message: "Password must be at least 8 characters long and contain at least one special character" };
    }

    try {
        const response = await fetch("http://127.0.0.1:8443/users/signin", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password }),
        });

        const r = await response.json();
        const status = response.status;

        if (status === 200) { // Assuming 200 is the success status code
            document.cookie = "token=" + r.jwt + "; expires=" + 86400;
            localStorage.setItem("userId", r.userId);
            localStorage.setItem("role",r.role);
            setUser({ userId: r.userId, role: r.role });

            // Navigate based on the user role
            if (r.role === "ADMIN") {
                navigate("/admin");
            } else {
                navigate("/home");
            }

            return { success: true, jwt: r.jwt, userId: r.userId, userRole: r.role };
        } else {
            console.error("Login failed:", r.message);
            return { success: false, message: r.message };
        }
    } catch (error) {
        console.error("Login error:", error);
        return { success: false, message: error.message };
    }
};


    const logout =async(navigate)=>{
        console.log("Handle logout clicked");
        const expiryDate = new Date(Date.now() + 0 * 1000);
        document.cookie = `token=;expires=${expiryDate.toUTCString()}`;
        localStorage.clear();
        setUser(null)
        navigate("/signup")
    }

const fetchuserBookings = async (data, setData) => {
    try {
        const userId = localStorage.getItem("userId");
        const listData = [];
        const co = document.cookie.split('=')[1];

        // Fetch user bookings by userId
        const response = await axios.get(`http://127.0.0.1:8443/v2/bookings/user/${userId}`, {
            headers: {
                Authorization: `Bearer ${co}`,
                'Content-Type': 'application/json',
            },
        });

        const user_data = response.data;
        console.log({response});
        // for (const booking of user_data) {
        //     console.log({ booking });
        //     const response2 = await axios.get(`http://127.0.0.1:8443/v2/bookings/booking/${booking.bookingId}`,{
        //         headers: {
        //             Authorization: `Bearer ${co}`,
        //         },});
        //         console.log({response2})
        //     // const location_data = await response2.data;
        //     listData.push(response2.data);
        // }

        // console.log({ listData });
        setData(response.data);

    } catch (error) {
        console.error("Error fetching user bookings:", error);
    }
};

// Getting all tours
const getAllData = async(setData)=>{
    console.log("getting all data")
    
    const request =await fetch("http://127.0.0.1:8443/v2/tours",{
      method:"GET"
    });
    const data =await request.json();
    const d2 = data
      console.log("d2",d2)
    setData(d2??[])
  }

// Delete tour
const handleDelete = async (tourId) => {
    console.log(tourId);
    const co = document.cookie.split('=')[1];
    try {
        const response = await axios.delete(`http://127.0.0.1:8443/v1/tours/${tourId}`, {
            headers: {
                "Authorization": `Bearer ${co}`,
            },
        });
        const result =  response.data;
        const status=result.status;
        if (status===200) {
            getAllData();
            setStatus(true);
        }
    } catch (error) {
        console.error("Failed to delete tour:", error);
    }
};

    
    async function removeBooking(id ) {
        console.log("Removing Booking :- "+id)
        const co = document.cookie.split('=')[1];
      const response = await axios.delete(`http://127.0.0.1:8443/v2/bookings/${id}`,{
        headers:{
            Authorization: `Bearer ${co}`
        }
        
      });
    
      const data = response.data;
    
      console.log({data})
    
    
    }

    return (
        <AuthContext.Provider value={{user,login,logout,fetchuserBookings,handleDelete,removeBooking,getAllData}}>
            {children}
        </AuthContext.Provider>
    )
    
}


export const useAuth = ()=>useContext(AuthContext);