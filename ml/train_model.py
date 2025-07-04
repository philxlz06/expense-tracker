# train_model.py

import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.ensemble import RandomForestClassifier
from sklearn.pipeline import Pipeline
from sklearn.model_selection import train_test_split
import joblib

# Load data
df = pd.read_csv(
    "/Users/philx/Documents/BACKEND/expense-tracker/ml/expenses.csv")

# Split into train/test (optional here since dataset is tiny)
X = df["description"]
y = df["category"]

# Build pipeline: TF-IDF → Random Forest
pipeline = Pipeline([
    ('tfidf', TfidfVectorizer()),
    ('clf', RandomForestClassifier(n_estimators=100, random_state=42))
])

# Train
pipeline.fit(X, y)

# Save model
joblib.dump(pipeline, "expense_classifier.joblib")

print("✅ Model trained and saved as 'expense_classifier.joblib'")

#/Users/philx/Documents/BACKEND/expense-tracker/ml/venv/bin/python /Users/philx/Documents/BACKEND/expense-tracker/ml/train_model.py
# To run this script, use the command: