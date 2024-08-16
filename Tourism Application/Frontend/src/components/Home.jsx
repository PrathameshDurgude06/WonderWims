import { useState,useEffect } from "react";
import Cardcontainer from "./Cardcontainer";
import data from "../database/Data";
import Header from "./Header";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../AuthContext";

function Home() {

  // const {fetchuserBookings} = useAuth();
  const [selectedState, setSelectedState] = useState("show all states");
  const [data,setData] = useState([])
  const [bookedData,setBookedData] = useState([]);
  const navigate = useNavigate();


  const uniqueStates = [
    "Uttar Pradesh",
    "Rajasthan",
    "LOude",
    "West Bengal",
    "Kerala",
    "Maharashtra",
    "Himachal Pradesh",
    "Bihar",
    "Jharkhand",
  ];

  const filterHandler = (stateName) => {
    setSelectedState(stateName);
  };


  const getAllData = async()=>{
    console.log("getting all data")
    const request =await fetch("http://127.0.0.1:8443/v2/tours",{
      method:"GET"
    });
    const data =await request.json();
    const d2 = data
      console.log("d2",d2)
    setData(d2??[])
  }

  useEffect(()=>{
    console.log("In use effect")
    getAllData();
    // fetchuserBookings(bookedData,setBookedData)
  },[])
  console.log({data})
  const filterData =
    selectedState != "show all states"
      ? data.filter((item) => item.state === selectedState)
      : data;


  console.log("cookie",document.cookie)

  // if(document.cookie){

    return (
      <>
        <div className="filter-option">
          <select
            onChange={(e) => filterHandler(e.target.value)}
            className={
              selectedState != "show all states"
                ? "active-select"
                : "inactive-select"
            }
          >
            <option>show all states</option>
            {uniqueStates.map((item) => (
              <option key={item} value={item}>
                {item}
              </option>
            ))}
          </select>
        </div>

        <Cardcontainer data={data??[]} />
         
      </>
    );
  

  // }else{
  //   navigate("/signup")
  // }
  

  }

export default Home;
