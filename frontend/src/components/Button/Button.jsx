import React from "react";
import { Link } from "react-router-dom";
import './Button.scss';

function Button({ label, to, type, name, id, onClick }) {
    return to ? (
        <Link to={to} className="cta-button" id={id} name={name}>
            {label}
        </Link>
    ) : (
        <button
            type={type}
            name={name}
            id={id}
            onClick={onClick}
            className="cta-button"
        >
            {label}
        </button>
    );
}

export default Button;