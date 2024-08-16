/* eslint-disable react/prop-types */
import { toast } from "react-toastify";
import { useState } from "react";

function Card({ id, name, info, imageLink, price,booked=false,removeBooking }) 
{
  console.log({id})
  // const [booked, setBooked] = useState(false);
  

  async function bookingHandler( userId ) {
    console.log("booked", id);
    const response =await fetch(`http://127.0.0.1:8443/bookings/user/${userId}`,{
      method:"GET",            
    });

    const respData = await response.json();
    const userId = localStorage.getItem("userId");
    const response2 =await fetch(`http://127.0.0.1:3000/booked`,{
      method:"POST",
      headers:{
        "Content-type":"application/json"
    },
      body:JSON.stringify([respData.data[0],userId])            
    });

  }

  return (
    <div className="card">
      <img src={imageLink} alt="error" className="imageLink" height={200} />

      <div className="tour-details">
        <h2 className="tour-price">{price}</h2>
        <h2 className="tour-name">{name}</h2>
      </div>

      <div className="description">{info}</div>
      {
        !booked?(<button
          className={`btn-red ${booked ? "btn-disabled" : ""}`}
          onClick={() => bookingHandler(id)}
        >
        Book Now
        </button>):(
          <button
          className={`btn-red ${booked ? "btn-disabled" : ""}`}
          onClick={() => removeBooking(id)}
        >
        Remove Bookings
        </button>
        )
      }
      
    </div>
  );
}

export default Card;
