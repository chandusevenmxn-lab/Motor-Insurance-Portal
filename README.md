# Motor Insurance Portal

This repository contains an Android app, a Spring Boot backend API, and a frontend portal for a motor insurance application.

## Project structure

- `app/` - Android application
- `backend/experience-api/` - Spring Boot backend service
- `frontend/individual-portal/` - frontend portal

## Run the application with Docker Compose

The easiest way to run the backend with PostgreSQL is via Docker Compose.

### Start services

```bash
docker compose up --build -d
```

### Stop services

```bash
docker compose down
```

### View logs

```bash
docker compose logs -f experience-api
docker compose logs -f postgres
```

### Access points

- Backend API: http://localhost:8080
- PostgreSQL: localhost:5432

## Build the backend image manually

```bash
docker build -f backend/experience-api/Dockerfile -t motor-insurance-api:latest .
```

Run it manually:

```bash
docker run -d --name motor-insurance-api -p 8080:8080 motor-insurance-api:latest
```

## Deploy updated changes

1. Commit and push your changes to the repository.
2. Build the latest image.
3. Recreate the containers to apply the updated image.

```bash
git add .
git commit -m "Update deployment"
git push origin main
docker compose up --build -d
```

## Notes

- The default backend configuration uses an in-memory H2 database for local development.
- The Docker Compose setup uses PostgreSQL for a more realistic deployment environment.
- For production, consider using environment variables for database credentials and secrets.
