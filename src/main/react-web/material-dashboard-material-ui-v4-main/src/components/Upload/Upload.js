import React, { useState } from "react";
import { Button } from "@material-ui/core";
import axios from "axios";

const Upload = () => {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = () => {
    if (!selectedFile) {
      alert("Please select a file first!");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);

    // Assuming the endpoint to upload files is /api/upload
    axios.post("http://localhost:8080/api/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
      .then(() => {
        alert("File uploaded successfully!");
      })
      .catch((error) => {
        console.error("There was an error uploading the file!", error);
        alert("File upload failed!");
      });
  };

  return (
    <div>
      <input type="file" onChange={handleFileChange} />
      <Button onClick={handleUpload} color="primary">Upload</Button>
    </div>
  );
};

export default Upload;
