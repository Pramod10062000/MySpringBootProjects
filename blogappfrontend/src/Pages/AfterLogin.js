import { useEffect } from "react";

const AfterLogin = () => {
  const [getData, setData] = useState({
    "": "",
    "": "",
    "": "",
    "": "",
  });

  useEffect(() => {
    try {
      let a = axios.getData("");
    } catch (Exception) {
      console.log(Exception);
    }
  });

  return <div></div>;
};
export default AfterLogin;
