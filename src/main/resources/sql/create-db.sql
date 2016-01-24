CREATE TABLE uzytkownicy (
    id int(11) primary key,
    imie varchar(50),
	nazwisko varchar(100),
	email varchar(200),
	haslo varchar(100),
	zdjecie varchar(500),
	aktywny tinyint(1),
	uprawnienia int(11)
);
