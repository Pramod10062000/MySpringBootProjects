import { getCurrentUserDetails } from "../auth";

import { myAxios, privateAxios } from "./Helper";
//this is a get request
export const Get_Category = () => {
  return myAxios.get("/api/category/getAll").then(response=>{return response.data});
};

//this is a get request
export const Post_Category = (categoryObj) => {
  let userId=getCurrentUserDetails().id;
  let categoryId=categoryObj.categoryId
  console.log(userId);
  console.log(categoryId);
  

  // "/user/{userID}/category/{catId}/posts"
  return privateAxios.post(`/api/user/${userId}/category/${categoryId}/posts`,categoryObj).then(response=>{return response.data});
};

export const Get_Post = () => {
  return myAxios.get("/api/Allposts").then(response=>{return response.data});
};