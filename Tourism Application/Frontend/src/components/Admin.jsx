import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import TourCard from "./Card";
import axios from "axios";

// Accommodation component to handle individual accommodations
const AccommodationForm = ({ accommodation, onChange }) => {
    return (
        <div className="mb-3">
            <h5>Accommodation</h5>
            <input 
                className="form-control mb-2" 
                type='text' 
                value={accommodation.name || ""} 
                onChange={(e) => onChange("name", e.target.value)} 
                placeholder='Accommodation Name' 
            />
            <input 
                className="form-control mb-2" 
                type='text' 
                value={accommodation.type || ""} 
                onChange={(e) => onChange("type", e.target.value)} 
                placeholder='Accommodation Type' 
            />
            <input 
                className="form-control mb-2" 
                type='text' 
                value={accommodation.location || ""} 
                onChange={(e) => onChange("location", e.target.value)} 
                placeholder='Accommodation Location' 
            />
            <input 
                className="form-control mb-2" 
                type='text' 
                value={accommodation.details || ""} 
                onChange={(e) => onChange("details", e.target.value)} 
                placeholder='Accommodation Details' 
            />
            <input 
                className="form-control mb-2" 
                type='date' 
                value={accommodation.checkIn || ""} 
                onChange={(e) => onChange("checkIn", e.target.value)} 
                placeholder='Check-In Date' 
            />
            <input 
                className="form-control mb-2" 
                type='date' 
                value={accommodation.checkOut || ""} 
                onChange={(e) => onChange("checkOut", e.target.value)} 
                placeholder='Check-Out Date' 
            />
        </div>
    );
};

const DestinationForm = ({ destination, onChange }) => {
    return (
        <div className="mb-3">
            <h5>Destination</h5>
            <input 
                className="form-control mb-2" 
                type='text' 
                value={destination.destName || ""} 
                onChange={(e) => onChange("destName", e.target.value)} 
                placeholder='Destination Name' 
            />
            <input 
                className="form-control mb-2" 
                type='text' 
                value={destination.state || ""} 
                onChange={(e) => onChange("state", e.target.value)} 
                placeholder='State' 
            />
            <input 
                className="form-control mb-2" 
                type='text' 
                value={destination.description || ""} 
                onChange={(e) => onChange("description", e.target.value)} 
                placeholder='Description' 
            />
            <AccommodationForm 
                accommodation={destination.accommodation || {}} 
                onChange={(key, value) => onChange("accommodation", { ...destination.accommodation, [key]: value })}
            />
        </div>
    );
};

