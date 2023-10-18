import React, { useState } from 'react';
import './App.css';
import Shortener from './Shortener';
import Result from './Result';
import { motion } from 'framer-motion';

function App() {
  const [shortUrl, setShortUrl] = useState('');

  const handleShorten = async (url) => {
    const response = await fetch('http://localhost:8080/api/tinify/shorten', {
      method: 'POST',
      headers: {
        'Content-Type': 'text/plain',
      },
      body: url,
    });

    if (response.ok) {
      const result = await response.text();
      setShortUrl(result);
    } else {
      console.error('Failed to shorten the URL');
    }
  }

  const fadeIn = {
    hidden: { opacity: 0 },
    visible: { opacity: 1, transition: { duration: 1 } }
  }

  const slideIn = {
    hidden: { x: '-100vw' },
    visible: { x: 0, transition: { duration: 0.5 } }
  }

  return (
    <div className="App">

      <div class="header">
        <h1>TINIFY</h1>
        <h2>-URL SHORTENER-</h2>
      </div>

      <div class="dark-section">
        <motion.div variants={slideIn} initial="hidden" animate="visible">
          <Shortener onShorten={handleShorten} />
        </motion.div>
        {shortUrl && (
          <motion.div variants={fadeIn} initial="hidden" animate="visible">
            <Result shortUrl={shortUrl} />
          </motion.div>
        )}
      </div>

      <div className="footer">
        <p>Made with <span>Spring & React</span></p>
      </div>
    </div>
  );

}

export default App;
