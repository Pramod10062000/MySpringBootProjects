export const isLogin = () => {
  let data = localStorage.getItem("data");
  // console.log(data);
  if (data != null) {
    return true;
  } else {
    return false;
  }
};

//saving the data into local storage
export const doLogin = (data, next) => {
  localStorage.setItem("data", JSON.stringify(data));
  next();
};

export const doLogOut = (props) => {
  localStorage.removeItem("data");
};

export const getCurrentUserDetails = () => {
  if (isLogin()) {
    return  JSON.parse(localStorage.getItem("data")).user;
  } else {
    return undefined;
  }
};


export const getToken=()=>{
  if(isLogin()){
    return JSON.parse(localStorage.getItem("data")).token;
  }
  else{
    return null;
  }
}
