import React, { useEffect, useState } from "react"
import Cardcontainer from "./Cardcontainer";
import { useAuth } from "../AuthContext";
function Bookings(){
    const {removeBooking,fetchuserBookings} = useAuth();
    const [data,setData] = useState([])
    console.log({data})

    useEffect(()=>{
        fetchuserBookings(data,setData);
    },[])
    return (
        <>
        <h1>Bookings</h1>
        <Cardcontainer data={data??[]} removeBooking={removeBooking} fetchuserBookings={fetchuserBookings} />
        </>
    )
}

export default Bookings