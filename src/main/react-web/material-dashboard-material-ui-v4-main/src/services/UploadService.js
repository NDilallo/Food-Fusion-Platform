import axios from "axios";

const REST_API_BASE_URL = 'http://localhost/api/postedrecipe';

export const createPostedRecipe = (postedRecipe) => axios.post(REST_API_BASE_URL, postedRecipe);