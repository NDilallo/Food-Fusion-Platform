import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/postedrecipe';

export const listUsers = () => axios.get(REST_API_BASE_URL);

export const addRating = (recipeId, rating) => {
    const url = `${REST_API_BASE_URL}/${recipeId}/rating`;
    return axios.post(url, { rating: rating });
  };
