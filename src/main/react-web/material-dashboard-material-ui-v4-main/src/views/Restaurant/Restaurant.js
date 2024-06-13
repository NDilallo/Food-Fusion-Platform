import Tab from '@material-ui/core/Tab';
import Tabs from '@material-ui/core/Tabs';
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";
import React, { useEffect, useState } from 'react';
import Card from "../../components/Card/Card.js";
import CardBody from "../../components/Card/CardBody.js";
import CardHeader from "../../components/Card/CardHeader.js";
import CustomInput from "../../components/CustomInput/CustomInput.js";
import GridContainer from "../../components/Grid/GridContainer.js";
import GridItem from "../../components/Grid/GridItem.js";

const styles = {
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
};

const useStyles = makeStyles(styles);

export default function Restaurant() {
  const classes = useStyles();
  const [restaurant, setRestaurant] = useState({
    name: "",
    cuisine: "",
    address: "",
  });
  const [chefs, setChefs] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [tabIndex, setTabIndex] = useState(0);

  useEffect(() => {
    // Fetch restaurant data
    axios.get('http://localhost:8080/api/restaurant/1') // Assuming restaurant ID 1 for now
      .then(response => {
        setRestaurant(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the restaurant data!', error);
      });

    // Fetch chefs data
    axios.get('http://localhost:8080/api/chefs')
      .then(response => {
        setChefs(response.data);
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
      <Tabs value={tabIndex} onChange={handleTabChange}>
        <Tab label="Restaurant Info" />
        <Tab label="Chefs" />
        <Tab label="Employees" />
      </Tabs>

      {tabIndex === 0 && (
        <GridContainer>
          <GridItem xs={12} sm={12} md={8}>
            <Card>
              <CardHeader color="primary">
                <h4 className={classes.cardTitleWhite}>Restaurant Information</h4>
              </CardHeader>
              <CardBody>
                <GridContainer>
                  <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                      labelText="Restaurant Name"
                      id="name"
                      formControlProps={{
                        fullWidth: true,
                      }}
                      inputProps={{
                        value: restaurant.name,
                        readOnly: true,
                      }}
                    />
                  </GridItem>
                  <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                      labelText="Cuisine"
                      id="cuisine"
                      formControlProps={{
                        fullWidth: true,
                      }}
                      inputProps={{
                        value: restaurant.cuisine,
                        readOnly: true,
                      }}
                    />
                  </GridItem>
                </GridContainer>
                <GridContainer>
                  <GridItem xs={12} sm={12} md={12}>
                    <CustomInput
                      labelText="Address"
                      id="address"
                      formControlProps={{
                        fullWidth: true,
                      }}
                      inputProps={{
                        value: restaurant.address,
                        readOnly: true,
                      }}
                    />
                  </GridItem>
                </GridContainer>
              </CardBody>
            </Card>
          </GridItem>
        </GridContainer>
      )}

      {tabIndex === 1 && (
        <GridContainer>
          <GridItem xs={12}>
            <Card>
              <CardHeader color="primary">
                <h4 className={classes.cardTitleWhite}>Chefs</h4>
              </CardHeader>
              <CardBody>
                <GridContainer>
                  {chefs.map((chef, index) => (
                    <GridItem key={index} xs={12} sm={6} md={4}>
                      <Card>
                        <CardBody>
                          <h4>{chef.name}</h4>
                          <p><strong>Role:</strong> {chef.role}</p>
                        </CardBody>
                      </Card>
                    </GridItem>
                  ))}
                </GridContainer>
              </CardBody>
            </Card>
          </GridItem>
        </GridContainer>
      )}

      {tabIndex === 2 && (
        <GridContainer>
          <GridItem xs={12}>
            <Card>
              <CardHeader color="primary">
                <h4 className={classes.cardTitleWhite}>Employees</h4>
              </CardHeader>
              <CardBody>
                <GridContainer>
                  {employees.map((employee, index) => (
                    <GridItem key={index} xs={12} sm={6} md={4}>
                      <Card>
                        <CardBody>
                          <h4>{employee.name}</h4>
                          <p><strong>Role:</strong> {employee.role}</p>
                        </CardBody>
                      </Card>
                    </GridItem>
                  ))}
                </GridContainer>
              </CardBody>
            </Card>
          </GridItem>
        </GridContainer>
      )}
    </div>
  );
}
