@echo off
setlocal

REM Set path to schema.sql (adjust if moved)
set "SQL_PATH=%~dp0src\main\java\org\sprint\schema.sql"

REM Check if the file exists
if not exist "%SQL_PATH%" (
    echo [ERROR] schema.sql not found at:
    echo         %SQL_PATH%
    pause
    exit /b 1
)

REM Ask for PostgreSQL password input
set /p PGPASSWORD=Enter PostgreSQL password: 

REM Run the SQL file with psql
echo Running schema.sql ...
psql -U postgres -d postgres -f "%SQL_PATH%"

REM Clean up sensitive vars
set PGPASSWORD=

pause
endlocal
