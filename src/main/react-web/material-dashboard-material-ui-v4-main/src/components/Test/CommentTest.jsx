import React, { useState, useEffect } from 'react';

import {listComments} from 'services/CommentService';



const CommentsListing = () => {
    const [comments, setComments] = useState([]);

    useEffect(() => {
        listComments()
            .then((data) => {
                setComments(data);
            })
            .catch((error) => {
                console.error("Error fetching comments:", error);
            });
    }, []);

    return (
        <div>
            <h2>Comments Listing</h2>
            <table>
                <thead>
                    <tr>
                        <th>Comment ID</th>
                        <th>Post ID</th>
                        <th>User ID</th>
                        <th>Content</th>
                        <th>Date Created</th>
                    </tr>
                </thead>
                <tbody>
                    {comments.map((comment, index) => (
                        <tr key={index}>
                            <td>{comment.id}</td>
                            <td>{comment.postId}</td>
                            <td>{comment.userId}</td>
                            <td>{comment.content}</td>
                            <td>{comment.dateCreated}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default CommentsListing;
