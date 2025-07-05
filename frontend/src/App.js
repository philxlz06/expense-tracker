import React, { useState } from "react";

function App() {
  const [description, setDescription] = useState("");
  const [prediction, setPrediction] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await fetch("http://localhost:8000/predict", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ description }),
    });

    const data = await res.json();
    setPrediction(data.predicted_category);
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
        />
        <button type="submit">Predict</button>
      </form>
      {prediction && (
        <h2 style={{ marginTop: "1rem" }}>
          Predicted Category: <strong>{prediction}</strong>
        </h2>
      )}
    </div>
  );
}

export default App;
