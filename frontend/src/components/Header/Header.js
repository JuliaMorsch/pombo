import React from 'react';
import './Header.css';
import logo from '../../assets/images/logo-pombo.svg';

const Header = () => {
    return (
        <header className='header'>
                <img src={logo} alt='logo' className='logo' />
                <p className='brand'>
                    PomboApplication </p>
                <ul className='navList'>
                    <li className='navItem'>Home</li>
                    <li className='navItem'>Sobre</li>
                    <li className='navItem'>Contato</li>
                </ul>
        </header>
    )
}

export default Header;