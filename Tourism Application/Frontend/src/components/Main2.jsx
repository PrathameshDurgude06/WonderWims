import React from "react";
import CarouselFile from "./CarouselFile";
import Home from "./Home";
import { ToastContainer } from "react-bootstrap";
import CardProfile from "./CardProfile";
function Main2(){
    return (
        <div className="maincontainer">
          <CarouselFile />
          {/* <h1>HUHUHUHUHUHUHUHHUHUHUH</h1> */}
          <Home  />
          <ToastContainer position="top-right" />
           <CardProfile/>
        </div>)

}

export default Main2;