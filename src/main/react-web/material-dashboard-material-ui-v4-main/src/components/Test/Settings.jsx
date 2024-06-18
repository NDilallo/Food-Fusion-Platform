import React, { useState, useEffect } from 'react';
import { settingsList, updateSetting } from 'services/SettingsService'; // Ensure this path matches your project structure

const ListingTest = () => {
    const [settings, setSettings] = useState([]);

    useEffect(() => {
        settingsList().then((response) => {
            setSettings(response.data);
        }).catch(error => {
            console.error(error);
        });
    }, []);

    const handleDarkModeChange = async (settingID, enableDarkMode) => {
        try {
            // Update the setting in the backend
            await updateSetting(settingID, { enableDarkMode });

            // Update the local state
            setSettings(prevSettings => prevSettings.map(setting => {
                if (setting.settingsID === settingID) {
                    return { ...setting, enableDarkMode };
                }
                return setting;
            }));
        } catch (error) {
            console.error(error);
        }
    };

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
                            <td>
                                <input
                                    type="checkbox"
                                    checked={setting.enableDarkMode}
                                    onChange={() => handleDarkModeChange(setting.settingsID, !setting.enableDarkMode)}
                                />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListingTest;