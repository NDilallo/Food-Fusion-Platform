import React, { useState } from "react";
import { createPostedRecipe } from "services/UploadService"; 

const UploadComponent = () => {

    const [recipeName, setRecipeName] = useState('');
    const [ingredients, setIngredients] = useState('');
    const [directions, setDirections] = useState('');
    const [cuisine, setCuisine] = useState('');

   // const navigator = useNavigate();

    const handleRecipeName = (e) => setRecipeName(e.target.value);

    const handleIngredients = (e) => setIngredients(e.target.value);

    const handleDirections = (e) => setDirections(e.target.value);

    const handleCuisine = (e) => setCuisine(e.target.value);

    function savePostedRecipe(e) {
        e.preventDefault();

        const recipe = {recipeName, ingredients, directions, cuisine};
        console.log(recipe);

        createPostedRecipe(recipe).then((response) =>{
            console.log(response.data);
           // navigator('/user'); // the url in the route to the profile page
        })
    }

    return (
        <div className='container'>
            <br></br> 
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <h2 className="text-center">Create Recipe</h2>
                    <div className="card-body">
                        <form>
                            <div className="form-group mb-2">
                                <label className="form-label">Recipe Name:</label>
                                <input
                                    type="text"
                                    placeholder="Enter Recipe Name"
                                    name="recipeName"
                                    value={recipeName}
                                    form="form-control"
                                    onChange={handleRecipeName}>
                                </input>
                            </div>
                            <br></br> 
                            <div className="form-group mb-2">
                                <label className="form-label">Ingredients:</label>
                                <input
                                    type="text"
                                    placeholder="Enter Ingredients"
                                    name="ingredients"
                                    value={ingredients}
                                    form="form-control"
                                    onChange={handleIngredients}>
                                </input>
                            </div>
                            <br></br> 
                            <div className="form-group mb-2">
                                <label className="form-label">Directions:</label>
                                <input
                                    type="text"
                                    placeholder="Enter Directions"
                                    name="directions"
                                    value={directions}
                                    form="form-control"
                                    onChange={handleDirections}>
                                </input>
                            </div>
                            <br></br>
                            <div className="form-group mb-2">
                                <label className="form-label">Cuisine:</label>
                                <input
                                    type="text"
                                    placeholder="Enter Cuisine"
                                    name="cuisine"
                                    value={cuisine}
                                    form="form-control"
                                    onChange={handleCuisine}>
                                </input>
                            </div>
                            <br></br>
                            <button className="btn btn-success" onClick={savePostedRecipe}>Post</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UploadComponent;