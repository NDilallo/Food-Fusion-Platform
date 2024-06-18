import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
import { createMuiTheme, ThemeProvider, CssBaseline} from '@material-ui/core';
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
import getPaletteTypeFromSettings from "views/theme.js";
import 'bootstrap/dist/css/bootstrap.min.css';
import './UserProfile.css'; // Import the custom CSS file

export default function UserProfile() {
  const [profile, setProfile] = useState({
    userID: null, // Ensure you have userID to correctly identify the profile
    firstName: "",
    lastName: "",
    city: "",
    aboutMe: "",
    emailAddress: "",
  });
  const [recipes, setRecipes] = useState([]);
  const [drafts, setDrafts] = useState([]);
  const [following, setFollowing] = useState([]);
  const [tabIndex, setTabIndex] = useState(0);
  const [isNewProfile, setIsNewProfile] = useState(true); // Flag to check if the profile is new
  const [savedRecipes, setSavedRecipes] = useState([]);

  useEffect(() => {
    // Fetch profile data
    axios
      .get("http://localhost:8080/api/profile/1") // Assuming userID 1 for now
      .then((response) => {
        if (response.data) {
          setProfile(response.data);
          setIsNewProfile(false);
        }
      })
      .catch((error) => {
        console.error("There was an error fetching the profile!", error);
      });

    axios
      .get("http://localhost:8080/api/postedrecipe")
      .then((response) => {
        setRecipes(response.data);
      })
      .catch((error) => {
        console.error("There was an error fetching the recipes!", error);
      });

    axios
      .get("http://localhost:8080/api/following?userId=1") // Assuming userID 1 for now
      .then((response) => {
        setFollowing(response.data);
      })
      .catch((error) => {
        console.error("There was an error fetching the following list!", error);
      });

    axios
      .get("http://localhost:8080/api/draft")
      .then((response) => {
        setDrafts(response.data);
      })
      .catch((error) => {
        console.error("There was an error fetching the drafts!", error);
      });

    axios
      .get("http://localhost:8080/api/saved")
      .then((response) => {
        setSavedRecipes(response.data);
      })
      .catch((error) => {
        console.error("There was an error fetching the saved posts!", error);
      });
  }, []);

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setProfile((prevState) => ({
      ...prevState,
      [id]: value,
    }));
  };
  
  const [paletteType, setPaletteType] = useState('light'); // Default to 'light'
  useEffect(() => {
    const fetchPaletteType = async () => {
      const type = await getPaletteTypeFromSettings();
      console.log("Palette type fetched:", type); // Print the fetched palette type
      setPaletteType(type);
    };
    fetchPaletteType();
  }, []);

const darkTheme = createMuiTheme({
    palette: {
      type: paletteType, // Use the state variable
    },
});

  const handleCreateProfile = () => {
    axios
      .post("http://localhost:8080/api/profile", profile)
      .then((response) => {
        setProfile(response.data);
        setIsNewProfile(false);
        alert("Profile created successfully");
      })
      .catch((error) => {
        console.error("There was an error creating the profile!", error);
      });
  };

  const handleUpdateProfile = () => {
    axios
      .put(`http://localhost:8080/api/profile/${profile.userID}`, profile)
      .then(() => {
        alert("Profile updated successfully");
      })
      .catch((error) => {
        console.error("There was an error updating the profile!", error);
      });
  };

  const handleTabChange = (event, newValue) => {
    setTabIndex(newValue);
  };

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <div className="container mt-4">
        <ul className="nav nav-tabs">
          <li className="nav-item">
            <a className={`nav-link ${tabIndex === 0 ? 'active' : ''}`} onClick={() => handleTabChange(null, 0)}>Profile</a>
          </li>
          <li className="nav-item">
            <a className={`nav-link ${tabIndex === 1 ? 'active' : ''}`} onClick={() => handleTabChange(null, 1)}>Recipes</a>
          </li>
          <li className="nav-item">
            <a className={`nav-link ${tabIndex === 2 ? 'active' : ''}`} onClick={() => handleTabChange(null, 2)}>My Drafts</a>
          </li>
          <li className="nav-item">
            <a className={`nav-link ${tabIndex === 3 ? 'active' : ''}`} onClick={() => handleTabChange(null, 3)}>Following</a>
          </li>
          <li className="nav-item">
            <a className={`nav-link ${tabIndex === 4 ? 'active' : ''}`} onClick={() => handleTabChange(null, 4)}>Saved</a>
          </li>
        </ul>

        {tabIndex === 0 && (
          <div className="card mt-3">
            <div className="card-header bg-primary text-white">
              <h4>Edit Profile</h4>
              <p>Complete your profile</p>
            </div>
            <div className="card-body">
              <div className="row">
                <div className="col-md-6">
                  <div className="form-group">
                    <label>First Name</label>
                    <input
                      type="text"
                      className="form-control"
                      id="firstName"
                      value={profile.firstName}
                      onChange={handleInputChange}
                    />
                  </div>
                </div>
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Last Name</label>
                    <input
                      type="text"
                      className="form-control"
                      id="lastName"
                      value={profile.lastName}
                      onChange={handleInputChange}
                    />
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="col-md-6">
                  <div className="form-group">
                    <label>City</label>
                    <input
                      type="text"
                      className="form-control"
                      id="city"
                      value={profile.city}
                      onChange={handleInputChange}
                    />
                  </div>
                </div>
                <div className="col-md-6">
                  <div className="form-group">
                    <label>Email Address</label>
                    <input
                      type="email"
                      className="form-control"
                      id="emailAddress"
                      value={profile.emailAddress}
                      onChange={handleInputChange}
                    />
                  </div>
                </div>
              </div>
              <div className="form-group">
                <label>About Me</label>
                <textarea
                  className="form-control"
                  id="aboutMe"
                  rows="5"
                  value={profile.aboutMe}
                  onChange={handleInputChange}
                ></textarea>
              </div>
            </div>
            <div className="card-footer">
              {isNewProfile ? (
                <button className="btn btn-primary" onClick={handleCreateProfile}>Create Profile</button>
              ) : (
                <button className="btn btn-primary" onClick={handleUpdateProfile}>Update Profile</button>
              )}
            </div>
          </div>
        )}

        {tabIndex === 1 && (
          <div className="container mt-3">
            <div className="row">
              {recipes.map((recipe, index) => (
                <div className="col-md-4" key={index}>
                  <div className="card mb-3">
                    <div className="card-body">
                      <h4>{recipe.name}</h4>
                      <p><strong>Ingredients:</strong> {recipe.ingredients}</p>
                      <p><strong>Steps:</strong> {recipe.steps}</p>
                      <p><strong>Cuisine:</strong> {recipe.recipeCuisine}</p>
                      <p><strong>Rating:</strong> {recipe.avgRating}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        )}

      {tabIndex === 2 && (
        <GridContainer>
          <GridItem xs={12}>
            <Card>
              <CardHeader color="primary">
                <h4 className={classes.cardTitleWhite}>My Drafts</h4>
              </CardHeader>
              <CardBody>
                <GridContainer>
                  {drafts.map((draft, index) => (
                    <GridItem key={index} xs={12} sm={6} md={4}>
                      <Card>
                        <CardBody>
                          <h4>{draft.recipeName}</h4>
                          <p><strong>Ingredients:</strong> {draft.ingredients}</p>
                          <p><strong>Steps:</strong> {draft.description}</p>
                          <p><strong>Cuisine:</strong> {draft.cuisine}</p>
                          <p><strong>Notes:</strong> {draft.draftNotes}</p>
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

      {tabIndex === 3 && (
        <GridContainer>
          <GridItem xs={12}>
            <Card>
              <CardHeader color="primary">
                <h4 className={classes.cardTitleWhite}>Following</h4>
              </CardHeader>
              <CardBody>
                <GridContainer>
                  {following.map((follow, index) => (
                    <GridItem key={index} xs={12} sm={6} md={4}>
                      <Card>
                        <CardBody>
                          <h4>{follow.username}</h4>
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

{tabIndex === 4 && (
          <GridContainer>
            <GridItem xs={12}>
              <Card>
                <CardHeader color="primary">
                  <h4 className={classes.cardTitleWhite}>Your Saved Recipes</h4>
                </CardHeader>
                <CardBody>
                  <GridContainer>
                    {savedRecipes.map((savedPost, index) => (
                      savedPost.saved_post.map((post, postIndex) => (
                        <GridItem key={`${index}-${postIndex}`} xs={12} sm={6} md={4}>
                          <Card>
                            <CardBody>
                              <h4>{post.name}</h4>
                              <p><strong>Ingredients:</strong> {post.ingredients}</p>
                              <p><strong>Steps:</strong> {post.steps}</p>
                              <p><strong>Cuisine:</strong> {post.recipeCuisine}</p>
                              <p><strong>Rating:</strong> {post.avgRating}</p>
                            </CardBody>
                          </Card>
                        </GridItem>
                      ))
                    ))}
                  </GridContainer>
                </CardBody>
              </Card>
            </GridItem>
          </GridContainer>
        )}
    </div>
    </ThemeProvider>
  );
}
