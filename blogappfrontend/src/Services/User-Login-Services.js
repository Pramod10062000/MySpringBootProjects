import { myAxios } from "./Helper";

export const login = (loginData) => {
  return (myAxios.post("/api/auth/login",loginData).then((response) => response.data));
};
