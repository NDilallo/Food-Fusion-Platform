import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { listUsers, addRating } from "services/PostedRecipeService";
import Card from "react-bootstrap/esm/Card";
import Button from "react-bootstrap/esm/Button";
import Form from "react-bootstrap/esm/Form";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
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
        console.log('Save post response:', response);
      })
      .catch((error) => {
        console.error('There was an error saving the post!', error);
      });
  };

  return (
    <div className="container mt-4">
      <h4 className="mb-4">Your Feed</h4>
      <div className="row">
        {posts.map((recipe, index) => (
          <div key={index} className="col-sm-6 col-md-4 mb-4">
            <Card>
              <Card.Body>
                <h4>{recipe.name}</h4>
                <p><strong>Ingredients:</strong> {recipe.ingredients}</p>
                <p><strong>Steps:</strong> {recipe.steps}</p>
                <p><strong>Cuisine:</strong> {recipe.recipeCuisine}</p>
                <p><strong>Rating:</strong> {recipe.avgRating}</p>
                <Form.Group as={Row}>
                  <Form.Label column sm="6">Select Rating</Form.Label>
                  <Col sm="6">
                    <Form.Control
                      as="select"
                      value={ratingInputs[recipe.recipeId] || ""}
                      onChange={(event) => handleInputChange(event, recipe.recipeId)}
                    >
                      <option value={1}>1</option>
                      <option value={2}>2</option>
                      <option value={3}>3</option>
                      <option value={4}>4</option>
                      <option value={5}>5</option>
                    </Form.Control>
                  </Col>
                </Form.Group>
                <Button
                  variant="primary"
                  onClick={() => handleSubmitRating(recipe.recipeId)}
                >
                  Submit Rating
                </Button>
                <Button
                  variant="secondary"
                  className="ml-2"
                  onClick={() => handleSavePost(recipe)}
                >
                  Save Post
                </Button>
              </Card.Body>
            </Card>
          </div>
        ))}
      </div>
    </div>
  );
}
