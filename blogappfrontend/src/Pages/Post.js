import { Button, Card, CardBody, CardText } from "reactstrap";

const Post = ({ post ={postTitle:"Default",content:"Default"}}) => {
  return (
    <div className="container-fluid">
      <Card className="border-0 shadow-sm mt-3">
        <CardBody>
          <h1>{post.postTitle}</h1>
          <CardText>
            <h2>{post.content}</h2>
          </CardText>
          <div>
            <Button>Read More</Button>
          </div>
        </CardBody>
      </Card>
    </div>
  );
};
export default Post;
