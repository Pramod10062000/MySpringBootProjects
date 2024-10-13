import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";
import { doLogin } from "../auth";
import { login } from "../Services/User-Login-Services";
const Login = () => {
  const navigate=useNavigate();

  const [data, setdata] = useState({
    username: "Login@gmail.com",
    password: "Login@Password",
  });

  const onchangeHandler = (event) => {
    setdata({
      ...data,
      [event.target.id]: event.target.value,
    });
  };
  const onSubmitHandler = (event) => {
    event.preventDefault();
    //validation for empty details
    if (data.username == "") {
      toast.error("Username is required");
      return;
    }
    if (data.password.trim() == "") {
      toast.error("Password is required");
      return;
    }
    // console.log(data);
    login(data)
      //resp will contain the token sent by the backend jwt token
      .then((resp) => {
       
        //saving the data into local storage
        doLogin(resp, () => {
          console.log(" login Data is saved in the backend");
        });
        // console.log(resp);

        //redirecting to user/Dashboard or /profile after sucessfull login
        navigate("/user/dashboard");
        toast.success("Login Success");
      })
      .catch((error) => {
        toast.error("Login failed");
        console.log(error);
      });
  };

  const onResetHandler = () => {
    setdata({
      username: "",
      password: "",
    });
  };

  return (
    <div>
      {JSON.stringify(data)}
      <div className="container">
        <div>
          <Container>
            <Row className="mt-4">
              <Col sm={{ size: 8, offset: 2 }}>
                <Card color="dark" inverse>
                  <CardHeader>Fill the Information for Logging In</CardHeader>
                  <CardBody>
                    <Form onSubmit={onSubmitHandler}>
                      <FormGroup row>
                        <Label for="exampleEmail" sm={2}>
                          Email
                        </Label>
                        <Col sm={10}>
                          <Input
                            id="username"
                            name="username"
                            placeholder="Enter your Email"
                            type="email"
                            value={data.username}
                            onChange={onchangeHandler}
                          />
                        </Col>
                      </FormGroup>
                      <FormGroup row>
                        <Label for="examplePassword" sm={2}>
                          Password
                        </Label>
                        <Col sm={10}>
                          <Input
                            id="password"
                            name="password"
                            placeholder="Enter your Password "
                            type="password"
                            value={data.password}
                            onChange={onchangeHandler}
                          />
                        </Col>
                      </FormGroup>
                      <FormGroup row>
                        <Label for="ConfirmExamplePassword" sm={2}>
                          Confirm Password
                        </Label>
                        <Col sm={10}>
                          <Input
                            id="ConfirmExamplePassword"
                            name="password"
                            placeholder="Confirm Password "
                            type="password"
                          />
                        </Col>
                      </FormGroup>

                      <FormGroup row>
                        <Label for="checkbox2" sm={2}>
                          Checkbox
                        </Label>
                        <Col
                          sm={{
                            size: 10,
                          }}
                        >
                          <FormGroup check>
                            <Input id="checkbox2" type="checkbox" />{" "}
                            <Label check>Not a Robot</Label>
                          </FormGroup>
                        </Col>
                      </FormGroup>
                      <FormGroup check row>
                        <Col
                          sm={{
                            offset: 2,
                            size: 10,
                          }}
                        >
                          <Container className="text-center">
                            <Button outline color="light">
                              Login
                            </Button>
                            <Button
                              color="secondary"
                              className="ms-4"
                              type="reset"
                              onClick={onResetHandler}
                            >
                              Reset
                            </Button>
                          </Container>
                        </Col>
                      </FormGroup>
                    </Form>
                  </CardBody>
                </Card>
              </Col>
            </Row>
          </Container>
        </div>
      </div>
    </div>
  );
};

export default Login;
