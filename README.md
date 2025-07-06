Expense Category Predictor
A full-stack expense categorization app that uses machine learning to predict the category of expense descriptions. Built with FastAPI (Python) backend serving a trained ML model, a React frontend UI, and deployed on an AWS EC2 instance.

Features
ML-powered expense category prediction using a Random Forest classifier with TF-IDF vectorization
REST API built with FastAPI for fast and scalable inference
React frontend with a clean UI to input expense descriptions and get category predictions
CORS enabled for smooth frontend-backend communication
Dockerized backend for easy local development and deployment
Temporary deployment on AWS EC2 for live demo and testing

Tech Stack
Backend: Python, FastAPI, scikit-learn, joblib
Frontend: React, JavaScript, HTML/CSS
Deployment: Docker, AWS EC2
Tools: Git, pip, uvicorn

Prerequisites
Python 3.12+
Node.js and npm
Docker (optional for containerized deployment)
AWS account for EC2 deployment (optional)

Run Backend Locally
Create and activate a Python virtual environment:
python3 -m venv venv
source venv/bin/activate
Install dependencies:
pip install -r requirements.txt

Run the FastAPI server:
uvicorn app:app --reload
The backend API will be available at: http://127.0.0.1:8000

Run Frontend Locally
Navigate to frontend directory:
cd frontend
Install npm dependencies:
npm install

Start React development server:
npm start
Open browser at http://localhost:3000

Using Docker (Backend)
Build the Docker image:
docker build -t expense-ml-service .

Run the container:
docker run -p 8000:8000 expense-ml-service
API will be accessible at http://localhost:8000

Deploy on AWS EC2 (Summary)
Created EC2 instance (free tier)
Configured security group to allow inbound traffic on port 8000 and SSH (22)
SSH into EC2, installed Python and dependencies
Deployed the backend app and ran it with uvicorn
Connected React frontend to EC2 public IP API for live predictions

API Usage

POST /predict
Request body:
json
{
  "description": "Lunch at Chipotle"
}
Response:
{
  "predicted_category": "Food"
}

Future Improvements
Improve ML model with more data and better feature engineering
Add authentication & user management
Deploy frontend and backend on a single domain
Add expense tracking, history, and analytics features
