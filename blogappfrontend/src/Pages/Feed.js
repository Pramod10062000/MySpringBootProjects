import axios from "axios";
import { useEffect, useState } from "react";
import { Pagination, PaginationItem, PaginationLink } from "reactstrap";

const Feed = () => {
  const [responseData, setResponseData] = useState({
    content: [],
    totalPages: "",
    totalElements: "",
    pageSize: "",
    lastPage: false,
  });

  const fetchData = async (pageNumber=0,pageSize=3) => {
    if(responseData.lastPage){
        return
    }
    if(responseData.pageNumber==-1){
        return
    }
    try {
      const response = await axios.get(`http://localhost:5000/api/Allposts?&pageNumber=${pageNumber}&pageSize=${pageSize}`);
      console.log("API Response:", response);
      setResponseData(response.data); // Store the entire response data
    } catch (error) {}
  };

  // Fetch data for the current page
  useEffect(() => {
    fetchData();
    // console.log("Inside useEffect");
    // console.log(responseData);
  }, []);

  const onChangePage=async (pageNumber=0,pageSize=3)=>{

    if(pageNumber>responseData.totalPages-1){
        return
    }
    if(pageNumber==-1){
        return
    }
    try{
        // console.log(pageNumber)
        const response=await axios.get(`http://localhost:5000/api/Allposts?&pageNumber=${pageNumber}&pageSize=${pageSize}`);
        setResponseData(response.data);
        console.log("API Response:", response);
        window.scroll(0,0)
        // console.log(responseData)

    }catch(error){
        console.log(error);

    }
    
   
  }

  return (
    <div>
      {/* {JSON.stringify(responseData)} */}
      <ul>
        {responseData.content.map((post) => (
          <li key={post.postId}>
            <h2>Post Title :{post.postTitle}</h2>
            <p>{post.content}</p>
            {/* <img src={post.imageName} alt={post.postTitle} /> */}
            <p>Category: {post.category.categoryTitle}</p>
            <p>Added Date: {new Date(post.addedDate).toLocaleDateString()}</p>
            <p>User: {post.user.userName}</p>
            <p>Page Number {responseData.pageNumber}</p>
            <p> pageSize {responseData.pageSize}</p>
            <p> totalPages {responseData.totalPages}</p>
            <p> totalElements {responseData.totalElements}</p>
            <p> lastPage {responseData.lastPage}</p>
          </li>
        ))}
      </ul>

      <Pagination size="lg">
        <PaginationItem onClick={()=>onChangePage(responseData.pageNumber-1)} disabled={responseData.pageNumber == 0}>
          <PaginationLink previous >Previous</PaginationLink>
        </PaginationItem>
        {[...Array(responseData.totalPages)].map((item, index) => {
          return (
            <PaginationItem onClick={()=>onChangePage(index)} active={index==responseData.pageNumber}>
              <PaginationLink>{index + 1}</PaginationLink>
            </PaginationItem>
          );
        })}

        <PaginationItem onClick={()=>onChangePage(responseData.pageNumber+1)} disabled={responseData.lastPage}>
          <PaginationLink next>Next</PaginationLink>
        </PaginationItem>
      </Pagination>
    </div>
  );
};

export default Feed;
