// Restaurant.jsx


const Restaurant = ({ name, address, cuisine }) => {
  return (
    <div>
      <p>Restaurant Name: {name}</p>
      <p>Address: {address}</p>
      <p>Cuisine: {cuisine}</p>
    </div>
  );
}

export default Restaurant;
