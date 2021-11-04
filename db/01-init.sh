

set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE DATABASE $APP_DB_NAME;
  \connect $APP_DB_NAME $POSTGRES_USER
  BEGIN;
     CREATE TABLE seller
         (
             id SERIAL PRIMARY KEY,
             name VARCHAR(50),
             phone VARCHAR(50)

         );
   CREATE TABLE cat
   (
       cat_id SERIAL PRIMARY KEY,
       price INTEGER,
       breed VARCHAR(50),
       seller_id INTEGER REFERENCES seller(id)

   );
      INSERT INTO seller (name,phone)
      VALUES ('Satan','666-66-666'),('Bender','1010101010010');
      INSERT INTO cat (price,breed,seller_id)
      VALUES (666,'Hellcat',1),(1010101,'Robocat',2);


  COMMIT;
EOSQL



