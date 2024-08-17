import React, { useEffect, useState } from "react"
import Cardcontainer from "./Cardcontainer";
import { useAuth } from "../AuthContext";
import { text } from "@fortawesome/fontawesome-svg-core";
function Bookings(){
    const {removeBooking,fetchuserBookings} = useAuth();
    const [data,setData] = useState([])
    console.log({data})

    useEffect(()=>{
        fetchuserBookings(data,setData);
    },[])
    return (
        <>
        <h1 class="text-center text-uppercase fw-bold text-success mt-5">Bookings</h1>
       

        <table class="table">
  <thead>
  {/* "bookingId":1,"status":"SUCCESS","bookingDate":"2024-08-14","totalCost":55000,"userId":2,"tourId":3 */}
    <tr>
      <th scope="col">Booking ID</th>
      <th scope="col">Booking Status</th>
      <th scope="col">Booking Date</th>
      <th scope="col">Total Cost</th>
      <th scope="col">User Id</th>
      <th scope="col">Tour Id</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
        {
            data.map(i=>(
<tr>
      <th scope="row">{i.bookingId}</th>
      <td>{i.status}</td>
      <td>{i.bookingDate}</td>
      <td>{i.totalCost}</td>
      <td>{i.userId}</td>
      <td>{i.tourId}</td>
     <td>
        <button onClick={()=>{removeBooking(i.bookingId)}} className="btn btn-danger">Delete</button>
     </td>
    </tr>
            ))
        }

    
    
  </tbody>
</table>
        </>
    )
}

export default Bookings