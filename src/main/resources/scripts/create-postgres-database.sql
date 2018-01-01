CREATE DATABASE sample_application_dev;
CREATE DATABASE sample_application_prod;

CREATE USER sample_dev_user WITH PASSWORD 'changeme';
CREATE USER sample_dev_prod WITH PASSWORD 'changeme';

GRANT ALL PRIVILEGES ON DATABASE sample_application_dev to sample_dev_user;
GRANT ALL PRIVILEGES ON DATABASE sample_application_prod to sample_dev_prod;
