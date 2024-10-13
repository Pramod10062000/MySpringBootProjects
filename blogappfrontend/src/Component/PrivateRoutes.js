import { Navigate, Outlet } from "react-router-dom";
import { isLogin } from "../auth";

const PrivateRoutes = () => {
  {
    /* important keyword for working of nested components -<Outlet/>
        if specified then only the nested components will render else the nested url based components wont work */
  }

  let loggedIn=isLogin();
  console.log(loggedIn)
  if (loggedIn) {
    return <Outlet />;
  } else {
    return <Navigate to={"/login"} />;
  }
};
export default PrivateRoutes;
