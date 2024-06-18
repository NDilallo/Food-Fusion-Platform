import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/postedrecipe';

export const listUsers = () => axios.get(REST_API_BASE_URL);

export const addRating = (recipeId, rating) => {
    const url = `${REST_API_BASE_URL}/${recipeId}/rating`;
    // const send_data = {
    //     "recipeId": 0,
    //     "ratings": [
    //       {
    //         "ratingId": 0,
    //         "rating": 5
    //       }
    //     ],
    //     "name": "string",
    //     "ingredients": "string",
    //     "steps": "string",
    //     "recipeCuisine": "string",
    //     "avgRating": 5
    //   }
    return axios.post(url, { rating: rating }); // Ensure rating is passed in the request body
    // console.log(rating);
    // console.log(url);
    // return axios.post(url, send_data);
  };
// export const addRating = (recipeId, rating) => {console.log("rating type: ", typeof(parseInt(rating))); return axios.post(`${REST_API_BASE_URL}/${recipeId}/rating`, {rating: rating});}