import React from "react"
import { useAuth } from "./AuthContext"
import {  Route,Navigate } from "react-router-dom";


const ProtectedRoute = ({component:Component, roles, ...rest})=>{
    const {user} = useAuth();
    console.log("User ",user)
    // return user?<Component {...props} />:<Navigate to="/signup" />;
    return (
        <Route {...rest} render={(props)=>{
            if(!user){
                return  <Navigate to="/signup" />
            }

            if(roles && !roles.includes(user.userRole)){
                return <Navigate to="/" />
            }

            return <Component {...props} />;
        }} 
        
        /> 
    )

}

export default ProtectedRoute;