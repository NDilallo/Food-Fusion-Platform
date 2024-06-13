import React, { useState } from "react";
import { Box, TextField, Button, Typography } from "@material-ui/core";
import axios from "axios";
import PropTypes from 'prop-types';

export default function SearchPage({ onFollowUser }) {
  const [userSearch, setUserSearch] = useState('');
  const [searchResult, setSearchResult] = useState(null);
  const [searchError, setSearchError] = useState('');

  const handleSearch = (e) => {
    e.preventDefault();

    if (!userSearch) {
      setSearchError('Please enter a search term.');
      setSearchResult(null);
      return;
    }

    const searchData = {
      userSearch: userSearch,
    };

    axios.post('http://localhost:8080/api/searchHistory', searchData)
      .then(() => {
        // Now search for the user in the user table
        return axios.get(`http://localhost:8080/api/user/search?username=${userSearch}`);
      })
      .then(response => {
        if (response.data.length > 0) {
          setSearchResult(response.data[0]);
          setSearchError('');
        } else {
          setSearchResult(null);
          setSearchError('No user found with that username.');
        }
      })
      .catch(error => {
        console.error('Error searching for the user', error);
        setSearchResult(null);
        setSearchError('An error occurred while searching. Please try again.');
      });

    setUserSearch('');
  };

  const handleFollow = () => {
    if (searchResult) {
      const followData = {
        userID: 1, // Assuming current user ID is 1 for now
        profileID: searchResult.id, // Assuming the profile ID is the same as user ID
        username: searchResult.username // Pass the username
      };

      console.log('Follow Data:', followData);

      axios.post('http://localhost:8080/api/following', followData)
        .then(() => {
          onFollowUser(searchResult.username); // Call the function passed via props
          alert('You are now following this user.');
        })
        .catch(error => {
          console.error('Error following the user', error);
          alert('An error occurred while following. Please try again.');
        });
    }
  };

  return (
    <Box
      component="form"
      sx={{
        '& > :not(style)': { width: 500, maxWidth: '100%', },
      }}
      noValidate
      autoComplete="off"
    >
      <h1>Search for a User</h1>
      <div>
        <TextField
          fullWidth
          id="outlined-basic"
          label="Search"
          variant="outlined"
          value={userSearch}
          onChange={(e) => setUserSearch(e.target.value)}
        />
      </div>
      <br></br>
      <div>
        <Button variant="contained" onClick={handleSearch}>Search</Button>
      </div>
      <br></br>
      <Typography variant="h6">Search Results</Typography>
      {searchError && <Typography color="error">{searchError}</Typography>}
      {searchResult && (
        <div>
          <Typography>{searchResult.username}</Typography>
          <Button variant="contained" onClick={handleFollow}>Follow</Button>
        </div>
      )}
    </Box>
  );
}

SearchPage.propTypes = {
  onFollowUser: PropTypes.func.isRequired,
};
