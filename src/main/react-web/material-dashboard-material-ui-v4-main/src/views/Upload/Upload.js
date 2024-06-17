import React, { useState, useEffect } from "react";
import { Box, TextField, MenuItem, Button } from "@material-ui/core";
import axios from "axios";
import { createMuiTheme, ThemeProvider, CssBaseline} from '@material-ui/core';
import getPaletteTypeFromSettings from "views/theme.js";
const cuisines = [
  { value: "American", label: "American" },
  { value: "Asian", label: "Asian" },
  { value: "Mexican", label: "Mexican" },
  { value: "Ethiopian", label: "Ethiopian" },
  { value: "Other", label: "Other" },
];

export default function UserPage() {
  const [recipeName, setRecipeName] = useState("");
  const [ingredients, setIngredients] = useState("");
  const [description, setDescription] = useState("");
  const [cuisine, setCuisine] = useState("");
  const [draftNotes, setDraftNotes] = useState("");
  const [paletteType, setPaletteType] = useState('light'); // Default to 'light'

  useEffect(() => {
   const fetchPaletteType = async () => {
        const type = await getPaletteTypeFromSettings();
        console.log("Palette type fetched:", type); // Print the fetched palette type
        setPaletteType(type);
   };
   fetchPaletteType();
}, []);

const darkTheme = createMuiTheme({
   palette: {
        type: paletteType, // Use the state variable
   },
});
  const handlePost = (e) => {
    e.preventDefault();
    const recipeData = {
      recipeName: recipeName,
      ingredients: ingredients,
      description: description,
      cuisine: cuisine,
      draftNotes: draftNotes,
    };

    axios
      .post("http://localhost:8080/api/postedrecipe", recipeData)
      .then((response) => {
        console.log("data submitted successfully", response.data);
      })
      .catch((error) => {
        console.error("error submitting the form", error);
      });

    resetFormFields();
  };

  const handleDraft = (e) => {
    e.preventDefault();
    const recipeData = {
      recipeName: recipeName,
      ingredients: ingredients,
      description: description,
      cuisine: cuisine,
      draftNotes: draftNotes,
    };

    axios
      .post("http://localhost:8080/api/draft", recipeData)
      .then((response) => {
        console.log("data submitted successfully", response.data);
      })
      .catch((error) => {
        console.error("error submitting the form", error);
      });

    resetFormFields();
  };

  const resetFormFields = () => {
    setRecipeName("");
    setIngredients("");
    setDescription("");
    setCuisine("");
    setDraftNotes("");
  };

  return (
    <ThemeProvider theme={darkTheme}>
     <CssBaseline />
    <Box
      component="form"
      sx={{
        "& > :not(style)": { width: 500, maxWidth: "100%" },
      }}
      noValidate
      autoComplete="off"
      className="container mt-4"
    >
      <h1 className="mb-4">Upload a new recipe</h1>
      <div className="mb-3">
        <TextField
          fullWidth
          id="outlined-basic"
          label="Recipe Name"
          variant="outlined"
          value={recipeName}
          onChange={(e) => setRecipeName(e.target.value)}
        />
      </div>
      <br></br>
      <div className="mb-3">
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
      <br></br>
      <div className="mb-3">
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
      <br></br>
      <div className="mb-3">
        <TextField
          id="outlined-select-cuisine"
          select
          label="Select"
          helperText="Please select your cuisine"
          value={cuisine}
          onChange={(e) => setCuisine(e.target.value)}
          fullWidth
          variant="outlined"
        >
          {cuisines.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </div>
      <br></br>
      <div className="mb-3">
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
      <br></br>
      <div>
        <Button
          variant="contained"
          onClick={handlePost}
          className="me-2"
          color="primary"
        >
          Post
        </Button>
        <Button
          variant="contained"
          onClick={handleDraft}
          color="secondary"
        >
          Save as Draft
        </Button>
      </div>
    </Box>
    </ThemeProvider>
  );
}