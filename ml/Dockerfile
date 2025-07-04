# Use official Python image
FROM python:3.12-slim

# Set working directory in the container
WORKDIR /app

# Copy the requirements
COPY requirements.txt .

# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy all project files into the container
COPY . .

# Expose FastAPI default port
EXPOSE 8000

# Run the FastAPI app
CMD ["python", "-m", "uvicorn", "app:app", "--host", "0.0.0.0", "--port", "8000"]