function Admin() {
    const [tourId, setID] = useState("");
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [duration, setDuration] = useState("");
    const [startDate, setStartDate] = useState("");
    const [imageLink, setImageLink] = useState("");
    const [price, setPrice] = useState(0);
    const [destinations, setDestinations] = useState([]);
    const [data, setData] = useState([]);
    const [isUpdate, setIsUpdate] = useState(false);

    // const getAllData = async () => {
    //     try {
    //         const response = await fetch("http://127.0.0.1:8443/v1/tours");
    //         const result = await response.json();
    //         setData(result ?? []);
    //     } catch (error) {
    //         console.error("Failed to fetch data:", error);
    //     }
    // };

    // useEffect(() => {
    //     getAllData();
    // }, []);

    

    const handleById = async (tourId) => {
        const co = document.cookie.split('=')[1];
        try {
            const response = await axios.put(`http://127.0.0.1:8443/v1/tours/id/${tourId}`, {
                headers: {
                    "Authorization": `Bearer ${co}`,
                },
            });
            return await response.json();
        } catch (error) {
            console.error("Failed to fetch tour by ID:", error);
        }
    };

    const handleUpdate = async (tourId) => {
        const tourData = await handleById(tourId);
        if (tourData) {
            setID(tourId);
            setTitle(tourData.title ?? "");
            setDescription(tourData.description ?? "");
            setDuration(tourData.duration ?? "");
            setStartDate(tourData.startDate ?? "");
            setImageLink(tourData.imageLink ?? "");
            setPrice(tourData.price ?? 0);
            setDestinations(tourData.destinations ?? []);
            setIsUpdate(true);
        }
    };

    const handleUpdateSubmit = async (id) => {
        const co = document.cookie.split('=')[1];
        try {
            const response = await axios.put(`http://127.0.0.1:8443/v1/tours/${id}`, {
                headers: {
                    "Authorization": `Bearer ${co}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ title, description, duration, startDate, imageLink, price, destinations })
            });
            const result = await response.json();
            const status = result.status;
            if (status===0) {
                getAllData();
                resetForm();
            }
        } catch (error) {
            console.error("Failed to update tour:", error);
        }
    };

    const handleCreateSubmit = async () => {
        const co = document.cookie.split('=')[1];
        console.log("handlecreatesubmit :- ",co);
        try {
            const response = await axios.post("http://127.0.0.1:8443/v1/tours/add",JSON.stringify({ title, description, duration, startDate, imageLink, price, destinations }), {
                headers: {
                    Authorization: `Bearer ${co}`,
                    "Content-Type": "application/json"
                },
              
            });
            const result = response.data;
            const status=result.status;
            if (status===201) {
                // getAllData();
                resetForm();
            }
        } catch (error) {
            console.error("Failed to create tour:", error);
        }
    };

    const resetForm = () => {
        setID("");
        setTitle("");
        setDescription("");
        setDuration("");
        setStartDate("");
        setImageLink("");
        setPrice(0);
        setDestinations([]);
        setIsUpdate(false);
    };

    const handleDestinationChange = (index, key, value) => {
        const newDestinations = [...destinations];
        newDestinations[index] = { ...newDestinations[index], [key]: value };
        setDestinations(newDestinations);
    };

    const handleAccommodationChange = (destIndex, key, value) => {
        const newDestinations = [...destinations];
        newDestinations[destIndex] = {
            ...newDestinations[destIndex],
            accommodation: { ...newDestinations[destIndex].accommodation, [key]: value }
        };
        setDestinations(newDestinations);
    };

    const addDestination = () => {
        setDestinations([...destinations, { destName: "", state: "", description: "", accommodation: {} }]);
    };

    return (
        <div className="container mt-4">
            <h2>{isUpdate ? "Update Tour" : "Create Tour"}</h2>
            <form>
                <div className="mb-3">
                    <input 
                        className="form-control" 
                        type='text' 
                        value={title} 
                        onChange={(e) => setTitle(e.target.value)} 
                        placeholder='Title' 
                    />
                </div>
                <div className="mb-3">
                    <input 
                        className="form-control" 
                        type='text' 
                        value={description} 
                        onChange={(e) => setDescription(e.target.value)} 
                        placeholder='Description' 
                    />
                </div>
                <div className="mb-3">
                    <input 
                        className="form-control" 
                        type='text' 
                        value={duration} 
                        onChange={(e) => setDuration(e.target.value)} 
                        placeholder='Duration' 
                    />
                </div>
                <div className="mb-3">
                    <input 
                        className="form-control" 
                        type='date' 
                        value={startDate} 
                        onChange={(e) => setStartDate(e.target.value)} 
                        placeholder='Start Date' 
                    />
                </div>
                <div className="mb-3">
                    <input 
                        className="form-control" 
                        type='text' 
                        value={imageLink} 
                        onChange={(e) => setImageLink(e.target.value)} 
                        placeholder='Image Link' 
                    />
                </div>
                <div className="mb-3">
                    <input 
                        className="form-control" 
                        type='number' 
                        value={price} 
                        onChange={(e) => setPrice(e.target.value)} 
                        placeholder='Price' 
                    />
                </div>

                <div className="mb-3">
                    <h4>Destinations</h4>
                    {destinations.map((destination, index) => (
                        <div key={index} className="border p-3 mb-3">
                            <DestinationForm 
                                destination={destination}
                                onChange={(key, value) => handleDestinationChange(index, key, value)}
                            />
                            <button 
                                type="button" 
                                className="btn btn-secondary" 
                                onClick={() => handleAccommodationChange(index, "name", "")} 
                            >
                                Clear Accommodation
                            </button>
                        </div>
                    ))}
                    <button 
                        type="button" 
                        className="btn btn-primary" 
                        onClick={addDestination}
                    >
                        Add Destination
                    </button>
                </div>

                <button 
                    type="button" 
                    className="btn btn-primary" 
                    onClick={isUpdate ? handleUpdateSubmit : handleCreateSubmit}
                >
                    {isUpdate ? "Update" : "Create"}
                </button>
            </form>

            <div className="mt-4">
                {data.map(tour => (
                    <TourCard 
                        key={tour.tourId}
                        tour={tour} 
                        handleDelete={()=>handleDelete(tour.tourId)} 
                        handleUpdate={()=>handleUpdate(tour.tourId)} 
                    />
                ))}
            </div>
        </div>
    );
}

export default Admin;
