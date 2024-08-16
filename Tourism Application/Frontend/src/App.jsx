import "./App.css";

import "react-toastify/dist/ReactToastify.css";
import Main from "./components/Main";
import Main2 from "./components/Main2";
import { Route, Routes,Router } from "react-router-dom";
import SignupSigIn from "./components/SignupSigIn";
import Admin from "./components/Admin";
import Bookings from "./components/Bookings";
import Home from "./components/Home";
import ProtectedRoute from "./ProtectedRoute";
import { AuthProvider, useAuth } from "./AuthContext";

function App() {
  return (
    <AuthProvider>
        <Routes>
            <Route path="/" element={<Main /> }>
            <Route index element={<Main2 />} />
            <Route path="/home" element={<Home /> } />
            <Route path="signup" element={<SignupSigIn /> } />
            <Route path="/admin" element={<Admin />} />
            <Route path="/bookings" element={<Bookings />} />
          </Route>
        </Routes>
    </AuthProvider>
    // <div className="appcontainer">
      
    //   <Main/>
      
    // </div>
  );
}

export default App;