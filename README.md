# E-Waste Collection System

Full-stack starter matching the supplied workflow: Waste Generator, Collection Agent and Admin.

## Stack
- Backend: Java 21, Spring Boot 3.5, Spring Security, JWT, Spring Data JPA, Maven
- Frontend: React + Vite + Bootstrap
- Database: TiDB Cloud (MySQL-compatible)
- Deployment: Render (backend), Vercel (frontend)

## Local prerequisites
Java 21+, Maven 3.9+, Node.js 20+, npm, a TiDB Cloud database (or local MySQL 8).

## 1. Configure backend
In `backend/src/main/resources/application.properties`, defaults are provided for local development. Prefer environment variables for real credentials:
- `DB_URL`: JDBC URL including `?sslMode=VERIFY_IDENTITY` for TiDB; example `jdbc:mysql://<host>:4000/ewaste?sslMode=VERIFY_IDENTITY`
- `DB_USERNAME`, `DB_PASSWORD`
- `JWT_SECRET`: at least 32 characters; set a long random secret
- `CORS_ORIGINS`: comma-separated frontend origins
- `ADMIN_NAME`, `ADMIN_EMAIL`, `ADMIN_PASSWORD`: optional initial admin created on startup when all are set

TiDB: create a database/schema named `ewaste`, copy the TiDB connection host/username/password from the TiDB Cloud connection dialog. TiDB typically uses port 4000 and TLS. Do not commit credentials.

From `backend/` run:
```bash
mvn spring-boot:run
```
API base URL: `http://localhost:8080/api`. Health: `http://localhost:8080/actuator/health` is not enabled; use `GET http://localhost:8080/api/health`.

## 2. Configure frontend
From `frontend/`:
```bash
npm install
```
Create `frontend/.env`:
```env
VITE_API_URL=http://localhost:8080/api
```
Then run `npm run dev` and open the URL printed by Vite.

## Initial admin
Set `ADMIN_NAME`, `ADMIN_EMAIL`, and `ADMIN_PASSWORD` in backend environment before starting. If those are absent, register a regular user and promote/create roles using a SQL admin action only if you control the database. Do not expose admin credentials in frontend code.

## User workflow
1. Register as a Waste Generator and sign in.
2. Create an e-waste report.
3. Schedule a pickup for that report.
4. Admin assigns a registered collector account to the pickup.
5. Collector updates status through ACCEPTED → PICKED_UP → COMPLETED.
6. Completing a pickup awards points to the requester (one award per pickup).

## Deploy backend to Render
1. Push this folder to a Git repository.
2. Create a Render **Web Service** and choose **Docker** as the runtime. Set the Root Directory to `backend` (Dockerfile is in that folder).
3. Render builds the image from `backend/Dockerfile`; no separate build/start command is needed.
4. Set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`, `CORS_ORIGINS`, and optional admin variables in Render Environment.
5. `CORS_ORIGINS` must contain your Vercel production URL, e.g. `https://your-app.vercel.app`. Redeploy after changing it.

## Deploy frontend to Vercel
1. Import repository into Vercel and set Root Directory to `frontend`.
2. Framework preset: Vite. Build command `npm run build`; output directory `dist`.
3. Set `VITE_API_URL` to your Render backend URL plus `/api`, e.g. `https://your-api.onrender.com/api`.
4. Redeploy. Add the final Vercel URL to backend `CORS_ORIGINS` and redeploy the backend.

## Notes
- Passwords are BCrypt hashed; JWTs are stored in localStorage for this demo. For production, consider secure HttpOnly cookies, email verification, rate limiting, and audit logging.
- `ddl-auto=update` is convenient for a college project. Use schema migrations for production.
- Never commit `.env`, passwords, or JWT secrets.
