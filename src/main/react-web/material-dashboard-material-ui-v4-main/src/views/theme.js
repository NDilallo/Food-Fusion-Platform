// theme.js
import { createMuiTheme } from '@material-ui/core/styles';
import axios from 'axios';

// Function to fetch settings and create theme
const createThemeFromSettings = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/settings');
        const settings = response.data;
        
        // Determine palette type based on settings (example logic)
        const paletteType = settings.enableDarkMode ? 'dark' : 'light';

        // Create and return the theme
        return createMuiTheme({
            palette: {
                type: paletteType,
                // other theme settings
            },
        });
    } catch (error) {
        console.error('Error fetching settings:', error);
        // Fallback to a default theme in case of error
        return createMuiTheme({
            palette: {
                type: 'dark', // or any default type you prefer
            },
        });
    }
};

export default createThemeFromSettings;
