import React from 'react';
import PropTypes from 'prop-types';
import 'assets/css/material-dashboard-react.css';

function PostCard({ username, text }) {
  return (
    <div className="post-container">
      <div className="post-header">
        <span className="username">{username}</span>
      </div>
      <div className="post-content">
        <p>{text}</p>
      </div>
    </div>
  );
}

PostCard.propTypes = {
  username: PropTypes.string.isRequired,
  text: PropTypes.string.isRequired,
};

export default PostCard;