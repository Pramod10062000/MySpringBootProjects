import axios from "axios";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import {
    Button,
    Card,
    CardBody,
    Container,
    Form,
    Input,
    Label
} from "reactstrap";
import { Post_Category } from "../Services/Post-Category";
const Addpost = (props) => {
  let v;
  
  
  const [getCategory, setCategory] = useState([]);

  const [getselectedData, setSelectedData] = useState({
    content: "",
    postTitle: "",
    categoryId: "",
  });

  const onchangeHandler = (event) => {
    setSelectedData({
      ...getselectedData,
      [event.target.name]:event.target.value,
    });
  };
  const initialData = () => {
    try {
      axios
        .get("http://localhost:5000/api/category/getAll")
        .then((response) => {
          console.log(response.data);
          setCategory([...response.data]);
        })
        .catch((err) => {
          console.log(err);
        });
    } catch (error) {
      console.log(error);
    }
  };
  //Acts as a constructor, it will be executed only once and only at the start of the code only once becuase of empty array bracket
  useEffect(() => {
    initialData();
  }, []);

  const onSubmitHandler = (event) => {
    event.preventDefault();
    
   
 
    if(getselectedData.categoryId===''){
        alert("empty id not allowed")
        return
    }
    if(getselectedData.content===''){
        alert("empty content not allowed")
        return
    }

    if(getselectedData.postTitle===''){
        alert("empty postTitle not allowed")
        return
    }
    // console.log(getselectedData);

    // submiting data to backend
    Post_Category(getselectedData).then(
       data=>{ 
        toast.success("Post Successful");
        setSelectedData({content: "",
        postTitle: "",
        categoryId: "",
      });
       }).catch((error)=>{
      toast.error("Error-Post not created")
      
       });

  };

  return (
    <div className="shadow mt-4 ms-4 mx-4">
      {/* {JSON.stringify(getselectedData)} */}
      <Card>
        <CardBody>
          <h3>Whats going on Inside ?</h3>

          <Form onSubmit={onSubmitHandler}>
            <div className="my-3">
              <Label>Post Title</Label>
              <Input
                bsSize="lg"
                valid
                name="postTitle"
                placeholder="Enter here"
                id="postTitle"
                onChange={onchangeHandler}
                maxLength={100}
                value={getselectedData.postTitle}
              />
            </div>

            <div className="my-3">
              <Label>Post Content</Label>
              <Input
                bsSize="lg"
                type="textarea"
                valid
                placeholder="Enter Post Content here"
                name="content"
                id="content"
                maxLength={1000}
                onChange={onchangeHandler}
                style={{ height: "200px" }}
                value={getselectedData.content}
              />

              <div className="mt-2">
                <Label for="exampleSelect">Post Category</Label>
                <Input
                  id="categoryId"
                  name="categoryId"
                  type="select"
                  title="id"
                  placeholder="Select any category "
                  onChange={onchangeHandler}
                >
                  {getCategory.map((category, index) => {
                    // console.log(category)
                    return (
                      <option value={category.id} key={index}>
                       {category.id}{category.categoryTitle}{ category.categoryDescription}
                   
                      </option>
                    );
                  })}
                </Input>
              </div>
            </div>
            <Container className="text-center">
              <Button type="submit" color="primary">Post</Button>
              <Button color="danger" className="ms-2" type="reset">
                Reset
              </Button>
            </Container>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};
export default Addpost;
