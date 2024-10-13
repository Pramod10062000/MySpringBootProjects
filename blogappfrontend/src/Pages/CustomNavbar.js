import "bootstrap/dist/css/bootstrap.min.css";
import { useEffect, useState } from "react";
import { BrowserRouter, NavLink as ReactLink } from "react-router-dom";
import {
  Collapse,
  DropdownItem,
  DropdownMenu,
  DropdownToggle,
  Nav,
  Navbar,
  NavbarBrand,
  NavbarText,
  NavbarToggler,
  NavItem,
  NavLink,
  UncontrolledDropdown,
} from "reactstrap";
import { doLogOut, getCurrentUserDetails, isLogin } from "../auth";
const CustomNavbar = (props) => {
  const [isOpen, setIsOpen] = useState(false);

  // const [getLogin, setIsLogin] = useState(false);
  // const [user, setUser] = useState(undefined);

  useEffect(() => {
    // console.log("Inside useEffect");

    props.setIsLogin(isLogin());
    props.setUser(getCurrentUserDetails());
    // console.log(props.user);
    // console.log(props.getLogin);
  }, [props.getLogin]);

  const onClickHandler = () => {
    doLogOut();
    props.setIsLogin(false);
  };

  return (
    <div>
    
    
      <BrowserRouter>
        <Navbar color="dark" dark expand="md" fixed="">
          <NavbarBrand tag={ReactLink} to="/">
            BlogApp
          </NavbarBrand>
          <NavbarToggler
            onClick={() => {
              setIsOpen(!isOpen);
            }}
          />
          <Collapse isOpen={isOpen} navbar>
            <Nav className="me-auto" navbar>
              <NavItem>
                <NavLink tag={ReactLink} to="/">
                  Home
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink href="/about">About</NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/feed">
                  Feed
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/user/dashboard">
                  Mak-A-Post
                </NavLink>
              </NavItem>

              {/* Conditional rendering of login signup and username components */}

              {props.getLogin && (
                <>
                  <NavItem>
                    <NavLink onClick={onClickHandler} tag={ReactLink}>
                      Logout
                    </NavLink>
                  </NavItem>

                  <NavItem>
                    <NavLink tag={ReactLink}>{props.user.userEmail}</NavLink>
                  </NavItem>
                </>
              )}

              {!props.getLogin && (
                <>
                  <NavItem>
                    <NavLink tag={ReactLink} to="/login">
                      Login
                    </NavLink>
                  </NavItem>
                  <NavItem>
                    <NavLink tag={ReactLink} to="/signup">
                      SignUp
                    </NavLink>
                  </NavItem>
                </>
              )}

              <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav caret>
                  Connect Us
                </DropdownToggle>
                <DropdownMenu right>
                  <DropdownItem tag={ReactLink} to="/services">
                    Insta
                  </DropdownItem>
                  <DropdownItem>X</DropdownItem>
                  <DropdownItem divider />
                  <DropdownItem>Contact</DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown>
            </Nav>
            <NavbarText>Information</NavbarText>
          </Collapse>
        </Navbar>
      </BrowserRouter>
    </div>
  );
};
export default CustomNavbar;
