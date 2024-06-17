import React, { useState, useEffect } from "react";
import axios from "axios";
import {
    Container,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    Checkbox,
    CssBaseline
} from '@material-ui/core';
import { createMuiTheme, ThemeProvider as MuiThemeProvider } from '@material-ui/core/styles';

const createTheme = (paletteType) => createMuiTheme({
    palette: {
        type: paletteType,
        // other theme settings
    },
});

export default function Test() {
    const [settings, setSettings] = useState([]);
    const [dynamicTheme, setDynamicTheme] = useState(createTheme('dark'));

    useEffect(() => {
        axios.get('http://localhost:8080/api/settings')
            .then(response => {
                const fetchedSettings = response.data;
                setSettings(fetchedSettings);

                if (fetchedSettings.enableDarkMode) {
                    setDynamicTheme(createTheme('dark'));
                    localStorage.setItem('themePreference', 'dark');
                } else {
                    setDynamicTheme(createTheme('light'));
                    localStorage.setItem('themePreference', 'light');
                }
            })
            .catch(error => {
                console.log('Error fetching settings:', error);
            });
    }, []);

    const handleDarkModeChange = (settingsID, enableDarkMode) => {
        axios.put(`http://localhost:8080/api/settings/${settingsID}`, { enableDarkMode })
            .then(response => {
                console.log('Settings updated', response.data);

                setSettings(currentSettings => currentSettings.map(setting => {
                    if (setting.settingsID === settingsID) {
                        return { ...setting, enableDarkMode };
                    }
                    return setting;
                }));

                if (enableDarkMode) {
                    setDynamicTheme(createTheme('dark'));
                    localStorage.setItem('themePreference', 'dark');
                } else {
                    setDynamicTheme(createTheme('light'));
                    localStorage.setItem('themePreference', 'light');
                }
            })
            .catch(error => {
                console.error('Error updating settings:', error);
            });
    };

    return (
        <MuiThemeProvider theme={dynamicTheme}>
            <CssBaseline />
            <Container>
                <Typography variant="h4" component="h2" gutterBottom>
                    Enable Dark Mode
                </Typography>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Enable Dark Mode</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {settings.map((setting, index) => (
                                <TableRow key={index}>
                                    <TableCell>
                                        <Checkbox
                                            checked={setting.enableDarkMode}
                                            onChange={() => handleDarkModeChange(setting.settingsID, !setting.enableDarkMode)}
                                            color="primary"
                                        />
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Container>
        </MuiThemeProvider>
    );
}
