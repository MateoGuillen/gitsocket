@echo off
setlocal EnableDelayedExpansion
if exist "C:\Program Files\PostgreSQL\13\bin\psql.exe" (
    @REM Run psql
    @echo Intentando ingresar con las opciones por defecto, ingrese la contraseña principal (si estas no son sus opciones, modifique este archivo):
    "C:\Program Files\PostgreSQL\13\bin\psql.exe" -h localhost -U postgres -p 5432 -c "CREATE DATABASE bd_hospital;" -f "bd.sql"

    pause
)
