import logo from './logo.svg';
import React from 'react';
import { Route, BrowserRouter, Routes } from 'react-router-dom';
import './App.css';
import { Home, HomeView } from './views/home-view/home-view';
import { User, UserView } from './views/user/user-view';
import { CreateUserView } from './views/createUser-view/createUser-view';

function App() {
  return (
    <React.StrictMode>
      <BrowserRouter>
        <Routes>
          <Route path="/">
            <Route index element={<HomeView />}></Route>
            <Route path="user/:id" element={<UserView />} ></Route>
            <Route path='register' element={<CreateUserView />}></Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </React.StrictMode>
  );
}

export default App;
