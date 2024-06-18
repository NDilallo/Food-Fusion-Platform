import React, { useState, useEffect } from "react";
import axios from "axios";
import getPaletteTypeFromSettings from "views/theme.js";
import { createMuiTheme, ThemeProvider, CssBaseline} from '@material-ui/core';
import 

{
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
    Button,
} from '@material-ui/core';

export default function Settings() {
    const [paletteType, setPaletteType] = useState('light'); // Default to 'light'
    const [settings, setSettings] = useState([]);

    useEffect(() => {
        const fetchPaletteType = async () => {
            const type = await getPaletteTypeFromSettings();
            console.log("Palette type fetched:", type); // Print the fetched palette type
            setPaletteType(type);
        };
        fetchPaletteType();
    }, []);

    useEffect(() => {
        axios.get('http://localhost:8080/api/settings')
            .then(response => {
                const fetchedSettings = response.data;
                setSettings(fetchedSettings);
            })
            .catch(error => {
                console.log('Error fetching settings:', error);
            });
    }, []);

    const darkTheme = createMuiTheme({
        palette: {
            type: paletteType, // Use the state variable
        },
    });

    const handleDarkModeChange = (settingsID, enableDarkMode) => {
        axios.put(`http://localhost:8080/api/settings/${1}`, { enableDarkMode })
            .then(response => {
                console.log('Settings updated', response.data);

                setSettings(currentSettings => currentSettings.map(setting => {
                    if (setting.settingsID === settingsID) {
                        return { ...setting, enableDarkMode };
                    }
                    return setting;
                }));

                window.location.reload(); // Refresh the page
            })
            .catch(error => {
                console.error('Error updating settings:', error);
            });
    };

    return (
        <ThemeProvider theme={darkTheme}>
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
                <Button variant="contained" color="primary" onClick={() => window.location.reload()}>Refresh</Button> {/* Add a refresh button */}
            </Container>
        </ThemeProvider>
    );
}
