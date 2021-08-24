@echo off
setlocal EnableDelayedExpansion
if exist "C:\Program Files\PostgreSQL\13\bin\psql.exe" (
    @REM REM Copyright (c) 2012-2020, EnterpriseDB Corporation.  All rights reserved

    @REM REM PostgreSQL server psql runner script for Windows

    @REM SET server=localhost
    @REM SET /P server="Server [!server!]: "

    @REM SET database=postgres
    @REM SET /P database="Database [!database!]: "

    @REM SET port=5432
    @REM SET /P port="Port [!port!]: "

    @REM SET username=postgres
    @REM SET /P username="Username [!username!]: "

    @REM for /f "delims=" %%a in ('chcp ^|find /c "932"') do @ SET CLIENTENCODING_JP=%%a
    @REM if "!CLIENTENCODING_JP!"=="1" SET PGCLIENTENCODING=SJIS
    @REM if "!CLIENTENCODING_JP!"=="1" SET /P PGCLIENTENCODING="Client Encoding [!PGCLIENTENCODING!]: "

    REM Run psql
    @echo Intentando ingresar con las opciones por defecto, ingrese la contraseña principal:
    "C:\Program Files\PostgreSQL\13\bin\psql.exe" -h localhost -U postgres -p 5432 -c "CREATE DATABASE bd_hospital;" -f "bd.sql"

    pause



)