import React, { useState } from "react";

function App() {
  const [description, setDescription] = useState("");
  const [prediction, setPrediction] = useState("");
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setPrediction("");

    try {
      // Spring Boot prediction API uses GET with query param
      const res = await fetch(
        `http://localhost:8080/api/expenses/predict?description=${encodeURIComponent(description)}`
      );

      if (!res.ok) {
        throw new Error(`Error: ${res.status} ${res.statusText}`);
      }

      const data = await res.text(); // Assuming your API returns plain text category
      setPrediction(data);
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h1>Expense Category Predictor</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={description}
          placeholder="e.g., Lunch at Chipotle"
          onChange={(e) => setDescription(e.target.value)}
          style={{ width: "300px", marginRight: "1rem" }}
          required
        />
        <button type="submit">Predict</button>
      </form>

      {prediction && (
        <h2 style={{ marginTop: "1rem" }}>
          Predicted Category: <strong>{prediction}</strong>
        </h2>
      )}

      {error && (
        <p style={{ marginTop: "1rem", color: "red" }}>
          {error}
        </p>
      )}
    </div>
  );
}

export default App;


// to run frontend cd frontend 
// npm install
// npm start
