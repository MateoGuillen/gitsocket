if ! command -v psql &> /dev/null
then
    echo No se encontrĂ³ psql
    exit
else
    psql -h localhost -U postgres -p 5432 -c "CREATE DATABASE bd_hospital;" -f "bd.sql"
fi
