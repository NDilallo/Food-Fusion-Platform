import React from "react";
import { Box, TextField } from "@material-ui/core";

const cuisines = [
    {
      label: 'American',
    },
    {
      label: 'Asian',
    },
    {
      label: 'Mexican',
    },
    {
      label: 'Ethiopian',
    },
    {
        label: 'Other',
      },
  ];

export default function UserPage() {
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
            <TextField fullWidth id="outlined-basic" label="Recipe Name" variant="outlined" />
        </div>
        <div>
            <TextField
            fullWidth 
            id="outlined-textarea"
            label="Ingredients"
            placeholder="Placeholder"
            multiline
            />
        </div>
        <div>
            <TextField
            fullWidth 
            id="outlined-textarea"
            label="Directions"
            placeholder="Placeholder"
            multiline
            />
        </div>
      </Box>
    );
  }