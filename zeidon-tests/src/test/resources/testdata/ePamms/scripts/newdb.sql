create database epammsm;
CREATE USER 'ePammsTester'@'localhost' IDENTIFIED BY 'tester';
grant all on epammsm.* to 'ePammsTester'@'localhost';
