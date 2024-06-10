import React, { useState } from "react";
import { Box, TextField, MenuItem, Button } from "@material-ui/core";
import axios from "axios";


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


   //
   const handlePost = (e) => {
       e.preventDefault();
       const recipeData = {
           recipeName: recipeName,
           ingredients: ingredients,
           description: description,
           cuisine: cuisine,
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
   };


   const handleDraft = (e) => {
       e.preventDefault();
       const recipeData = {
           recipeName: recipeName,
           ingredients: ingredients,
           description: description,
           cuisine: cuisine,
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
       <p>Current recipe name: {recipeName}</p>
       <p>Current ingredients: {ingredients}</p>
       <p>Current descrip: {description}</p>
       <p>Current cuisine: {cuisine}</p>
       <div>
           <Button variant="contained" onClick={handlePost}>Post</Button>
           <Button variant="contained" onClick={handleDraft}>Save as Draft</Button>
       </div>
     </Box>
   );
 }
