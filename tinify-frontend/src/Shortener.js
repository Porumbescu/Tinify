import React, { useState } from 'react';
import { Button, TextField, Container } from '@mui/material';
import { motion } from 'framer-motion';

const slideIn = {
    hidden: { x: '-100vw' },
    visible: { x: 0, transition: { duration: 0.5 } }
}

function Shortener({ onShorten }) {
    const [url, setUrl] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (onShorten) {
            onShorten(url);
        }
    };

    return (
        <motion.div variants={slideIn} initial="hidden" animate="visible">
            <form onSubmit={handleSubmit}>
                <Container maxWidth="sm">
                    <TextField
                        fullWidth
                        label="Enter your URL"
                        variant="outlined"
                        value={url}
                        InputLabelProps={{ shrink: true }}
                        onChange={e => setUrl(e.target.value)}
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        style={{
                            marginTop: '20px',
                            marginLeft: 'auto',
                            marginRight: 'auto',
                            display: 'block',
                            backgroundColor: '#ffffff',
                            color: '#4a3c4b',
                        }}
                        onClick={handleSubmit}
                    >
                        Shorten
                    </Button>
                </Container>
            </form>
        </motion.div>
    )
}

export default Shortener;