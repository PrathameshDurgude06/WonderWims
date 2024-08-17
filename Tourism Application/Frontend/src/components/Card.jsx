import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

// TourCard Component
function TourCard({ tour, handleDelete, handleUpdate}) {
    const navigate = useNavigate();

    const handleBooking = async(tourId,tourPrice)=>{
        const data = {
            "status":"SUCCESS",
            "bookingDate":new Date(),
            "totalCost":tourPrice,
            "userId":localStorage.getItem("userId"),
            "tourId":tourId
        }
        console.table(data)
        const co = document.cookie.split('=')[1];
        const response  = await axios.post("http://localhost:8443/v2/bookings/book",data,{
            headers: {
                "Authorization": `Bearer ${co}`,
                'Content-Type':"application/json"
            }
        });

        const resData = response.data;
        console.log(resData)
        
        navigate("/bookings")
    }

    console.table(tour)
    return (
        <div className="card mb-3" style={{ maxWidth: '700px' }}>
            <div 
                className="card-img-top" 
                style={{ backgroundImage: `url(${tour?.imageLink ?? ""})`, height: '200px', backgroundSize: 'cover', backgroundPosition: 'center' }}
            ></div>
            <div className="card-body">
                <h5 className="card-title">{tour?.title ?? "No Title"}</h5>
                <h6 className="card-subtitle mb-2 text-muted">
                    {tour?.duration ?? "Unknown Duration"} | {new Date(tour?.startDate ?? "").toLocaleDateString() ?? "Unknown Date"}
                </h6>
                <p className="card-text">{tour?.description ?? "No Description"}</p>
                <ul className="list-unstyled">
                    <li><strong>Price:</strong> ${tour?.price ?? "0"}</li>
                    {/* Additional features can be listed here */}
                </ul>
                <ul>
                    {(tour?.destinations ?? []).map((dest, index) => (
                        <li key={index}>{dest.destName ?? "Unknown Destination"}</li>
                    ))}
                </ul>
                <div className="d-flex justify-content-between">
                    {localStorage.getItem("role")==='ADMIN'?(<><button 
                        className="btn btn-primary" 
                        onClick={() => handleUpdate(tour.tourId)}
                    >
                        Update
                    </button>
                    <button 
                        className="btn btn-danger" 
                        onClick={() => handleDelete(tour.tourId)}
                    >
                        Delete
                    </button></>):(<><button onClick={()=>handleBooking(tour.tourId,tour?.price)} className='btn btn-success'>Book</button></>)}
                </div>
            </div>
        </div>
    );
}

export default TourCard;
