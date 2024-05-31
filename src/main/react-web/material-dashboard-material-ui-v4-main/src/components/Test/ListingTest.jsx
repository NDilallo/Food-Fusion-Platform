import React, {useState} from 'react';
import { useEffect } from 'react';

const ListingTest = () => {

    const [users, setUsers] = useState([])

    useEffect(() => {
        
    })

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