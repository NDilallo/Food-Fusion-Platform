import React, { useState, useEffect } from "react";
import "assets/css/material-dashboard-react.css";
import { listUsers, addRating } from "services/PostedRecipeService";

import GridItem from "../../components/Grid/GridItem.js";
import GridContainer from "../../components/Grid/GridContainer.js";
import Card from "../../components/Card/Card.js";
import CardHeader from "../../components/Card/CardHeader.js";
import CardBody from "../../components/Card/CardBody.js";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import IconButton from "@material-ui/core/IconButton";
import BookmarkIcon from "@material-ui/icons/Bookmark";
import SavedService from "services/SavedService";

export default function Dashboard() {
  const [posts, setPosts] = useState([]);
  const [ratingInputs, setRatingInputs] = useState({});

  useEffect(() => {
    listUsers()
      .then((response) => {
        const sortedPosts = response.data.sort((a, b) => b.recipeId - a.recipeId);
        setPosts(sortedPosts);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const handleInputChange = (event, recipeId) => {
    const value = event.target.value;
    setRatingInputs((prevInputs) => ({
      ...prevInputs,
      [recipeId]: value,
    }));
  };

  const handleSubmitRating = (recipeId) => {
    const rating = ratingInputs[recipeId];
    if (rating >= 1 && rating <= 5) {
      addRating(recipeId, Math.floor(rating))
        .then((updatedRecipe) => {
          setPosts((prevPosts) =>
            prevPosts.map((post) =>
              post.recipeId === recipeId
                ? { ...post, avgRating: updatedRecipe.avgRating }
                : post
            )
          );
          setRatingInputs((prevInputs) => ({
            ...prevInputs,
            [recipeId]: "", // Clear the rating input after submission
          }));
        })
        .catch((error) => {
          console.error(error);
        });
    } else {
      alert("Please select a rating between 1 and 5.");
    }
  };

  const handleSavePost = (post) => {
    const savedPost = {
      user_comment: null,
      saved_post: [
        {
          ratings: [
            {
              rating: post.ratings.rating
            }
          ],
          name: post.name,
          ingredients: post.ingredients,
          steps: post.steps,
          recipeCuisine: post.recipeCuisine,
          avgRating: post.avgRating
        }
      ]
    };
  
    SavedService.savePost(savedPost)
      .then((response) => {
        console.log('Save post response:', response); // Log the response for debugging
  
        // Handle successful response as needed
      })
      .catch((error) => {
        console.error('There was an error saving the post!', error);
      });
  };

  return (
    <GridContainer>
      <GridItem xs={12}>
        <Card>
          <CardHeader color="primary">
            <h4>Your Feed</h4>
          </CardHeader>
          <CardBody>
            <GridContainer>
              {posts.map((recipe, index) => (
                <GridItem key={index} xs={12} sm={6} md={4}>
                  <Card>
                    <CardHeader
                      color="secondary"
                      style={{ display: "flex", justifyContent: "space-between" }}
                    >
                      <h4>{recipe.name}</h4>
                      <IconButton
                        color="primary"
                        onClick={() => handleSavePost(recipe)}
                      >
                        <BookmarkIcon />
                      </IconButton>
                    </CardHeader>
                    <CardBody>
                      <p><strong>Ingredients:</strong> {recipe.ingredients}</p>
                      <p><strong>Steps:</strong> {recipe.steps}</p>
                      <p><strong>Cuisine:</strong> {recipe.recipeCuisine}</p>
                      <p><strong>Rating:</strong> {recipe.avgRating}</p>
                      <Grid container spacing={2} alignItems="center">
                        <Grid item xs={6}>
                          <FormControl fullWidth>
                            <InputLabel id={`rating-label-${recipe.recipeId}`}>Select Rating</InputLabel>
                            <Select
                              labelId={`rating-label-${recipe.recipeId}`}
                              id={`rating-select-${recipe.recipeId}`}
                              value={ratingInputs[recipe.recipeId] || ""}
                              onChange={(event) => handleInputChange(event, recipe.recipeId)}
                              fullWidth
                            >
                              <MenuItem value={1}>1</MenuItem>
                              <MenuItem value={2}>2</MenuItem>
                              <MenuItem value={3}>3</MenuItem>
                              <MenuItem value={4}>4</MenuItem>
                              <MenuItem value={5}>5</MenuItem>
                            </Select>
                          </FormControl>
                        </Grid>
                        <Grid item xs={6}>
                          <Button
                            variant="contained"
                            color="primary"
                            onClick={() => handleSubmitRating(recipe.recipeId)}
                          >
                            Submit Rating
                          </Button>
                        </Grid>
                      </Grid>
                    </CardBody>
                  </Card>
                </GridItem>
              ))}
            </GridContainer>
          </CardBody>
        </Card>
      </GridItem>
    </GridContainer>
  );
}
