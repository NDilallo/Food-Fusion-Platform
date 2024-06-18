import Tab from '@material-ui/core/Tab';
import Tabs from '@material-ui/core/Tabs';
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";
import React, { useEffect, useState } from 'react';
import Card from "../../components/Card/Card.js";
import CardBody from "../../components/Card/CardBody.js";
import CardHeader from "../../components/Card/CardHeader.js";
import GridContainer from "../../components/Grid/GridContainer.js";
import GridItem from "../../components/Grid/GridItem.js";

const useStyles = makeStyles({
  cardCategoryWhite: {
    color: "rgba(255,255,255,.62)",
    margin: "0",
    fontSize: "14px",
    marginTop: "0",
    marginBottom: "0",
  },
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "0px",
    minHeight: "auto",
    fontWeight: "300",
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
    marginBottom: "3px",
    textDecoration: "none",
  },
});

const Restaurant = () => {
  const classes = useStyles();

  const [restaurant, setRestaurant] = useState([{
    name: "Spice World",
    cuisine: "Indian",
    address: "456 Oak Ave, Townsville",
    chefs: [
      { role: 'Main Chef', name: 'Michael Brown' },
      { role: 'Sous Chef', name: 'Emily White' },
      { role: 'Dessert Chef', name: 'David Lee' }
    ]},
    {
      name: "S",
      cuisine: "India",
      address: "456 OaAve, Townsville",
      chefs: [
        { role: 'Main Chef', name: 'Michal Brown' },
        { role: 'Sous Chef', name: 'Emly White' },
        { role: 'Dessert Chef', name: 'Daid Lee' }
      ]}]);
    
  // const [restaurant, setRestaurant] = useState({
  //   name: "Spice World",
  //   cuisine: "Indian",
  //   address: "456 Oak Ave, Townsville",
  //   chefs: [
  //     { role: 'Main Chef', name: 'Michael Brown' },
  //     { role: 'Sous Chef', name: 'Emily White' },
  //     { role: 'Dessert Chef', name: 'David Lee' }
  //   ]}
  //   // {
  //   //   name: "Portofino",
  //   //   cuisine: "Italian",
  //   //   address: "456 Oak Ave, Chicago",
  //   //   chefs: [
  //   //     { role: 'Main Chef', name: 'Michael Spears' },
  //   //     { role: 'Sous Chef', name: 'Emily Lee' },
  //   //     { role: 'Dessert Chef', name: 'David Copper' }
  //   //   ]},
  //);
  // const [restaurant2, setRestaurant2] = useState({
  //     name: "Portofino",
  //     cuisine: "Italian",
  //     address: "456 Oak Ave, Chicago",
  //     chefs: [
  //       { role: 'Main Chef', name: 'Michael Spears' },
  //       { role: 'Sous Chef', name: 'Emily Lee' },
  //       { role: 'Dessert Chef', name: 'David Copper' }
  //     ]},
  // );
  const [chefs, setChefs] = useState({
    executiveChefs: [],
    sousChefs: [],
    pastryChefs: []
  });
  const [employees, setEmployees] = useState([]);
  const [tabIndex, setTabIndex] = useState(0);

  useEffect(() => {
    // Fetch restaurant data
    axios.get('http://localhost:8080/api/restaurant/1') // Assuming restaurant ID 1 for now
      .then(response => {
        
        // setRestaurant(response.data);
        setRestaurant(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the restaurant data!', error);
      });

    // Fetch chefs data
    axios.get('http://localhost:8080/api/chefs')
      .then(response => {
        const executiveChefs = response.data.filter(chef => chef.type === 'Executive Chef');
        const sousChefs = response.data.filter(chef => chef.type === 'Sous Chef');
        const pastryChefs = response.data.filter(chef => chef.type === 'Pastry Chef');
        setChefs({
          executiveChefs,
          sousChefs,
          pastryChefs
        });
      })
      .catch(error => {
        console.error('There was an error fetching the chefs data!', error);
      });

    // Fetch employees data
    axios.get('http://localhost:8080/api/employees')
      .then(response => {
        setEmployees(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the employees data!', error);
      });
  }, []);

  const handleTabChange = (event, newValue) => {
    setTabIndex(newValue);
  };

  return (
    <div>
          {/* <h1>Restaurant List</h1> */}
          {/* <ul>
            {restaurant.map((restaurant, index) => (
              <li key={index}>
                <h2>{restaurant.name}</h2>
                <p>Cuisine: {restaurant.cuisine}</p>
                <p>Address: {restaurant.address}</p>
              </li>
            ))}
          </ul> */}
      <Tabs value={tabIndex} onChange={handleTabChange}>
        <Tab label="Restaurant Info" />
        <Tab label="Chefs" />
        <Tab label="Employees" />
      </Tabs>

      {tabIndex === 0 && (
        <GridContainer>
          <GridItem xs={12} sm={12} md={8}>
            {/* <Card>
              <CardHeader color="primary"> */}
                <h4 className={classes.cardTitleBlack}>Restaurant Information</h4>
                <ul>
            {restaurant.map((restaurant, index) => (
              <li key={index}>
                <h2>{restaurant.name}</h2>
                <p>Cuisine: {restaurant.cuisine}</p>
                <p>Address: {restaurant.address}</p>
              </li>
            ))}
          </ul>
              {/* </CardHeader>
              </Card> */}
              </GridItem>
              </GridContainer>
              /* <CardBody>
                <GridContainer>
                  <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                      //labelText="Restaurant Name"
                      //id="name"
                      formControlProps={{
                        //fullWidth: true,
                      }}
                      inputProps={{
                        //value: restaurant.name,
                        //readOnly: true,
                      }}
                      // inputProps2={{
                      //   value: restaurant2.name,
                      //   readOnly: true,
                      // }}
                    />
                  </GridItem>
                  <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                      labelText="Cuisine"
                      id="cuisine"
                      formControlProps={{
                        //fullWidth: true,
                      }}
                      inputProps={{
                        //value: restaurant.cuisine,
                        //readOnly: true,
                      }}
                      // inputProps2={{
                      //   value: restaurant2.cuisine,
                      //   readOnly: true,
                      // }}
                    />
                  </GridItem>
                </GridContainer>
                <GridContainer>
                  <GridItem xs={12} sm={12} md={12}>
                    <CustomInput
                      labelText="Address"
                      id="address"
                      formControlProps={{
                        //fullWidth: true,
                      }}
                      inputProps={{
                        //value: restaurant.address,
                        //readOnly: true,
                      }}
                      // inputProps2={{
                      //   value: restaurant2.address,
                      //   readOnly: true,
                      // }}
                    />
                  </GridItem>
                </GridContainer>
              </CardBody>*/
           // </Card>
          //</GridItem>
       // </GridContainer>
      )}

      {tabIndex === 1 && (
        <GridContainer>
          {chefs.executiveChefs.map((chef, index) => (
            <GridItem key={index} xs={12} sm={6} md={4}>
              <Card>
                <CardHeader color="primary">
                  <h4 className={classes.cardTitleWhite}>{chef.name}</h4>
                </CardHeader>
                <CardBody>
                  <p><strong>Role:</strong> {chef.role}</p>
                </CardBody>
              </Card>
            </GridItem>
          ))}
        </GridContainer>
      )}

      {tabIndex === 2 && (
        <GridContainer>
          {chefs.sousChefs.map((chef, index) => (
            <GridItem key={index} xs={12} sm={6} md={4}>
              <Card>
                <CardHeader color="primary">
                  <h4 className={classes.cardTitleWhite}>{chef.name}</h4>
                </CardHeader>
                <CardBody>
                  <p><strong>Role:</strong> {chef.role}</p>
                </CardBody>
              </Card>
            </GridItem>
          ))}
        </GridContainer>
      )}

      {tabIndex === 3 && (
        <GridContainer>
          {chefs.pastryChefs.map((chef, index) => (
            <GridItem key={index} xs={12} sm={6} md={4}>
              <Card>
                <CardHeader color="primary">
                  <h4 className={classes.cardTitleWhite}>{chef.name}</h4>
                </CardHeader>
                <CardBody>
                  <p><strong>Role:</strong> {chef.role}</p>
                </CardBody>
              </Card>
            </GridItem>
          ))}
        </GridContainer>
      )}

      {tabIndex === 4 && (
        <GridContainer>
          {employees.map((employee, index) => (
            <GridItem key={index} xs={12} sm={6} md={4}>
              <Card>
                <CardHeader color="primary">
                  <h4 className={classes.cardTitleWhite}>{employee.name}</h4>
                </CardHeader>
                <CardBody>
                  <p><strong>Role:</strong> {employee.role}</p>
                </CardBody>
              </Card>
            </GridItem>
          ))}
        </GridContainer>
      )}
    </div>
  );
};

export default Restaurant;
