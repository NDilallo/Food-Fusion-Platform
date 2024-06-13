// RestaurantPage.jsx

import Restaurant from "./Restaurant";

const RestaurantPage = () => {
  // Sample restaurant data
  const restaurant = {
    name: "Gourmet Paradise",
    address: "123 Culinary St, Flavor Town",
    cuisine: "Italian",
  };

  return (
    <div>
      <h1>Restaurant Information</h1>
      <Restaurant {...restaurant} />
    </div>
  );
}

export default RestaurantPage;
