import React from "react";
import './Input.scss';

function Input({ label, value, onChange, placeholder, type, name, id }) {
    return (
        <div className="Input">
            <label htmlFor={id}></label>
            <input 
                type={type}
                name={name} 
                id={id}
                value={value}
                onChange={onChange}
                placeholder={placeholder}
            />
        </div>
    );
}

export default Input;