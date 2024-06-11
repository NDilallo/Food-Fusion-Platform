import React, {useState} from 'react';
import { useEffect } from 'react';
import { listUsers } from 'services/UserService';

const ListingTest = () => {

    const [users, setUsers] = useState([])

    useEffect(() => {

        listUsers().then((response) => {
            setUsers(response.data);
        }).catch(error => {
            console.error(error);
        })

    }, [])

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
                    {users.map((user, index) => (
                        <tr key={index}>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.pass}</td>
                            <td>
                            {user.profile_link ? (
                                    <>
                                        userID: {user.profile_link.userID}, 
                                        {/* Add other properties as needed */}
                                    </>
                                ) : (
                                    'No profile link available'
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListingTest;