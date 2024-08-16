import { Outlet } from "react-router-dom";
import "../App.css";
import CardProfile from "./CardProfile";
import CarouselFile from "./CarouselFile";
import Footer from "./Footer";
import Header from "./Header";
import Home from "./Home";

import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";




function Main() {
  // return (
  //   <div className="maincontainer">
  //    <Header />
  //     <CarouselFile />
  //     <Home />
  //     <ToastContainer position="top-right" />
  //      <CardProfile/>
  //       <Footer />
  //   </div>
  // );
  return (
    <>
      
      <Header />
      <Outlet />
      <Footer />
    </>
  )
}

export default Main;