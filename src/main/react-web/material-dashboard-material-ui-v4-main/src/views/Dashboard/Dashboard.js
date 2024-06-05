import React, { useState, useEffect } from "react";
import PostCard from "components/Dashboard/PostCard";
import "assets/css/material-dashboard-react.css";
import { listUsers } from "services/PostedRecipeService";

export default function Dashboard() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    listUsers()
      .then((response) => {
        setPosts(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <div className="dashboard-container">
      {posts.length > 0 ? (
        posts.map((post) => (
          <PostCard
            key={post.recipeId}
            username={post.name}
            text={post.ingredients}
          />
        ))
      ) : (
        <p>No posts available</p>
      )}
    </div>
  );
}
