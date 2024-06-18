import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import React, { useEffect, useState } from 'react';
import { Button, Card, Col, Container, Dropdown, Row, Tab, Tabs } from 'react-bootstrap';

const Restaurant = () => {
  const [restaurant, setRestaurant] = useState([{
    name: "Spice World",
    cuisine: "Indian",
    address: "456 Oak Ave, Chicago",
    chefs: [
      { role: 'Executive Chef', name: 'Michael Brown' },
      { role: 'Sous Chef', name: 'Emily White' },
      { role: 'Dessert Chef', name: 'David Lee' }
    ]},
    {
      name: "Chicago Diner",
      cuisine: "American",
      address: "4444 halsted, Chicago",
      chefs: [
        { role: 'Executive Chef', name: 'DJ Brown' },
        { role: 'Sous Chef', name: 'AJ White' },
        { role: 'Dessert Chef', name: 'MJ Lee' }
      ]},
      {
        name: "The Gourmet Kitchen",
        cuisine: "American",
        address: "1234 N LSD, Chicago",
        chefs: [
          { role: 'Executive Chef', name: 'Emma' },
          { role: 'Sous Chef', name: 'Watson' },
          { role: 'Dessert Chef', name: 'Thomas' }
        ]},
        {
          name: "Demera",
          cuisine: "Ethiopian",
          address: "1234 N Broadway, Chicago",
          chefs: [
            { role: 'Executive Chef', name: 'Leo' },
            { role: 'Sous Chef', name: 'Nardo' },
            { role: 'Dessert Chef', name: 'DiCaprio' }
          ]},
          {
            name: "Portofino",
            cuisine: "Italian",
            address: "1234 E Wacker, Chicago",
            chefs: [
              { role: 'Executive Chef', name: 'Martin' },
              { role: 'Sous Chef', name: 'Cooper' },
              { role: 'Dessert Chef', name: 'Junior' }
            ]},
          {
            name: "Tzuco",
            cuisine: "Mexican",
            address: "1234 State St, Chicago",
            chefs: [
              { role: 'Executive Chef', name: 'Jesus' },
              { role: 'Sous Chef', name: 'Rodriqguez' },
              { role: 'Dessert Chef', name: 'Angelo' }
            ]}
    ]);
  
  const initialChefs = {
    executiveChefs: [
      { name: restaurant[0].chefs[0].name, role: "Executive Chef", restaurant: { name: restaurant[0].name, address: restaurant[0].address, cuisine: restaurant[0].cuisine } },
      { name: restaurant[1].chefs[0].name, role: "Executive Chef", restaurant: { name: restaurant[1].name, address: restaurant[1].address, cuisine: restaurant[1].cuisine } },
      { name: restaurant[2].chefs[0].name, role: "Executive Chef", restaurant: { name: restaurant[2].name, address: restaurant[2].address, cuisine: restaurant[2].cuisine } },
      { name: restaurant[3].chefs[0].name, role: "Executive Chef", restaurant: { name: restaurant[3].name, address: restaurant[3].address, cuisine: restaurant[3].cuisine } },
      { name: restaurant[4].chefs[0].name, role: "Executive Chef", restaurant: { name: restaurant[4].name, address: restaurant[4].address, cuisine: restaurant[4].cuisine } },
      { name: restaurant[5].chefs[0].name, role: "Executive Chef", restaurant: { name: restaurant[5].name, address: restaurant[5].address, cuisine: restaurant[5].cuisine } },
    ],
    sousChefs: [
      { name: restaurant[0].chefs[1].name, role: "Sous Chef", restaurant: { name: restaurant[0].name, address: restaurant[0].address, cuisine: restaurant[0].cuisine } },
      { name: restaurant[1].chefs[1].name, role: "Sous Chef", restaurant: { name: restaurant[1].name, address: restaurant[1].address, cuisine: restaurant[1].cuisine } },
      { name: restaurant[2].chefs[1].name, role: "Sous Chef", restaurant: { name: restaurant[2].name, address: restaurant[2].address, cuisine: restaurant[2].cuisine } },
      { name: restaurant[3].chefs[1].name, role: "Sous Chef", restaurant: { name: restaurant[3].name, address: restaurant[3].address, cuisine: restaurant[3].cuisine } },
      { name: restaurant[4].chefs[1].name, role: "Sous Chef", restaurant: { name: restaurant[4].name, address: restaurant[4].address, cuisine: restaurant[4].cuisine } },
      { name: restaurant[5].chefs[1].name, role: "Sous Chef", restaurant: { name: restaurant[5].name, address: restaurant[5].address, cuisine: restaurant[5].cuisine } },
    ],
    dessertChefs: [
      { name: restaurant[0].chefs[2].name, role: "Dessert Chef", restaurant:{name: restaurant[0].name, address: restaurant[0].address, cuisine: restaurant[0].cuisine } },
      { name: restaurant[1].chefs[2].name, role: "Dessert Chef", restaurant: { name: restaurant[1].name, address: restaurant[1].address, cuisine: restaurant[1].cuisine } },
      { name: restaurant[2].chefs[2].name, role: "Dessert Chef", restaurant:{name: restaurant[2].name, address: restaurant[2].address, cuisine: restaurant[2].cuisine } },
      { name: restaurant[3].chefs[2].name, role: "Dessert Chef", restaurant: { name: restaurant[3].name, address: restaurant[3].address, cuisine: restaurant[3].cuisine } },
      { name: restaurant[4].chefs[2].name, role: "Dessert Chef", restaurant:{name: restaurant[4].name, address: restaurant[4].address, cuisine: restaurant[4].cuisine } },
      { name: restaurant[5].chefs[2].name, role: "Dessert Chef", restaurant: { name: restaurant[5].name, address: restaurant[5].address, cuisine: restaurant[5].cuisine } },
    ]
  };
  
  const options = [
    { value: 'executiveChefs', label: 'Executive Chefs' },
    { value: 'sousChefs', label: 'Sous Chefs' },
    { value: 'dessertChefs', label: 'Dessert Chefs' }
  ];

  const initialWaiters = [
    { name: "John Doe", restaurant: { name: restaurant[3].name, address: restaurant[3].address } },
    { name: "Dakota Johnson", restaurant: { name: restaurant[1].name, address: restaurant[1].address } },
    { name: "Barbara Barber", restaurant: { name: restaurant[5].name, address: restaurant[5].address } },
    { name: "Cheyyene Cabar", restaurant: { name: restaurant[4].name, address: restaurant[4].address } },
  ];
  const [setEmployees] = useState([]);
  const [tabIndex, setTabIndex] = useState(0);

  const [selectedCategory, setSelectedCategory] = useState(options[0].value);
  const [chefs, setChefs] = useState(initialChefs);
  const [waiters] = useState(initialWaiters);
  const [selectedChef, setSelectedChef] = useState(null);

  const handleChange = (event) => {
    setSelectedCategory(event);
    setSelectedChef(null); // Reset selected chef when changing category
  };

  const handleChefClick = (chef) => {
    setSelectedChef(chef);
  };

  const currentChefs = chefs[selectedCategory];

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
        const dessertChefs = response.data.filter(chef => chef.type === 'Dessert Chef');
        setChefs({
          executiveChefs,
          sousChefs,
          dessertChefs
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
    <Container>
      <Tabs activeKey={tabIndex} onSelect={(k) => handleTabChange(null, k)}>
        <Tab eventKey={0} title="Restaurant Information">
          <Row>
            {restaurant.map((restaurant, index) => (
              <Col key={index} xs={12} sm={6} md={4}>
                <Card className="mt-3">
                  <Card.Header className="bg-primary text-white">{restaurant.name}</Card.Header>
                  <Card.Body>
                    <Card.Text><strong>Cuisine: </strong> {restaurant.cuisine}</Card.Text>
                    <Card.Text><strong>Address: </strong> {restaurant.address}</Card.Text>
                  </Card.Body>
                </Card>
              </Col>
            ))}
          </Row>
        </Tab>

        <Tab eventKey={1} title="Chefs">
          <Dropdown onSelect={handleChange} className="mt-3 mb-3">
            <Dropdown.Toggle variant="primary" id="dropdown-basic">
              {options.find(option => option.value === selectedCategory).label}
            </Dropdown.Toggle>
            <Dropdown.Menu>
              {options.map(option => (
                <Dropdown.Item key={option.value} eventKey={option.value}>
                  {option.label}
                </Dropdown.Item>
              ))}
            </Dropdown.Menu>
          </Dropdown>
          <Row>
            {currentChefs.map((chef, index) => (
              <Col key={index} xs={12} sm={6} md={4}>
                <Card className="mt-3">
                  <Card.Header className="bg-primary text-white">{chef.name}</Card.Header>
                  <Card.Body>
                    <Card.Text><strong>Role:</strong> {chef.role}</Card.Text>
                    <Button variant="primary" onClick={() => handleChefClick(chef)}>
                      View Restaurant Details
                    </Button>
                  </Card.Body>
                </Card>
              </Col>
            ))}
          </Row>
          {selectedChef && (
            <Card className="mt-3">
              <Card.Header className="bg-secondary text-white">Restaurant Details</Card.Header>
              <Card.Body>
                <Card.Text><strong>Name:</strong> {selectedChef.restaurant.name}</Card.Text>
                <Card.Text><strong>Address:</strong> {selectedChef.restaurant.address}</Card.Text>
                <Card.Text><strong>Cuisine:</strong> {selectedChef.restaurant.cuisine}</Card.Text>
              </Card.Body>
            </Card>
          )}
        </Tab>

        <Tab eventKey={2} title="Employees">
          <Row>
            {Object.keys(chefs).map((category) =>
              chefs[category].map((chef, index) => (
                <Col key={index} xs={12} sm={6} md={4}>
                  <Card className="mt-3">
                    <Card.Header className="bg-primary text-white">{chef.name}</Card.Header>
                    <Card.Body>
                      <Card.Text><strong>Role:</strong> {chef.role}</Card.Text>
                      <Card.Text><strong>Restaurant:</strong> {chef.restaurant.name}</Card.Text>
                    </Card.Body>
                  </Card>
                </Col>
              ))
            )}
            {waiters.map((waiter, index) => (
              <Col key={index} xs={12} sm={6} md={4}>
                <Card className="mt-3">
                  <Card.Header className="bg-primary text-white">{waiter.name}</Card.Header>
                  <Card.Body>
                    <Card.Text><strong>Role:</strong> Waiter</Card.Text>
                    <Card.Text><strong>Restaurant:</strong> {waiter.restaurant.name}</Card.Text>
                  </Card.Body>
                </Card>
              </Col>
            ))}
          </Row>
        </Tab>
      </Tabs>
    </Container>
  );
};

export default Restaurant;
