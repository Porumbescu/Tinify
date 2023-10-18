import React from 'react';
import { motion } from 'framer-motion';
import { Button } from '@mui/material';

const fadeIn = {
    hidden: {opacity: 0},
    visible: {opacity: 1, transition: {duration: 1} }
  }

function Result({shortUrl}){

    const handleCopyToClipboard = () => {
        navigator.clipboard.writeText(shortUrl).then(() => {
          alert('Link copied to clipboard!');
        }).catch(err => {
          console.error('Could not copy text: ', err);
        });
      };

    return (
        <motion.div variants={fadeIn} initial="hidden" animate="visible" className="result-container">
          {shortUrl && (
                <>
                    <p>Shortened URL:</p>
                    <a className="result-link" href={shortUrl} target="_blank" rel="noopener noreferrer">
                        {shortUrl}
                    </a>
                    <Button 
                        variant="contained" 
                        color="primary" 
                        style={{ marginLeft: '10px' }}
                        onClick={handleCopyToClipboard}
                    >
                        Copy
                </Button>
                </>
           )}
        </motion.div>
    );
}

export default Result;
