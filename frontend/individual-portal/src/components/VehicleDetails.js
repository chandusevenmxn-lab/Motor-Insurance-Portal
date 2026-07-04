import React, { useState } from 'react';
import axios from 'axios';

const VehicleDetails = ({ onNext }) => {
    const [formData, setFormData] = useState({
        registrationNumber: '',
        make: '',
        model: '',
        manufacturingYear: '',
        vehicleValue: ''
    });

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // JNY-QT-001 Step 1: POST to Motor Experience API
            const response = await axios.post('http://api.zurich.com/motor/v1/quotes', formData);
            console.log('Quote Created:', response.data);
            onNext(response.data.id);
        } catch (error) {
            console.error('Submission failed', error);
        }
    };

    return (
        <div className="zurich-container">
            <h2>MOTOR-QT-001: Vehicle Details</h2>
            <form onSubmit={handleSubmit} className="zurich-form">
                <input
                    type="text"
                    placeholder="Registration Number"
                    onChange={(e) => setFormData({...formData, registrationNumber: e.target.value})}
                />
                <input
                    type="text"
                    placeholder="Make"
                    onChange={(e) => setFormData({...formData, make: e.target.value})}
                />
                <input
                    type="text"
                    placeholder="Model"
                    onChange={(e) => setFormData({...formData, model: e.target.value})}
                />
                <input
                    type="number"
                    placeholder="Year"
                    onChange={(e) => setFormData({...formData, manufacturingYear: e.target.value})}
                />
                <input
                    type="number"
                    placeholder="Vehicle Value"
                    onChange={(e) => setFormData({...formData, vehicleValue: e.target.value})}
                />
                <button type="submit" className="zurich-btn-primary">Calculate Premium</button>
            </form>
        </div>
    );
};

export default VehicleDetails;
