import { useState } from "react";
import { toast } from "react-toastify";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormFeedback,
  FormGroup,
  Input,
  Label,
  Row,
} from "reactstrap";
import { signup } from "../Services/User-SignUp-Services";
const SignUp = () => {
  const [getData, setData] = useState({
    userName: "dummy",
    userEmail: "abc@gmail.com",
    password: "abc",
    about: "This is dummy user",
  });

  const [getErrors, setError] = useState({
    errors: {},
    isError: false,
  });
  const onChangeHandler = (event) => {
    setData({ ...getData, [event.target.id]: event.target.value });
  };

  const onClickHandler = (event) => {
    setData({
      userName: "dummy",
      userEmail: "abc@gmail.com",
      password: "abc",
      about: "This is dummy user",
    });
  };

  const onSubmitHandler = (event) => {
    event.preventDefault();
    console.log(getErrors);
    //passing data to api
    signup(getData)
      .then((resp) => {
        toast.success("user is register successfully with Id=" + resp.id);
        setError({
          errors: "",
          isError: false,
        });
        setData({
          userName: "",
          userEmail: "",
          password: "",
          about: "",
        });
      })
      .catch((error) => {
        console.log(error);
        console.log("Error Log");
        setError({
          ...getErrors,
          //setting error object inside error state var so that we can access the validations
          errors: error,
          isError: true,
        });
      });
  };

  return (
    <div className="container">
      {JSON.stringify(getData)}
      <div>
        <Container>
          <Row className="mt-4">
            <Col sm={{ size: 8, offset: 2 }}>
              <Card color="dark" inverse>
                <CardHeader>Fill the Information for Signing In</CardHeader>
                <CardBody>
                  <Form onSubmit={onSubmitHandler}>
                    <FormGroup row>
                      <Label for="exampleName" sm={2}>
                        Name
                      </Label>
                      <Col sm={10}>
                        <Input
                          onChange={onChangeHandler}
                          id="userName"
                          name="userName"
                          placeholder="Enter your Name"
                          type="text"
                          value={getData.userName}
                          //?.=null safe / working on undefined values wih out getting error if undefined
                          invalid={
                            getErrors.errors?.response?.data?.userName
                              ? true
                              : false
                          }
                        />
                        <FormFeedback>
                          {getErrors.errors?.response?.data?.userName}
                        </FormFeedback>
                      </Col>
                    </FormGroup>

                    <FormGroup row>
                      <Label for="exampleEmail" sm={2}>
                        Email
                      </Label>
                      <Col sm={10}>
                        <Input
                          onChange={onChangeHandler}
                          id="userEmail"
                          name="userEmail"
                          placeholder="Enter your Email"
                          type="email"
                          value={getData.userEmail}
                          invalid={getErrors.errors?.response?.data?.userEmail?true:false}
                        />
                        <FormFeedback>
                          {getErrors.errors?.response?.data?.userEmail}
                        </FormFeedback>
                      </Col>
                    </FormGroup>
                    <FormGroup row>
                      <Label for="examplePassword" sm={2}>
                        Password
                      </Label>
                      <Col sm={10}>
                        <Input
                          onChange={onChangeHandler}
                          id="password"
                          name="password"
                          placeholder="Enter your Password "
                          type="password"
                          value={getData.password}
                          invalid={getErrors.errors?.response?.data?.password?true:false}
                        />
                        <FormFeedback>
                          {getErrors.errors?.response?.data?.password}
                        </FormFeedback>
                      </Col>
                    </FormGroup>
                    <FormGroup row>
                      <Label for="ConfirmExamplePassword" sm={2}>
                        Re-enter
                      </Label>
                      <Col sm={10}>
                        <Input
                          id="ConfirmPassword"
                          name="ConfirmPassword"
                          placeholder="Confirm Password "
                          type="password"
                        />
                      </Col>
                    </FormGroup>

                    <FormGroup>
                      <Label for="exampleText">About</Label>
                      <Input
                        onChange={onChangeHandler}
                        id="about"
                        name="text"
                        type="textarea"
                        value={getData.about}
                        invalid={getErrors.errors?.response?.data?.about?true:false}
                      />
                      <FormFeedback>
                        {getErrors.errors?.response?.data?.about}
                      </FormFeedback>
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
                          <Input id="checkbox2" type="checkbox" />
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
                            Submit
                          </Button>
                          <Button
                            color="secondary"
                            className="ms-4"
                            type="reset"
                            onClick={onClickHandler}
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
  );
};
export default SignUp;
