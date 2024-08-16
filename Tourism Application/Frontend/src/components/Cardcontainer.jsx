/* eslint-disable react/prop-types */
import { useEffect, useState } from "react";
import Card from "./Card";
import { useAuth } from "../AuthContext";

const Cardcontainer = ({ data }) => {
  const { removeBooking, fetchuserBookings } = useAuth();
  const [bookedData, setBookedData] = useState([]);
  console.log("Card container ", { data });
  useEffect(() => {
    // fetchuserBookings(bookedData,setBookedData)
  }, []);
  return (
    <div className="filter-container">
      <div className="cards">
        {data.map((item) => {

          console.log(Array.isArray(item));
          return <Card
                  // key={item[0].id}
                  // {...item[0]}
                  tour={item}
                  removeBooking={removeBooking}
                  booked={true}
                ></Card>
          // if (Array.isArray(item)) {
          //   const fData = bookedData.filter((i) => i[0].id === item[0].id);

          //   if (fData.length > 0) {
          //     return (
          //       <Card
          //         key={item[0].id}
          //         {...item[0]}
          //         removeBooking={removeBooking}
          //         booked={true}
          //       ></Card>
          //     );
          //   }
          // } else {
          //   const fData = bookedData.filter((i) => i[0].id === item.id);

          //   if (fData.length > 0) {
          //     return (
          //       <Card
          //         key={item.id}
          //         {...item}
          //         removeBooking={removeBooking}
          //         booked={true}
          //       ></Card>
          //     );
          //   } else {
          //     return (
          //       <Card
          //         key={item.id}
          //         {...item}
          //         removeBooking={removeBooking}
          //         booked={false}
          //       ></Card>
          //     );
          //   }
          // }
        })}
      </div>
    </div>
  );
};

export default Cardcontainer;
