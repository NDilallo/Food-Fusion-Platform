import React, { useState } from "react";
import { Box, TextField, Button } from "@material-ui/core";
import axios from "axios";


export default function SearchPage() {
    const [userSearch, setUserSearch] = useState('');

    const handleSearch = (e) => {
        e.preventDefault();
        const searchData = {
            userSearch: userSearch,
        };

        axios.post('http://localhost:8080/api/searchHistory', searchData)
           .then(response => {
               console.log("data submitted successfully", response.data);
           })
           .catch(error => {
               console.error('error submitting the form', error);
           });

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
        </Box>
      );
   
}