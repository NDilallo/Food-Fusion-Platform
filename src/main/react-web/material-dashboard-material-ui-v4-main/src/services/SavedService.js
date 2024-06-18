import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8080/api/saved';

const savePost = (savedPost) => {
    return axios.post(REST_API_BASE_URL, savedPost);
};

const getSavedPosts = () => {return axios.get(REST_API_BASE_URL);}

export default {
    savePost,
    getSavedPosts,
};