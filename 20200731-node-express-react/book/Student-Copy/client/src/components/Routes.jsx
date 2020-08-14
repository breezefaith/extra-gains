import React from "react";
import { Route, Switch } from "react-router-dom";

import Home from './pages/Home';
import About from './pages/About';
import Login from './sessions/Login';
import Logout from './sessions/Logout';

import Books from './books/Index';
import NewBook from './books/New';
import EditBook from './books/Edit';

import Register from './users/Register';

function Routes({ user, setUser }) {
  return (
    <Switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/about" component={About} />
      <Route exact path="/login" render={
        renderProps => <Login
          {...renderProps}
          setUser={setUser}
        />
      } />
      {<Route exact path="/logout" render={
        renderProps => <Logout
          {...renderProps}
          setUser={setUser}
        />
      } />}
      {<Route exact path="/register" render={
        renderProps => <Register
          {...renderProps}
          setUser={setUser}
        />
      } />}

      {<Route exact path="/books" render={
        renderProps => <Books
          {...renderProps}
          user={user}
        />
      } />}
      <Route exact path="/books/new" render={
        renderProps => <NewBook
          {...renderProps}
          user={user}
        />
      } />
      <Route exact path="/books/edit" render={
        renderProps => <EditBook
          {...renderProps}
          user={user}
        />
      }/>
    </Switch>
  );
}

export default Routes;