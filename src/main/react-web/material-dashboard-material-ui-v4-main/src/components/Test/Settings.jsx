import React, { useState } from 'react';
import axios from 'axios';

const Settings = () => {
    const [darkMode, setDarkMode] = useState(false);
    const [setting1, setSetting1] = useState(false);
    const [setting2, setSetting2] = useState(false);

    const handleDarkModeChange = async () => {
        try {
            const response = await axios.put('/api/settings', { darkMode: !darkMode });
            if (response.status === 200) {
                setDarkMode(!darkMode);
            }
        } catch (error) {
            console.error('Error updating dark mode setting:', error);
        }
    };

    const handleSetting1Change = async () => {
        try {
            const response = await axios.put('/api/settings', { setting1: !setting1 });
            if (response.status === 200) {
                setSetting1(!setting1);
            }
        } catch (error) {
            console.error('Error updating setting 1:', error);
        }
    };

    const handleSetting2Change = async () => {
        try {
            const response = await axios.put('/api/settings', { setting2: !setting2 });
            if (response.status === 200) {
                setSetting2(!setting2);
            }
        } catch (error) {
            console.error('Error updating setting 2:', error);
        }
    };

    return (
        <div style={{ padding: '20px' }}>
            <h1>Settings</h1>
            <div>
                <label htmlFor="darkMode">Dark Mode</label>
                <input
                    type="checkbox"
                    id="darkMode"
                    checked={darkMode}
                    onChange={handleDarkModeChange}
                />
            </div>

            <div>
                <label htmlFor="setting1">Setting 1</label>
                <input
                    type="checkbox"
                    id="setting1"
                    checked={setting1}
                    onChange={handleSetting1Change}
                />
            </div>

            <div>
                <label htmlFor="setting2">Setting 2</label>
                <input
                    type="checkbox"
                    id="setting2"
                    checked={setting2}
                    onChange={handleSetting2Change}
                />
            </div>
        </div>
    );
};

export default Settings;
