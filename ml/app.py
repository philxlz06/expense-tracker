from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import joblib

# Define input schema


class ExpenseData(BaseModel):
    description: str


app = FastAPI()

# Enable CORS
app.add_middleware(
    CORSMiddleware,
    # You can specify origins like ["http://localhost:8000"] for more security
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Load your trained model
model = joblib.load("expense_classifier.joblib")


@app.post("/predict")
def predict(expense: ExpenseData):
    # Input must be a list of strings because TfidfVectorizer expects iterable text
    input_data = [expense.description]

    prediction = model.predict(input_data)

    return {"predicted_category": prediction[0]}

# To run this FastAPI app, use the command:
# uvicorn app:app --reload


# To run this FastAPI app, use the command
# (venv) (.venv) philx@Phils-Mac ml % uvicorn app:app --reload
