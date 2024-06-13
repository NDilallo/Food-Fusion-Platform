import React, { useState } from "react";
import { Box, TextField, Button } from "@material-ui/core";
import axios from "axios";

export default function SearchPage() {
    const [userSearch, setUserSearch] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [error, setError] = useState(null);

    const handleSearch = async (e) => {
        e.preventDefault();
        const searchData = {
            userSearch: userSearch,
        };

        try {
            // Save search history
            await axios.post('http://localhost:8080/api/searchHistory', searchData);

            // Search for users
            const response = await axios.get(`http://localhost:8080/api/user/search?username=${userSearch}`);
            setSearchResults(response.data);
            setError(null); // Clear any previous errors
        } catch (error) {
            setError('There was an error searching for users');
            console.error('Error searching for users:', error);
        }

        setUserSearch('');
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
          {error && <div style={{ color: 'red' }}>{error}</div>}
          <div>
              <h2>Search Results</h2>
              {searchResults.length > 0 ? (
                  searchResults.map((user, index) => (
                      <p key={index}>{user.username} - {user.profile_link?.firstName} {user.profile_link?.lastName}</p>
                  ))
              ) : (
                  <p>No users found</p>
              )}
          </div>
        </Box>
      );
}
