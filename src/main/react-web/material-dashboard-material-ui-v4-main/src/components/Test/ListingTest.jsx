import React from 'react';

const ListingTest = () => {
    const dummyData = [
        {
            "id": 1,
            "username": "n_dilallo",
            "pass": "abc123",
            "profile_link": {
                "userID": 1,
                "postedRecipesTableID": 0,
                "settingsTableID": 0,
                "followingTableID": 0,
                "draftsTableID": 0,
                "commentID": 0,
                "notificationID": 0
            }
        },
        {
            "id": 0,
            "username": "new_user",
            "pass": "user_password",
            "profile_link": {
                "userID": 2,
                "postedRecipesTableID": 1,
                "settingsTableID": 1,
                "followingTableID": 1,
                "draftsTableID": 1,
                "commentID": 1,
                "notificationID": 1
            }
        }
    ];

    return (
        <div>
            <h2>ListingTest</h2>
            <table>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Profile Link</th>
                    </tr>
                </thead>
                <tbody>
                    {dummyData.map((user, index) => (
                        <tr key={index}>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.pass}</td>
                            <td>
                                {/* Access specific properties of the profile_link object */}
                                userID: {user.profile_link.userID}, 
                                {/* Add other properties as needed */}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListingTest;