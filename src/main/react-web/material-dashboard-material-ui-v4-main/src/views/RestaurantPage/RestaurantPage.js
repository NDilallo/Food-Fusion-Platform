
export default function RestaurantPage() {
  // Sample restaurant data
  const restaurant = {
    name: "Gourmet Paradise",
    address: "123 Culinary St, Flavor Town",
    cuisine: "Italian",
  };

  return (
    <div>
      <h1>Restaurant Information</h1>
      <p>Restaurant Name: {restaurant.name}</p>
      <p>Address: {restaurant.address}</p>
      <p>Cuisine: {restaurant.cuisine}</p>
    </div>
  );
}
