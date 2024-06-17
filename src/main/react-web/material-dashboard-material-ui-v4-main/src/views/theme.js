import axios from 'axios';

// theme.js

// Function to fetch settings and return palette type
const getPaletteTypeFromSettings = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/settings');
        const settings = response.data[0];
        
        // Determine palette type based on settings (example logic)
        console.log('Settings:', settings); // Print the fetched settings
        let paletteType; settings;
    console.log('Settings:', settings.enableDarkMode); // Print the fetched settings
        if (settings.enableDarkMode) {
            paletteType = 'dark';
        } else {
            paletteType = 'light';
        }
        console.log('Palette Type:', paletteType); // Print the determined palette type
        
        return paletteType;
    } catch (error) {
        console.error('Error fetching settings:', error);
        // Fallback to a default type in case of error
        return 'dark'; // or 'light', as your default preference
    }
};

export default getPaletteTypeFromSettings;
