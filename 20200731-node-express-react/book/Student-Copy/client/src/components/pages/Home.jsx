import React from 'react';
import { Link } from 'react-router-dom';

function Home({user}) {
  return (
    <header className="home-cta">
      <h1>GuessIT!</h1>

      {user ? (
        // <Link to="/books" className="btn btn-primary reddin">Books</Link>
        <Link to="/books/new" className="btn btn-primary reddin">Add Book</Link>
      ) : (
        <Link to="/register" className="btn btn-primary reddin">Register to Manager Book</Link>
      )}
    </header>
  );
};

export default Home;