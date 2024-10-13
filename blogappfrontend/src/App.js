import { useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";
import PrivateRoutes from "./Component/PrivateRoutes";
import About from "./Pages/About";
import CustomNavbar from "./Pages/CustomNavbar";
import Feed from "./Pages/Feed";
import Home from "./Pages/Home";
import Login from "./Pages/Login";
import ProfileInfo from "./Pages/ProfileInfo";
import Services from "./Pages/Services";
import SignUp from "./Pages/SignUp";
import UserDashboard from "./Pages/UserDashboard";
function App() {
  const [getLogin, setIsLogin] = useState(false);
  const [user, setUser] = useState(undefined);
  return (
    <div>
      <CustomNavbar
        getLogin={getLogin}
        setIsLogin={setIsLogin}
        user={user}
        setUser={setUser}
      />

      <BrowserRouter>
        <ToastContainer position="bottom-center" />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/about" element={<About />} />
          <Route path="/services" element={<Services />} />
          <Route path="p" element={<ProfileInfo />} />

          <Route path="/user" element={<PrivateRoutes />}>
            <Route path="dashboard" element={<UserDashboard />} />
          </Route>
          <Route path="/user" element={<PrivateRoutes />}>
            <Route path="p" element={<ProfileInfo />} />
          </Route>

          <Route path="/feed" element={<Feed />}/>
            
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
