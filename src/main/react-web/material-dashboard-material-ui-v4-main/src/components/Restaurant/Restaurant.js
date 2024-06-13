import { Button, Card, CardContent, CardHeader, Grid, TextField } from "@material-ui/core";
import axios from "axios";
import { useEffect, useState } from "react";

const Restaurant = () => {
  const [restaurant, setRestaurant] = useState({
    name: "",
    cuisine: "",
    address: "",
  });
  const [chefs, setChefs] = useState([]);
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    // Fetch initial data
    axios.get('http://localhost:8080/api/restaurant/1') // Assuming restaurant ID 1 for now
      .then(response => {
        setRestaurant(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the restaurant data!', error);
      });

    axios.get('http://localhost:8080/api/chefs')
      .then(response => {
        setChefs(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the chefs data!', error);
      });

    axios.get('http://localhost:8080/api/employees')
      .then(response => {
        setEmployees(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the employees data!', error);
      });
  }, []);

  const handleInputChange = (event) => {
    const { id, value } = event.target;
    setRestaurant(prevState => ({
      ...prevState,
      [id]: value
    }));
  };

  const handleUpdate = () => {
    axios.put(`http://localhost:8080/api/restaurant/1`, restaurant)
      .then(() => {
        alert("Restaurant updated successfully!");
      })
      .catch(error => {
        console.error('There was an error updating the restaurant!', error);
        alert("Restaurant update failed!");
      });
  };

  return (
    <div>
      <Grid container spacing={3}>
        <Grid item xs={12} md={8}>
          <Card>
            <CardHeader title="Restaurant Information" />
            <CardContent>
              <TextField
                id="name"
                label="Restaurant Name"
                value={restaurant.name}
                onChange={handleInputChange}
                fullWidth
                margin="normal"
              />
              <TextField
                id="cuisine"
                label="Cuisine"
                value={restaurant.cuisine}
                onChange={handleInputChange}
                fullWidth
                margin="normal"
              />
              <TextField
                id="address"
                label="Address"
                value={restaurant.address}
                onChange={handleInputChange}
                fullWidth
                margin="normal"
              />
              <Button onClick={handleUpdate} color="primary">Update Restaurant</Button>
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card>
            <CardHeader title="Chefs" />
            <CardContent>
              {chefs.map((chef, index) => (
                <div key={index}>
                  <h4>{chef.name}</h4>
                  <p><strong>Role:</strong> {chef.role}</p>
                </div>
              ))}
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={4}>
          <Card>
            <CardHeader title="Employees" />
            <CardContent>
              {employees.map((employee, index) => (
                <div key={index}>
                  <h4>{employee.name}</h4>
                  <p><strong>Role:</strong> {employee.role}</p>
                </div>
              ))}
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </div>
  );
};

export default Restaurant;
