import React, { useState, useEffect } from 'react';
import { settingsList } from 'services/SettingsService'; // Ensure this path matches your project structure


const ListingTest = () => {
    
    const [settings, setSettings] = useState([])

    useEffect(() => {
        settingsList().then((response) => {
            setSettings(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    return (
        <div>
            <h2>Settings List</h2>
            <table>
                <thead>
                    <tr>
                        <th>Settings ID</th>
                        <th>User ID</th>
                        <th>Enable Dark Mode</th>
                    </tr>
                </thead>
                <tbody>
                    {settings.map((setting, index) => (
                        <tr key={index}>
                            <td>{setting.settingsID}</td>
                            <td>{setting.userID}</td>
                            <td>{setting.enableDarkMode ? 'Yes' : 'No'}</td>

                        
                        </tr>
                        
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListingTest;