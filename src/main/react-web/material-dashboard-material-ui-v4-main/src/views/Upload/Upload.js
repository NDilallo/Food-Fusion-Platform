import React, { useState } from "react";
import { Box, TextField, MenuItem, Button } from "@material-ui/core";
import axios from "axios";
//import { ThemeProvider, CssBaseline } from '@material-ui/core/styles'; // Import createMuiTheme for older versions
const cuisines = [
   {
       value: 'American',
       label: 'American',
   },
   {
       value: 'Asian',
       label: 'Asian',
   },
   {
       value: 'Mexican',
       label: 'Mexican',
   },
   {
       value: 'Ethiopian',
       label: 'Ethiopian',
   },
   {
       value: 'Other',
       label: 'Other',
   },
];

export default function UserPage() {
   const [recipeName, setRecipeName] = useState('');
   const [ingredients, setIngredients] = useState('');
   const [description, setDescription] = useState('');
   const [cuisine, setCuisine] = useState('');
   const [draftNotes, setDraftNotes] = useState('');

   const handlePost = (e) => {
       e.preventDefault();
       const recipeData = {
           recipeName,
           ingredients,
           description,
           cuisine,
           draftNotes,
       };

       axios.post('http://localhost:8080/api/postedrecipe', recipeData)
           .then(response => {
               console.log("data submitted successfully", response.data);
           })
           .catch(error => {
               console.error('error submitting the form', error);
           });

        setRecipeName('');
        setIngredients('');
        setDescription('');
        setCuisine('');
        setDraftNotes('');
   };

   const handleDraft = (e) => {
       e.preventDefault();
       const recipeData = {
           recipeName,
           ingredients,
           description,
           cuisine,
           draftNotes,
       };

       axios.post('http://localhost:8080/api/draft', recipeData)
           .then(response => {
               console.log("data submitted successfully", response.data);
           })
           .catch(error => {
               console.error('error submitting the form', error);
           });

        setRecipeName('');
        setIngredients('');
        setDescription('');
        setCuisine('');
        setDraftNotes('');
   };

//    const darkTheme = createMuiTheme({
//     palette: {
//       type: 'dark',
//     },
//   });

   return (
    // <ThemeProvider theme={darkTheme}>
//<CssBaseline />
      <Box
        component="form"
        sx={{
          '& > :not(style)': { width: 500, maxWidth: '100%' },
        }}
        noValidate
        autoComplete="off"
      >
        <h1>Upload a new recipe</h1>
        <div>
          <TextField
            fullWidth
            id="outlined-basic"
            label="Recipe Name"
            variant="outlined"
            value={recipeName}
            onChange={(e) => setRecipeName(e.target.value)}
          />
        </div>
        <br />
        <div>
          <TextField
            fullWidth
            id="outlined-textarea"
            label="Ingredients"
            placeholder="Placeholder"
            multiline
            value={ingredients}
            onChange={(e) => setIngredients(e.target.value)}
          />
        </div>
        <br />
        <div>
          <TextField
            fullWidth
            id="outlined-textarea"
            label="Directions"
            placeholder="Placeholder"
            multiline
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </div>
        <br />
        <div>
          <TextField
            id="outlined-select-cuisine"
            select
            label="Select"
            helperText="Please select your cuisine"
            value={cuisine}
            onChange={(e) => setCuisine(e.target.value)}
          >
            {cuisines.map((option) => (
              <MenuItem key={option.value} value={option.value}>
                {option.label}
              </MenuItem>
            ))}
          </TextField>
        </div>
        <br />
        <div>
          <TextField
            fullWidth
            id="outlined-textarea"
            label="Draft Notes"
            placeholder="Placeholder"
            multiline
            value={draftNotes}
            onChange={(e) => setDraftNotes(e.target.value)}
          />
        </div>
        <br />
        <div>
          <Button variant="contained" onClick={handlePost}>
            Post
          </Button>
          <Button variant="contained" onClick={handleDraft}>
            Save as Draft
          </Button>
        </div>
      </Box>
    // </ThemeProvider>
   );
}
