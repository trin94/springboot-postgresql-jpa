docker run -d \
    --name database \
    -e POSTGRES_PASSWORD=pw \
    -p 5432:5432 \
    postgres:12.3
    

