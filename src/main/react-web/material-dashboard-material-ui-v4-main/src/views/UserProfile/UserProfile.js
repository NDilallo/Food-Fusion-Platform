import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
import GridItem from "../../components/Grid/GridItem.js";
import GridContainer from "../../components/Grid/GridContainer.js";
import CustomInput from "../../components/CustomInput/CustomInput.js";
import Button from "../../components/CustomButtons/Button.js";
import Card from "../../components/Card/Card.js";
import CardHeader from "../../components/Card/CardHeader.js";
import CardAvatar from "../../components/Card/CardAvatar.js";
import CardBody from "../../components/Card/CardBody.js";
import CardFooter from "../../components/Card/CardFooter.js";
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import avatar from "assets/img/faces/fritz.jpeg";
import axios from "axios";

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

export default function UserProfile() {
  const classes = useStyles();
  const [profile, setProfile] = useState({
    userID: null, // Ensure you have userID to correctly identify the profile
    firstName: "",
    lastName: "",
    city: "",
    aboutMe: "",
    emailAddress: ""
  });
  const [recipes, setRecipes] = useState([]);
  const [followers, setFollowers] = useState([]);
  const [tabIndex, setTabIndex] = useState(0);
  const [isNewProfile, setIsNewProfile] = useState(true); // Flag to check if the profile is new

  useEffect(() => {
    // Fetch profile data
    axios.get('http://localhost:8080/api/profile/1') // Assuming userID 1 for now
      .then(response => {
        if (response.data) {
          setProfile(response.data);
          setIsNewProfile(false);
        }
      })
      .catch(error => {
        console.error('There was an error fetching the profile!', error);
      });

    axios.get('http://localhost:8080/api/postedrecipe')
      .then(response => {
        setRecipes(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the recipes!', error);
      });

    axios.get('http://localhost:8080/api/followers')
      .then(response => {
        setFollowers(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the followers!', error);
      });
  }, []);

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setProfile(prevState => ({
      ...prevState,
      [id]: value
    }));
  };

  const handleCreateProfile = () => {
    axios.post('http://localhost:8080/api/profile', profile)
      .then(response => {
        setProfile(response.data);
        setIsNewProfile(false);
        alert('Profile created successfully');
      })
      .catch(error => {
        console.error('There was an error creating the profile!', error);
      });
  };

  const handleUpdateProfile = () => {
    axios.put(`http://localhost:8080/api/profile/${profile.userID}`, profile)
      .then(() => {
        alert('Profile updated successfully');
      })
      .catch(error => {
        console.error('There was an error updating the profile!', error);
      });
  };

  const handleTabChange = (event, newValue) => {
    setTabIndex(newValue);
  };

  return (
    <div>
      <Tabs value={tabIndex} onChange={handleTabChange}>
        <Tab label="Profile" />
        <Tab label="Recipes" />
        <Tab label="Followers" />
      </Tabs>

      {tabIndex === 0 && (
        <GridContainer>
          <GridItem xs={12} sm={12} md={8}>
            <Card>
              <CardHeader color="primary">
                <h4 className={classes.cardTitleWhite}>Edit Profile</h4>
                <p className={classes.cardCategoryWhite}>Complete your profile</p>
              </CardHeader>
              <CardBody>
                <GridContainer>
                  <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                      labelText="First Name"
                      id="firstName"
                      formControlProps={{
                        fullWidth: true,
                      }}
                      inputProps={{
                        value: profile.firstName,
                        onChange: handleInputChange,
                      }}
                    />
                  </GridItem>
                  <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                      labelText="Last Name"
                      id="lastName"
                      formControlProps={{
                        fullWidth: true,
                      }}
                      inputProps={{
                        value: profile.lastName,
                        onChange: handleInputChange,
                      }}
                    />
                  </GridItem>
                </GridContainer>
                <GridContainer>
                  <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                      labelText="City"
                      id="city"
                      formControlProps={{
                        fullWidth: true,
                      }}
                      inputProps={{
                        value: profile.city,
                        onChange: handleInputChange,
                      }}
                    />
                  </GridItem>
                  <GridItem xs={12} sm={12} md={6}>
                    <CustomInput
                      labelText="Email Address"
                      id="emailAddress"
                      formControlProps={{
                        fullWidth: true,
                      }}
                      inputProps={{
                        value: profile.emailAddress,
                        onChange: handleInputChange,
                      }}
                    />
                  </GridItem>
                </GridContainer>
                <GridContainer>
                  <GridItem xs={12} sm={12} md={12}>
                    <InputLabel style={{ color: "#AAAAAA" }}>About Me</InputLabel>
                    <CustomInput
                      labelText="About Me"
                      id="aboutMe"
                      formControlProps={{
                        fullWidth: true,
                      }}
                      inputProps={{
                        multiline: true,
                        rows: 5,
                        value: profile.aboutMe,
                        onChange: handleInputChange,
                      }}
                    />
                  </GridItem>
                </GridContainer>
              </CardBody>
              <CardFooter>
                {isNewProfile ? (
                  <Button color="primary" onClick={handleCreateProfile}>Create Profile</Button>
                ) : (
                  <Button color="primary" onClick={handleUpdateProfile}>Update Profile</Button>
                )}
              </CardFooter>
            </Card>
          </GridItem>
          <GridItem xs={12} sm={12} md={4}>
            <Card profile>
              <CardAvatar profile>
                <a href="#pablo" onClick={(e) => e.preventDefault()}>
                  <img src={avatar} alt="..." />
                </a>
              </CardAvatar>
              <CardBody profile>
                <h6 className={classes.cardCategory}>Profile</h6>
                <h4 className={classes.cardTitle}>{`${profile.firstName} ${profile.lastName}`}</h4>
                <p className={classes.description}>
                  {profile.aboutMe}
                </p>
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
                <h4 className={classes.cardTitleWhite}>Your Recipes</h4>
              </CardHeader>
              <CardBody>
                <GridContainer>
                  {recipes.map((recipe, index) => (
                    <GridItem key={index} xs={12} sm={6} md={4}>
                      <Card>
                        <CardBody>
                          <h4>{recipe.name}</h4>
                          <p><strong>Ingredients:</strong> {recipe.ingredients}</p>
                          <p><strong>Steps:</strong> {recipe.steps}</p>
                          <p><strong>Cuisine:</strong> {recipe.recipeCuisine}</p>
                          <p><strong>Rating:</strong> {recipe.avgRating}</p>
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
                <h4 className={classes.cardTitleWhite}>Followers</h4>
              </CardHeader>
              <CardBody>
                <GridContainer>
                  {followers.map((follower, index) => (
                    <GridItem key={index} xs={12} sm={6} md={4}>
                      <Card>
                        <CardBody>
                          <h4>Follower ID: {follower.followerID}</h4>
                          <p><strong>User ID:</strong> {follower.userID}</p>
                          <p><strong>Profile ID:</strong> {follower.profileID}</p>
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