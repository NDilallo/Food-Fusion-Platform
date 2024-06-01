// services/CommentService.js
import axios from 'axios';

export const listComments = async () => {
    try {
        const response = await axios.get('/api/comment');
        return response.data;
    } catch (error) {
        console.error("Error fetching comments:", error);
        throw error;
    }
};