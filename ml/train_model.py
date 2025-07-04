import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.ensemble import RandomForestClassifier
from sklearn.pipeline import Pipeline
import joblib

# Load data
df = pd.read_csv(
    "/Users/philx/Documents/BACKEND/expense-tracker/ml/expenses.csv")

X = df["description"]
y = df["category"]

# Build pipeline: TF-IDF → Random Forest
pipeline = Pipeline([
    ('tfidf', TfidfVectorizer()),
    ('clf', RandomForestClassifier(n_estimators=100, random_state=42))
])

# Train on full data
pipeline.fit(X, y)

# Quick sanity check: predict on first 5 samples and compare to true labels
sample_preds = pipeline.predict(X[:5])
print("Sample predictions:", sample_preds)
print("True labels:     ", y[:5].values)

# Save model
joblib.dump(pipeline, "expense_classifier.joblib")

print("✅ Model trained on full data and saved as 'expense_classifier.joblib'")

# /Users/philx/Documents/BACKEND/expense-tracker/ml/venv/bin/python /Users/philx/Documents/BACKEND/expense-tracker/ml/train_model.py
# To run this script, use the command:
